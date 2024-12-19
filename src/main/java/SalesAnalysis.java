import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SalesAnalysis {
    private static final int BLOCKS_COUNT = 8;

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Sales Analysis");
        conf.set("mapreduce.input.fileinputformat.split.maxsize", "32000000");
        conf.set("mapreduce.input.fileinputformat.split.minsize", "10000000");
        conf.set("dfs.blocksize", "32000000");
        conf.set("mapreduce.task.timeout", "1200000");
        conf.set("mapreduce.map.maxattempts", "5");

        conf.set("mapreduce.map.memory.mb", "4096");
        conf.set("mapreduce.reduce.memory.mb", "8192");

        conf.set("mapreduce.map.java.opts", "-Xmx3072m");
        conf.set("mapreduce.reduce.java.opts", "-Xmx6144m");

        job.setJarByClass(SalesAnalysis.class);
        job.setMapperClass(SalesMapper.class);
        job.setReducerClass(SalesReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        for (int i = 0; i < BLOCKS_COUNT; i++) {
            FileInputFormat.addInputPath(job, new Path("/user/transactions/" + i + ".csv"));
        }

        FileOutputFormat.setOutputPath(job, new Path("/user/results/"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}