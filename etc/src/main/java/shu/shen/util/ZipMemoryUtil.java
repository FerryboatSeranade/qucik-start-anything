package shu.shen.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipMemoryUtil {

    /**
     * 从一个压缩文件中获取子文件节点的校验和
     */
    public static String getCheckSum(String zipFile, String zipNode, String algorithm) throws IOException {
        String checksum = null;
        ZipFile zf = new ZipFile(zipFile);
        InputStream in = new BufferedInputStream(new FileInputStream(zipFile));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.getName().equals(zipNode)) {//是文件
                InputStream inputStream = zf.getInputStream(ze);
                checksum = SecurityUtil.encryp(inputStream, algorithm);
            }
        }
        zin.closeEntry();
        zin.close();
        return checksum;
    }

    /**
     * 读取压缩文件中的子文件节点内容
     */
    public static String getContent(String zipFile, String zipNode) throws IOException {
        ZipFile zf = new ZipFile(zipFile);
        InputStream in = new BufferedInputStream(new FileInputStream(zipFile));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        StringBuilder lines = new StringBuilder();
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.getName().equals(zipNode) && ze.getSize() > 0) {//是文件
                BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
                String line;
                while ((line = br.readLine()) != null) {
                    lines.append(line);
                }
                br.close();
            }
        }
        zin.closeEntry();
        zin.close();
        return lines.toString();
    }

}
