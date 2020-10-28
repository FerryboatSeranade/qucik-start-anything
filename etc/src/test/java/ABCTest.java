import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ABCTest {

    @Test
    public void test() throws IOException {
        String streamSHA1 = null;
        String streamSHA2;
        String streamSHA3 = null;
        try {
            //未压缩文件的输入流
            FileInputStream fileInputStream = new FileInputStream("D:\\temp\\Telegram_v7.1.3.apk");
            ZipInputStream zis = new ZipInputStream(fileInputStream);
            streamSHA1 = new GetStreamSHA256().getStreamSHA1(fileInputStream);
            streamSHA2 = new GetStreamSHA256().getStreamSHA1(zis);

            String zipFilePath="D:\\temp\\Telegram_v7.1.3.zip";
            ZipFile zipFile = new ZipFile(zipFilePath);
            FileInputStream fileInputStream2 = new FileInputStream(zipFilePath);
            ZipInputStream zis2 = new ZipInputStream(fileInputStream2);
            ZipEntry ze;
            while ((ze=zis2.getNextEntry())!=null){
                if (ze.getName().equals("Telegram_v7.1.3.apk")) {
                    InputStream inputStream = zipFile.getInputStream(ze);
                    streamSHA3 = new GetStreamSHA256().getStreamSHA1(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(streamSHA1,streamSHA3);

    }

}
