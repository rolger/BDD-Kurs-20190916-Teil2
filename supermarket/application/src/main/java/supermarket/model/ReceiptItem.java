package supermarket.model;

public class ReceiptItem {
    private final String text;
    private final double quantity;
    private final Money totalPrice;
    private Unit unit;

    public ReceiptItem(String text, Unit unit, double quantity, Money totalPrice) {
        this.text = text;
        this.quantity = quantity;
        this.unit = unit;
        this.totalPrice = totalPrice;
    }

    public String getText() {
        return text;
    }

    public double getQuantity() {
        return quantity;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Unit getUnit() {
        return unit;
    }
}
