public class CategoryData {
    private final String category;
    private final double revenue;
    private final int quantity;

    public CategoryData(String category, double revenue, int quantity) {
        this.category = category;
        this.revenue = revenue;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public double getRevenue() {
        return revenue;
    }

    public int getQuantity() {
        return quantity;
    }
}
