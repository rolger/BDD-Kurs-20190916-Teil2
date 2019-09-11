package supermarket.model;

public class Product {
    private final ProductCode code;
    private final String name;
    private final Money basePrice;

    public Product(ProductCode number, String name, Money price) {
        this.code = number;
        this.name = name;
        this.basePrice = price;
    }

    public ProductCode getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Money getBasePrice() {
        return basePrice;
    }

}
