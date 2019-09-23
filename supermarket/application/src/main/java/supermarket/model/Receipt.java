package supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> items = new ArrayList<>();

    public Money getTotalPrice() {
        Money total = new Money(0);
        for (ReceiptItem item : items) {
            total = item.getTotalPrice().add(total);
        }
        return total;
    }

    public void addItem(Product p, double quantity, Money totalPrice) {
        items.add(new ReceiptItem(p.getName(), p.getUnit(), quantity, totalPrice));
    }

    public List<ReceiptItem> getItems() {
        return new ArrayList<>(items);
    }

}
