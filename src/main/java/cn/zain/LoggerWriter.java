package cn.zain;


import org.apache.log4j.Logger;

/**
 * Created by yongz on 2018/2/20.
 */
public class LoggerWriter {
    static Logger logger = Logger.getLogger(LoggerWriter.class);

    public static void main(String[] args) {
        while (true) {
            logger.info("--当前时间" + System.currentTimeMillis());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
