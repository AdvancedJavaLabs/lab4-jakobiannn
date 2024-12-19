import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SalesMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Пропуск заголовка CSV
        if (key.get() == 0 && value.toString().contains("transaction_id")) {
            return;
        }

        String[] fields = value.toString().split(",");
        if (fields.length == 5) {
            String category = fields[2]; // категория
            double price = Double.parseDouble(fields[3]); // цена
            int quantity = Integer.parseInt(fields[4]); // количество
            double totalPrice = price * quantity; // выручка

            context.write(new Text(category), new Text(totalPrice + "," + quantity));
        }
    }
}