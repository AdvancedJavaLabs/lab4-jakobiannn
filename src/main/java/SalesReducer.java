import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SalesReducer extends Reducer<Text, Text, Text, Text> {
    private final List<CategoryData> categoryDataList = new ArrayList<>();
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double totalRevenue = 0;
        int totalQuantity = 0;

        for (Text value : values) {
            String[] parts = value.toString().split(",");
            double revenue = Double.parseDouble(parts[0]);
            int quantity = Integer.parseInt(parts[1]);

            totalRevenue += revenue;
            totalQuantity += quantity;
        }

        categoryDataList.add(new CategoryData(key.toString(), totalRevenue, totalQuantity));
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        categoryDataList.sort(Comparator.comparingDouble(CategoryData::getRevenue).reversed());
        context.write(new Text("Category,Revenue,Quantity"), null);
        for (CategoryData data : categoryDataList) {
            String formattedRevenue = DECIMAL_FORMAT.format(data.getRevenue());
            String line = data.getCategory() + "," + formattedRevenue + "," + data.getQuantity();
            context.write(new Text(line), null);
        }
    }
}