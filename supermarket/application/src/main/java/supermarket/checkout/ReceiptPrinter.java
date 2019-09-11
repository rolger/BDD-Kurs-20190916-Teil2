package supermarket.checkout;

import org.apache.commons.lang3.StringUtils;
import supermarket.model.Receipt;
import supermarket.model.ReceiptItem;

import java.time.LocalDate;

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
            String name = item.getText();
            String price = item.getTotalPrice().format();

            receiptBuilder.append(name);
            receiptBuilder.append(withLeftPadding(name.length(), price));
            nextLine(receiptBuilder);
        }
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
