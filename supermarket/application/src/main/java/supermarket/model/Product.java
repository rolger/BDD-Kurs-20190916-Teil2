package supermarket.model;

public class Product {
    private final ProductCode code;
    private final String name;
    private final Money basePrice;
    private final Unit unit;
    private Offering offering;

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

    public Offering getOffering() {
        return offering;
    }

    public void setOffering(Offering offering) {
        if (offering.matchesWithProductUnit(unit)) {
            this.offering = offering;
        }
    }

    public Money calculateDiscount(Money basePrice, Article article) {
        return offering.calculateDiscount(basePrice, article);
    }

    public boolean hasOffering() {
        return offering != null;
    }
}
