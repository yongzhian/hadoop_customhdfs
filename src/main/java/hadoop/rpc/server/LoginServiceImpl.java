package hadoop.rpc.server;

import hadoop.rpc.protocal.LoginServiceProtocal;

/**
 * Created by yongz on 2018/2/20.
 */
public class LoginServiceImpl implements LoginServiceProtocal{
    @Override
    public String login(String name,String pwd) {
        return "success" + name;
    }
}
