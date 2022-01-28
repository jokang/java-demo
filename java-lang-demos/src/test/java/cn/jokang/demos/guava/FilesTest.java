package cn.jokang.demos.guava;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author zhoukang
 * @date 2020-06-23
 */
public class FilesTest {
    @Test
    public void testWriteFile() throws IOException {
        final File f = new File("/tmp/test.txt");
        String content = "this is the content.";
        Files.write(content.getBytes(Charset.forName("UTF-8")), f);

        String content2 = "this is the new content.";
        // 后面写的会把前面的覆盖掉
        Files.write(content2.getBytes(Charset.forName("UTF-8")), f);
    }

    @Test
    public void testWriter() throws IOException {
        final File f = new File("/tmp/test.txt");
        String content = "this is the content.";
        BufferedWriter bw = Files.newWriter(f, Charset.forName("UTF-8"));
        bw.write("Line 1\n");
        bw.write("Line 2");
        bw.close();
        System.out.println("Done");
    }

    @Test
    public void testWriterWithTry() {
        File f = new File("/tmp/test.txt");
        try (BufferedWriter bw = Files.newWriter(f, Charset.defaultCharset())) {
            String header = Joiner.on(",").join("col1", "col2", "col3");
            bw.write(header);
            bw.newLine();

            String line = Joiner.on(",").join("val1", "val2", "val3");
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            //
        }
    }
}
