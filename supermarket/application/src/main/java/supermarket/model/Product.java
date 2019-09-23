package supermarket.model;

public class Product {
    private final ProductCode code;
    private final String name;
    private final Money basePrice;
    private final Unit unit;

    public Product(ProductCode number, String name, Money basePrice) {
        this(number, name, Unit.PIECE, basePrice);
    }

    public Product(ProductCode number, String name, Unit unit, Money basePrice) {
        this.code = number;
        this.name = name;
        this.unit = unit;
        this.basePrice = basePrice;
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

    public Unit getUnit() {
        return unit;
    }
}
