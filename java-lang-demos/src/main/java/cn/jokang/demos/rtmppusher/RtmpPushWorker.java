package cn.jokang.demos.rtmppusher;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoukang
 * @date 2020/10/21
 */
@Slf4j
public class RtmpPushWorker implements Runnable {
    private String ffmpegPath;
    private String baseDir;
    private List<String> videoFiles;
    private String rtmpAddress;
    private String fifoPath;
    private String liveRoomStr;

    Process readFifoProcess;
    FileWriter fifoHolderFileWriter;

    public RtmpPushWorker(String ffmpegPath, List<String> videoFiles, String rtmpAddress, String baseDir, String liveRoomStr) {
        this.ffmpegPath = ffmpegPath;
        this.videoFiles = videoFiles;
        this.rtmpAddress = rtmpAddress;
        this.baseDir = baseDir;
        this.liveRoomStr = liveRoomStr;

        this.fifoPath = baseDir + "/fifo_" + liveRoomStr;

    }

    @Override
    public void run() {
        try {
            String createFifoCmd = "mkfifo " + fifoPath;
            log.info("开始创建管道 cmd={}", createFifoCmd);
            //TODO 现在是已存在的时候直接抛错
            execAndWaitAndCheckExitCode(createFifoCmd, 10);
            log.info("创建管道成功 cmd={}", createFifoCmd);
        } catch (Exception e) {
            log.error("创建管道异常 fifoPath={}", fifoPath, e);
            return;
        }

        try {
            String readFifoStderrFile = baseDir + "/read_fifo_stderr.log";
            String readFifoStdoutFile = baseDir + "/read_fifo_stdout.log";
            String readFifoCmd = ffmpegPath + " -re -loglevel +repeat+level+info -nostdin " +
                "-i " + fifoPath + " " +
                "-c:v copy -c:a aac -rtmp_buffer 3000 -f flv -threads 6 " + rtmpAddress;
            ProcessBuilder processBuilder = new ProcessBuilder(readFifoCmd.split("\\s+"));
            processBuilder.redirectError(new File(readFifoStderrFile));
            processBuilder.redirectOutput(new File(readFifoStdoutFile));
            log.info("开始创建读管道进程 cmd={}", readFifoCmd);
            readFifoProcess = processBuilder.start();
            log.info("创建读管道进程完成 cmd={}", readFifoCmd);
        } catch (Exception e) {
            log.error("创建读管道进程异常 {}", fifoPath, e);
            return;
        }

        // 需要在读管道命令启动成功之后打开,否则会block住
        try {
            fifoHolderFileWriter = new FileWriter(fifoPath);
        } catch (IOException e) {
            log.error("创建管道holder异常 {}", fifoPath, e);
            return;
        }

        try {
            // 循环列表文件进行推流
            for (String file : videoFiles) {
                String filePath = baseDir + "/" + file;
                String writeFifoStderrFile = baseDir + "/write_fifo_stderr.log";
                String writeFifoStdoutFile = baseDir + "/write_fifo_stdout.log";
                String writeFifoCmd = ffmpegPath + " -y -loglevel +repeat+level+info -nostdin " +
                    "-i " + filePath + " " +
                    "-f mpegts -strict -2 -q:v 5 -acodec aac -ar 16000 -vbsf h264_mp4toannexb -vcodec libx264 -threads 6 " + fifoPath;
                ProcessBuilder writeFifoPb = new ProcessBuilder(writeFifoCmd.split("\\s+"));
                writeFifoPb.redirectError(new File(writeFifoStderrFile));
                writeFifoPb.redirectOutput(new File(writeFifoStdoutFile));
                log.info("开始执行写管道命令 liveRoomId={} cmd={}", liveRoomStr, writeFifoCmd);
                Process p = writeFifoPb.start();
                //TODO wait seconds?
                int exitValue = p.waitFor();
                log.info("写管道执行完成 liveRoomId={} exitValue={} file={}", liveRoomStr, exitValue, filePath);
                if (0 != exitValue) {
                    break;
                }
            }
        } catch (Exception e) {
            //TODO 是否需要重试?
            log.error("写管道执行异常 liveRoomId={}", liveRoomStr, e);
            return;
        }

        try {
            fifoHolderFileWriter.close();
        } catch (Exception e) {
            log.error("关闭管道Holder异常 liveRoomStr={}", liveRoomStr, e);
        }
        try {
            readFifoProcess.destroy();
        } catch (Exception e) {
            log.error("关闭读管道进程异常 liveRoomStr={}", liveRoomStr, e);
        }
        try {
            execAndWaitAndCheckExitCode("unlink " + fifoPath, 10);
        } catch (Exception e) {
            log.error("删除管道失败 fifoPath={}", fifoPath, e);
        }
    }

    /**
     * 执行单个命令,并检查返回码,命令执行超时的时候,尝试kill掉,回收资源
     */
    private void execAndWaitAndCheckExitCode(String cmd, int seconds) throws IOException, InterruptedException {
        if (seconds <= 0) {
            throw new IllegalArgumentException("seconds should be greater than 0");
        }
        Process proc = Runtime.getRuntime().exec(cmd);

        boolean procExit = proc.waitFor(seconds, TimeUnit.SECONDS);
        if (procExit) {
            int exitCode = proc.exitValue();
            if (exitCode == 0) {
                return;
            } else {
                throw new IllegalStateException("执行命令异常 exitCode=" + exitCode + " cmd:" + cmd);
            }
        } else {
            // 执行超时时,尝试kill掉原进程
            proc.destroy();
            throw new IllegalStateException("执行命令超时 cmd:" + cmd);
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> playList = Lists.newArrayList(
            "11.mp4",
            "22.mp4",
            "33.mp4",
            "44.mp4"
        );
        String rtmpAddress = "rtmp://beauty-tx-push.meituan.net/mtlr/1234456";
        RtmpPushWorker worker = new RtmpPushWorker("/usr/local/bin/ffmpeg", playList, rtmpAddress, "/Users/数字人/live_room", "live_room");
        worker.run();
    }

}
