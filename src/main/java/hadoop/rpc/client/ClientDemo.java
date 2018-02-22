package hadoop.rpc.client;

import hadoop.rpc.protocal.LoginServiceProtocal;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by yongz on 2018/2/20.
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        LoginServiceProtocal loginServiceProtocal = RPC.getProxy(LoginServiceProtocal.class, 1L,
                new InetSocketAddress("localhost", 10000), new Configuration());
        String login = loginServiceProtocal.login("123dd","12");
        System.out.println(login);

    }
}
