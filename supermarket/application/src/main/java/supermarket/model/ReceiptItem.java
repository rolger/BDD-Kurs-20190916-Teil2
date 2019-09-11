package supermarket.model;

public class ReceiptItem {
    private final String text;
    private final double quantity;
    private final Money totalPrice;

    public ReceiptItem(String text, double quantity, Money totalPrice) {
        this.text = text;
        this.quantity = quantity;
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

 }
