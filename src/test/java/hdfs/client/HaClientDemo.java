package hdfs.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by yongz on 2018/3/12.
 */
public class HaClientDemo {
    public static void main(String[] args) throws IOException {
        //会自动加载resources目录下的文件
        Configuration conf = new Configuration();
        FileSystem fs  = FileSystem.get(conf);
        fs.copyFromLocalFile(new Path("D:/software_dev/JetBrains/IDEA2016.1.3_workspace_git/customhdfs/src/test/resources/core-site.xml"),new Path("/core-site.xml"));
        fs.close();
    }
}


