package supermarket.model;

public class ArticleInfo {
    private String product;
    private Double quantity;

    public ArticleInfo(String product, Double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public Double getQuantity() {
        return quantity;
    }

}
