import lombok.Data;
import org.junit.Test;
import shu.shen.enumType.HashAlgorithmType;
import shu.shen.util.SecurityUtil;
import shu.shen.util.ZipMemoryUtil;

import java.io.*;


public class resolveZipFromMemory {
    @Data
    static class versionFile{
        private String checksum;
        private String version;
        private String deviceModel;
    }
    @Test
    public void test2() throws IOException {
        String zipFile = "D:\\temp\\Telegram_v7.1.3.zip";
        String zipNodeApk = "Telegram_v7.1.3.apk";
        String checkSum = ZipMemoryUtil.getCheckSum(zipFile, zipNodeApk, HashAlgorithmType.SHA256.getAlgorithm());
        String zipNodeVersion = "version.json";
        String content = ZipMemoryUtil.getContent(zipFile, zipNodeVersion);
        String apkFile = "D:\\temp\\Telegram_v7.1.3.apk";
        String encryp = SecurityUtil.encryp(apkFile, HashAlgorithmType.SHA256.getAlgorithm());
        System.out.println("content = " + content);

        versionFile deserialize = JsonUtils.deserialize(content, versionFile.class);
        System.out.println("deserialize = " + deserialize);
        System.out.println("deserialize.getChecksum() = " + deserialize.getChecksum());
        System.out.println("deserialize.getDeviceModel() = " + deserialize.getDeviceModel());
        System.out.println("deserialize.getVersion() = " + deserialize.getVersion());
    }

}