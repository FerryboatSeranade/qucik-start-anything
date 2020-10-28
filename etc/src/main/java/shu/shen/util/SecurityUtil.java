package shu.shen.util;

import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;

public class SecurityUtil {

    public static String encryp(InputStream fis, String hashType) {
        String str = "";
        try {
            str = getHash(fis, hashType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String encryp(File file, String hashType) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        return encryp(fis, hashType);
    }

    public static String encryp(String filePath, String hashType) throws FileNotFoundException {
        File file = new File(filePath);
        return encryp(file, hashType);
    }

    private static String getHash(InputStream fis, String hashType) throws Exception {
        byte buffer[] = new byte[1024];
        MessageDigest md5 = MessageDigest.getInstance(hashType);
        for (int numRead = 0; (numRead = fis.read(buffer)) > 0; ) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();
        return toHexString(md5.digest());
    }

    private static String toHexString(byte b[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aB : b) {
            sb.append(Integer.toHexString(aB & 0xFF));
        }
        return sb.toString();
    }

}
