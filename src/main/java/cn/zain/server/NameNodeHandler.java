package cn.zain.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Created by yongz on 2018/2/7.
 */
public class NameNodeHandler implements Runnable {
    private Socket socket;
    private String[] dataNodes = {"vm2.zain.cn","vm3.zain.cn","vm4.zain.cn"};

    public NameNodeHandler(Socket sc) {
        this.socket = sc;
    }

    public void run() {
        try {
            System.out.println("处理请求....");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            byte[] b = new byte[1024];
            int read = inputStream.read(b);
            String request = new String(b, 0, read);
            String[] splits = request.split(":");
            if("PUTBLOCK".equals(splits[0])){
                long timeMillis = System.currentTimeMillis();

                int nextInt = new Random().nextInt(3);
                String dataNode = dataNodes[nextInt];
                outputStream.write((timeMillis+":"+dataNode).getBytes());
                outputStream.flush();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
