package hdfs.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by yongz on 2018/2/20.
 */
public class ClientDemo {
    private static Logger logger = LoggerFactory.getLogger(ClientDemo.class);
    private FileSystem client;

    @Before
    public void init() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        configuration.set("dfs.blocksize", "32m");
        //user必须是linux用户
        client = FileSystem.get(new URI("hdfs://vm1.zain.cn:9000/"), configuration, "zain");
        logger.info("init success");
    }

    //上传
    @Test
    public void upload() throws Exception {
        client.copyFromLocalFile(new Path("d:/原.xls"), new Path("/zain_demo/src.xls"));
        client.close();
        logger.info("upload success");
    }

    //上传一个stream
    @Test
    public void uploadStream() throws Exception {
        FSDataOutputStream out = client.create(new Path("/access.txt"), true);
        out.write("adsassssssssssss".getBytes());
        out.write("bbbbbbbbbbbb".getBytes());
        out.flush();
        out.close();
        client.close();
        logger.info("uploadStream success");
    }

    //下载
    @Test
    public void download() {
        //操作本地win有权限问题,故需要useRawLocalFileSystem使用java库写文件，或者本地配置hadoop环境变量
        try {
            client.copyToLocalFile(false, new Path("/zain_demo/src.xls"), new Path("d:/原1.xls"), true);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("download success");
    }

    //创建目录
    @Test
    public void mkdir() {
        //fs.permissions.umask-mode 为022，覆盖组和其他人的w权限 core-site.xml
        try {
            client.mkdirs(new Path("/zain_demo/mk"), new FsPermission((short) 777));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("mkdir success");
    }

    //删除目录
    @Test
    public void delDir() {
        try {
            client.delete(new Path("/zain_demo"), true); //recursive 递归删除
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("delDir success");
    }

    //rename目录
    @Test
    public void renameDir() {
        try {
            client.rename(new Path("/zain"), new Path("/zain01"));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("rename success");
    }

    //list目录,递归查询所有文件
    @Test
    public void listDir() {
        //迭代器需要在next时才取数据
        try {
            RemoteIterator<LocatedFileStatus> listFiles = client.listFiles(new Path("/"), true);
            while (listFiles.hasNext()) {
                LocatedFileStatus file = listFiles.next();
                logger.info("最近访问时间,{}", file.getBlockSize());
                logger.info("文件所属组,{}", file.getGroup());
                logger.info("文件大小：{}，修改时间：{} ，所有者：{} ", file.getLen(), file.getModificationTime(), file.getOwner());
                logger.info("副本：{}，块位置：{} ，全路径：{} ", file.getReplication(), Arrays.toString(file.getBlockLocations()), file.getPath());
                logger.info("文件权限,{}", file.getPermission());
                logger.info("--------------------------------------");
            }


            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("listDir success");
    }

    //list目录,查询文件目录,返回集合
    @Test
    public void listDir2() {
        //迭代器需要在next时才取数据
        try {
            FileStatus[] fileStatuses = client.listStatus(new Path("/"));//返回对象
            for (FileStatus fs : fileStatuses) {
                logger.info("path：{}" ,fs.getPath());
                logger.info("是否是目录：{}" ,fs.isDirectory());
                logger.info("--------------------------------------");
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("listDir2 success");
    }

    //读取部分文件 0-20

    @Test
    public void partFileRead() throws Exception {
        FSDataInputStream in = client.open(new Path("/vm_hosts.txt"));
        FileOutputStream out = new FileOutputStream("d:/my_vm_hosts.txt");
        byte[] bytes = new byte[20];
        int len = 0;
        long count = 0;
        while ((len = in.read(bytes)) != -1){
            out.write(bytes);;
            count += len;
            if(count == 20){
                break;
            }
        }
        in.close();
        out.close();
        client.close();
        logger.info("partFileRead success");
    }
    //读取部分文件 20-40,尽量在一台机器上读取，故128M为长度
    @Test
    public void partFileReadFrom20() throws Exception {
        FSDataInputStream in = client.open(new Path("/vm_hosts.txt"));
        in.seek(20);
        FileOutputStream out = new FileOutputStream("d:/my_vm_hosts2.txt");
        byte[] bytes = new byte[20];
        int len = 0;
        long count = 0;
        while ((len = in.read(bytes)) != -1){
            out.write(bytes);;
            count += len;
            if(count == 20){
                break;
            }
        }
        in.close();
        out.close();
        client.close();
        logger.info("partFileRead success");
    }


}
