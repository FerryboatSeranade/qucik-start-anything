import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.zip.*;

/**
 * 压缩和解压,我目前不太需要
 */
@Slf4j
public class ZipUtil {
    private static final int BUFFER = 1024 * 10;
    /**
     * 将指定目录压缩到和该目录同名的zip文件，自定义压缩路径
     *
     * @param sourceFilePath 目标文件路径
     * @param zipFilePath    指定zip文件路径
     * @return
     */
    public static boolean zip(String sourceFilePath, String zipFilePath,String zipFileName) {
        boolean result = false;
        File source = new File(sourceFilePath);
        if (!source.exists()) {
            log.info(sourceFilePath + " doesn't exist.");
            return result;
        }
        if (!source.isDirectory()) {
            log.info(sourceFilePath + " is not a directory.");
            return result;
        }
        File zipFile = new File(zipFilePath + File.separator + zipFileName + ".zip");
        if (zipFile.exists()) {
            log.info(zipFile.getName() + " is already exist.");
            return result;
        } else {
            if (!zipFile.getParentFile().exists()) {
                if (!zipFile.getParentFile().mkdirs()) {
                    log.info("cann't create file " + zipFileName);
                    return result;
                }
            }
        }
        log.info("creating zip file...");
        FileOutputStream dest = null;
        ZipOutputStream out = null;
        try {
            dest = new FileOutputStream(zipFile);
            CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
            out = new ZipOutputStream(new BufferedOutputStream(checksum));
            out.setMethod(ZipOutputStream.DEFLATED);
            compress(source, out, source.getName());
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (result) {
            log.info("done.");
        } else {
            log.info("fail.");
        }
        return result;
    }

    private static void compress(File file, ZipOutputStream out, String mainFileName) {
        int index = file.getAbsolutePath().indexOf(mainFileName);
        String entryName = file.getAbsolutePath().substring(index);
        //System.out.println(entryName);
        if (file.isFile()) {
            FileInputStream fi = null;
            BufferedInputStream origin = null;
            try {
                fi = new FileInputStream(file);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(entryName);
                out.putNextEntry(entry);
                byte[] data = new byte[BUFFER];
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (origin != null) {
                    try {
                        origin.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (file.isDirectory()) {
            try {
                out.putNextEntry(new ZipEntry(entryName+File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
            File[] fs = file.listFiles();
            if (fs != null && fs.length > 0) {
                for (File f : fs) {
                    compress(f, out, mainFileName);
                }
            }
        }
    }

    /**
     * 将zip文件解压到指定的目录，该zip文件必须是使用该类的zip方法压缩的文件
     *
     * @param zipFile   要解压的zip文件
     * @param destPath  指定解压到的目录
     * @return
     */
    public static boolean unzip(File zipFile, String destPath) {
        boolean result = false;
        if (!zipFile.exists()) {
            log.info(zipFile.getName() + " doesn't exist.");
            return result;
        }
        File target = new File(destPath);
        if (!target.exists()) {
            if (!target.mkdirs()) {
                log.info("can't create file " + target.getName());
                return result;
            }
        }
        String mainFileName = zipFile.getName().replace(".zip", "");
        File targetFile = new File(destPath + File.separator + mainFileName);
        if (targetFile.exists()) {
            log.info(targetFile.getName() + " already exist.");
            return result;
        }
        ZipInputStream zis = null;
        log.info("start unzip file ...");
        try {
            FileInputStream fis = new FileInputStream(zipFile);//转成内存中的流
            CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
            zis = new ZipInputStream(new BufferedInputStream(checksum));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                int count;
                byte data[] = new byte[BUFFER];
                String entryName = entry.getName();
                //log.info(entryName);
                String newEntryName = destPath + File.separator + entryName;
                newEntryName=newEntryName.replaceAll("\\\\", "/");//win->unix style
                File f = new File(newEntryName);
                if(newEntryName.endsWith("/")){//目录
                    if(!f.exists()){//不存在就尝试创建
                        if(!f.mkdirs()) {
                            throw new RuntimeException("can't create directory " + f.getName());
                        }
                    }
                }else{
                    File temp=f.getParentFile();
                    if (!temp.exists()) {
                        if (!temp.mkdirs()) {
                            throw new RuntimeException("create file " + temp.getName() + " fail");
                        }
                    }
                    FileOutputStream fos = new FileOutputStream(f);
                    BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
                    while ((count = zis.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                }
            }
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (result) {
            log.info("done.");
        } else {
            log.info("fail.");
        }
        return result;
    }

    //todo:测试所用
    public static void main(String[] args) throws IOException {
        String zipfile ="D:\\temp\\c\\beetlsql.zip";
        File zipFile = new File(zipfile);
        String output="D:\\temp\\c";
        ZipUtil.unzip(zipFile, output);
    }

}

