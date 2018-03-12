package cn.zain;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

/**
 * Created by yongz on 2018/2/24.
 */
public class DownM3u8 {
    //    http://ginocdn.bybzj.com:8092/wuma/20171105/19208/550kb/hls/index.m3u8
//    http://ginocdn.bybzj.com:8092/wuma/20171105/19184/550kb/hls/index.m3u8
//    http://ginocdn.bybzj.com:8092/guochan/20171231/201712310256/550kb/hls/index.m3u8
    static String url = "http://ginocdn.bybzj.com:8092/wuma/20171201/hd_heyzo-1594/550kb/hls/"; //狂操墨西哥25岁极致胸部 ～[原版无码]


    static String urlTail = "index.m3u8";

    public static void main(String[] args) throws IOException {
        String result = HttpTools.getData(url + urlTail);
        String[] datas = result.split("\n");
        int total = (datas.length - 5) / 2;
        System.out.println("文件数约(个):" + total);
        File file = new File("E://m3u8/t.m3u8");
        FileOutputStream outputStream = new FileOutputStream(file);

        int finished = 0;
        for (String str : datas) {

            if (StringUtils.isBlank(str) || !str.endsWith(".ts")) {
                continue;
            }
            System.out.println("下载 :" + str);
            byte[] dataBytes = null;
            while (null == dataBytes) {
                dataBytes = HttpTools.getDataBytes(url + str);
                if (null == dataBytes) {
                    System.out.println("失败重试");
                }

            }
            finished++;
            System.out.println("下载完成 :" + str + " 大小kb：" + dataBytes.length / 1024 + " 总进度:" + finished + "/" + total);
            outputStream.write(dataBytes);
            outputStream.flush();
        }

        System.out.println("完成 :");
    }
}
