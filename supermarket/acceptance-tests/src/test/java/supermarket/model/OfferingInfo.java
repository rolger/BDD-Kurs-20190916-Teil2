package supermarket.model;

public class OfferingInfo {
    private String product;
    private String discount;

    public OfferingInfo(String product, String discount) {
        this.product = product;
        this.discount = discount;
    }

    public String getProduct() {
        return product;
    }

    public String getDiscount() {
        return discount;
    }

}
