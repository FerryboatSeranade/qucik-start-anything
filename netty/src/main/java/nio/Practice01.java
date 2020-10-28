package nio;

import sun.nio.ch.FileChannelImpl;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;

public class Practice01 {
    public static void main(String[] args) throws IOException {
        String content="hello,world";
        String modulePath="netty/";
        String fileName="file01.txt";
        File file = new File(modulePath+fileName);
        String absolutePath = file.getAbsolutePath();
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileInputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        boolean b = fileChannel instanceof FileChannelImpl;
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put(content.getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileInputStream.close();
    }
}
