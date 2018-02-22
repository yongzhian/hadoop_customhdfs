package cn.zain.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yongz on 2018/2/7.
 */
public class NameNode {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("服务器启动...");
        while (true){
            Socket sc = serverSocket.accept();
            Thread thread = new Thread(new NameNodeHandler(sc));
            thread.start();
        }

    }
}
