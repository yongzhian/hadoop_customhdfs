package hadoop.rpc.publish;

import hadoop.rpc.protocal.LoginServiceProtocal;
import hadoop.rpc.server.LoginServiceImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by yongz on 2018/2/20.
 */
public class ServicePublisher {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        RPC.Builder builder = new RPC.Builder(conf);
        builder.setBindAddress("localhost").setPort(10000)
                .setProtocol(LoginServiceProtocal.class)
                .setInstance(new LoginServiceImpl());
        RPC.Server server = builder.build();
        server.start();
    }
}
