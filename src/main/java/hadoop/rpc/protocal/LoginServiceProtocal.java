package hadoop.rpc.protocal;

/**
 * Created by yongz on 2018/2/20.
 */
public interface LoginServiceProtocal {
    final long versionID = 1L;
    String login(String name,String pwd);
}
