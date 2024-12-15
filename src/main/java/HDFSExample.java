import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSExample {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fs = FileSystem.get(conf);

        // Пример создания файла в HDFS
        Path file = new Path("/user/example.txt");
        if (fs.exists(file)) {
            System.out.println("File already exists");
        } else {
            fs.create(file).close();
            System.out.println("File created");
        }
        fs.close();
    }
}