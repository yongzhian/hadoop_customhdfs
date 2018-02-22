package cn.zain.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by yongz on 2018/2/7.
 */
public class Client {


    public static void main(String[] args) throws IOException {
        //与namenode请求上传，相应一个blockid，以及可用的datanode
        Socket toNn = new Socket("localhost", 9000);

        InputStream toNnInputStream = toNn.getInputStream();
        OutputStream toNnOutputStream = toNn.getOutputStream();

        toNnOutputStream.write("PUTBLOCK:/aa/a.log:1".getBytes());
        toNnOutputStream.flush();

        //这里默认不超过1024
        byte[] b = new byte[1024];
        int read = toNnInputStream.read(b);
        //规定服务器返回BLK_001:vm2.zain.cn
        String resp = new String(b, 0, read);
        String[] splits = resp.split(":");
        String blkId = splits[0];
        String dnHost = splits[0];

        System.out.println("client收到： " + resp);

        toNnInputStream.close();
        toNnOutputStream.close();
        toNn.close();
    }

}
