package supermarket.model;

public class ProductInfo {
    private String product;
    private final String unit;
    private Double price;

    public ProductInfo(String product, String unit, Double price) {
        this.product = product;
        this.unit = unit;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public Unit asUnit() {
        switch (unit) {
            case "kg":
                return Unit.KG;
            default:
                return Unit.PIECE;
        }
    }
}
