package supermarket.checkout;

import supermarket.model.Receipt;
import supermarket.model.ReceiptItem;
import supermarket.model.Unit;

import java.time.LocalDate;
import java.util.Locale;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.repeat;

public class ReceiptPrinter {

    public static final int COLUMNS = 40;
    public static final String TOTAL_TITLE = "Total :";

    private final LocalDate receiptDate;
    private final String shopName;

    public ReceiptPrinter(String shopName, LocalDate receiptDate) {
        this.shopName = shopName;
        this.receiptDate = receiptDate;
    }

    public String printReceipt(Receipt receipt) {
        StringBuilder receiptBuilder = new StringBuilder();

        printHeader(receiptBuilder);
        printLines(receipt, receiptBuilder);
        printFooter(receipt, receiptBuilder);

        return receiptBuilder.toString();
    }

    private void printHeader(StringBuilder receiptBuilder) {
        receiptBuilder.append(withCenterPadding(shopName));
        nextLine(receiptBuilder);
        nextLine(receiptBuilder);
        receiptBuilder.append("Your purchase from " + receiptDate);
        nextLine(receiptBuilder);
        separatorLine(receiptBuilder);
    }

    private void printLines(Receipt receipt, StringBuilder receiptBuilder) {
        receiptBuilder.append(withLeftPadding(0, "EUR"));
        nextLine(receiptBuilder);

        for (ReceiptItem item : receipt.getItems()) {
            String itemLine = formatLine(item);
            receiptBuilder.append(itemLine);
            receiptBuilder.append(withLeftPadding(itemLine.length(), item.getTotalPrice().format()));
            nextLine(receiptBuilder);
        }
    }

    private String formatLine(ReceiptItem item) {
        String itemLine = item.getText();
        if (shouldQuantityBePrinted(item)) {
            itemLine += " x ";
            itemLine += formatQuantity(item);
            itemLine += " ";
            itemLine += formatUnits(item);
        }
        return itemLine;
    }

    private boolean shouldQuantityBePrinted(ReceiptItem item) {
        return item.getQuantity() > 0 && item.getUnit() != null;
    }

    private String formatQuantity(ReceiptItem item) {
        return item.getUnit() == Unit.KG ?
                format(Locale.GERMAN, "%s", item.getQuantity()) :
                format(Locale.GERMAN, "%d", (int) item.getQuantity());
    }

    private String formatUnits(ReceiptItem item) {
        return item.getUnit() == Unit.KG ? "kg" : "";
    }

    private void printFooter(Receipt receipt, StringBuilder receiptBuilder) {
        separatorLine(receiptBuilder);
        receiptBuilder.append(TOTAL_TITLE);
        receiptBuilder.append(withLeftPadding(TOTAL_TITLE.length(), receipt.getTotalPrice().format()));
        nextLine(receiptBuilder);
    }

    private void separatorLine(StringBuilder receiptBuilder) {
        receiptBuilder.append(repeat("-", COLUMNS));
        nextLine(receiptBuilder);
    }

    private void nextLine(StringBuilder receiptBuilder) {
        receiptBuilder.append("\n");
    }

    private String withCenterPadding(String string) {
        return repeat(" ", (COLUMNS - string.length()) / 2) + string;
    }

    private String withLeftPadding(int length, String string) {
        return format("%1$" + (COLUMNS - length) + "s", string);
    }

}
