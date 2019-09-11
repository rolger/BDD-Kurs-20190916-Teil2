package supermarket;

import supermarket.model.Receipt;
import supermarket.model.ReceiptItem;

import java.time.LocalDate;

public class ReceiptPrinter {

    public static final int COLUMNS = 40;

    private final LocalDate receiptDate;

    public ReceiptPrinter(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String printReceipt(Receipt receipt) {
        StringBuilder result = new StringBuilder();

        printHeader(result);
        printLines(receipt, result);
        printFooter(receipt, result);

        return result.toString();
    }

    private void printHeader(StringBuilder result) {
        result.append("This is your receipt from " + receiptDate);
        emptyLine(result);
        emptyLine(result);
    }

    private void printLines(Receipt receipt, StringBuilder result) {
        result.append(withLeftPadding(0, "EUR"));
        emptyLine(result);

        for (ReceiptItem item : receipt.getItems()) {
            String name = item.getText();
            String price = item.getTotalPrice().format();

            result.append(name);
            result.append(withLeftPadding(name.length(), price));
            emptyLine(result);
        }
    }

    private void printFooter(Receipt receipt, StringBuilder result) {
        emptyLine(result);
        result.append("Total: ");
        result.append(withLeftPadding("Total: ".length(), receipt.getTotalPrice().format()));
        emptyLine(result);
    }

    private void emptyLine(StringBuilder result) {
        result.append("\n");
    }

    private String withLeftPadding(int length, String string) {
        return String.format("%1$" + (COLUMNS - length) + "s", string);
    }

}
