import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * 计算文件sha256值
 *
 * @author ryz
 * @since 2020-05-12
 */
public class GetFileSHA256 {

    @Test
    public void test() {
        File file = new File("D:\\temp\\Telegram_v7.1.3.apk");
        System.out.println(" 文件 " + file + " SHA256值是:" + getFileSHA1(file));//703c20e4735e211b4b6efc6b70d8ef22cce36954bff106c62f2e7a2f15268ce
    }

    public String getFileSHA1(File file) {
        String str = "";
        try {
            str = getHash(file, "SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private String getHash(File file, String hashType) throws Exception {
        InputStream fis = new FileInputStream(file);
        byte buffer[] = new byte[1024];
        MessageDigest md5 = MessageDigest.getInstance(hashType);
        for (int numRead = 0; (numRead = fis.read(buffer)) > 0; ) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();
        return toHexString(md5.digest());
    }

    private String toHexString(byte b[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aB : b) {
            sb.append(Integer.toHexString(aB & 0xFF));
        }
        return sb.toString();
    }

}
