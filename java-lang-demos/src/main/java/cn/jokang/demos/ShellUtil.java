package cn.jokang.demos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoukang
 * @date 2020/10/21
 */
public class ShellUtil {
    public static void main(String[] args) throws Exception {
//            String shellCommand = "python /Users/zhoukang/Downloads/sleep.py";
//            String[] cmd = {"/bin/bash", "-cl", shellCommand};
//            Process pid = Runtime.getRuntime().exec(cmd);
//            if (pid == null) {
//                throw new RuntimeException("执行命令失败,获取不到Process对象");
//            }
//            System.out.println("process" + pid);
//
////            InputStream stdoutStream = pid.getInputStream();
////            InputStream stderrStream = pid.getErrorStream();
////            readStd("stderr", stderrStream);
////            readStd("stdout", stdoutStream);
//
//            System.out.println("Start to wait");
//            if (!pid.waitFor(60, TimeUnit.SECONDS)) {
//                throw new RuntimeException("命令执行超时");
//            }
//        } catch (InterruptedException | IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("javac");
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<ERROR>");
            while ( (line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("</ERROR>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
    }

    private static void readStd(String std, InputStream processStream) throws IOException {
        System.out.println("read from " + std);
        StringBuilder sb = new StringBuilder();
        InputStreamReader ins = new InputStreamReader(processStream);
        BufferedReader bufferedReader = new BufferedReader(ins);
        String tmpLine;
        // readLine阻塞直到shell脚本结束
        while ((tmpLine = bufferedReader.readLine()) != null) {
            sb.append(tmpLine);
            System.out.println("get data from " + std + ": " + tmpLine);
        }
        System.out.println(std + "=" + sb.toString());
    }

}
