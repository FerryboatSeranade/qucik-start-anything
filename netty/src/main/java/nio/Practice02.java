package nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Practice02 {
    public static void main(String[] args) throws Exception {
        String moduleFile = "netty/";
        String fileName = "file01.txt";
        File file = new File(moduleFile + fileName);
        if (!file.exists()) {
            throw new Exception(file.getAbsolutePath() + "不存在,无法读取");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(50);
        fileChannel.read(byteBuffer);
        //.array()取出hb
        String s = new String(byteBuffer.array());
        System.out.println("byteBuffer.array().length = " + byteBuffer.array().length);
        System.out.println("s = " + s);
    }

}
