import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class HadoopHDFSExample {
    private static final int BLOCKS_COUNT = 8;

    public static void main(String[] args) {
        // Укажите путь HDFS
        String hdfsUri = "hdfs://localhost:9000";

        try {
            // Настройка конфигурации Hadoop
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", hdfsUri);

            // Получение файловой системы
            FileSystem fs = FileSystem.get(conf);

            List<Path> paths = new ArrayList<>();
            for (int i = 0; i < BLOCKS_COUNT; i++) {
                paths.add(new Path("/user/transactions/" + i + ".csv"));
            }
            // Чтение данных из HDFS
            try (FSDataInputStream inputStream = fs.open(paths.get(0))) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                System.out.println("Содержимое файла:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Закрытие файловой системы
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}