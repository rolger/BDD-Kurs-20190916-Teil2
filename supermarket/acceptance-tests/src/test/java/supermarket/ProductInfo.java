package supermarket;

public class ProductInfo {
    private String product;
    private Double price;

    public ProductInfo(String product, Double price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }

}
