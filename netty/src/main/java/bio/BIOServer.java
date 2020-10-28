package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        while (true){
            System.out.println("等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("建立连接");
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("id : "+Thread.currentThread().getId()+"; name : "+Thread.currentThread().getName());
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    /**
     * 对socket的处理!
     * @param socket
     * @throws IOException
     */
    public static void handler(Socket socket) throws IOException {
        System.out.println("id : "+Thread.currentThread().getId()+"; name : "+Thread.currentThread().getName());
        InputStream inputStream = socket.getInputStream();
        byte[] byteArr = new byte[1024];
        int read = -2;
        while ((read=inputStream.read(byteArr))!=-1){
            String msg = new String(byteArr, 0, read);
            System.out.println(msg);
        }
    }

}
