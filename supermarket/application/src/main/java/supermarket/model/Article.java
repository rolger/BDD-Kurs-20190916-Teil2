package supermarket.model;

public class Article {
    private final ProductCode id;
    private final double quantity;

    public Article(ProductCode id, double quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ProductCode getId() {
        return id;
    }

    public double getQuantity() {
        return quantity;
    }

}
