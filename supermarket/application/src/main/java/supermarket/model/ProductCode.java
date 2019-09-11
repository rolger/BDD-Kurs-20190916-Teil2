package supermarket.model;

import java.util.Objects;

public class ProductCode {

    private String code;

    public static ProductCode buildFromBarcode(String code) {
        return new ProductCode(code);
    }

    public ProductCode(String code) {
        if (isNullOrWhiteSpace(code))
            throw new IllegalArgumentException("The code must not be empty.");
        if (!containsJustDigits(code))
            throw new IllegalArgumentException("The code must consist only if digits but was "+ code);
        if (code.length() != 5)
            throw new IllegalArgumentException("The code must be 5 characters long but was " + code);

        this.code = code;
    }

    private boolean isNullOrWhiteSpace(String text) {
        return text == null || text.isEmpty();
    }
    private static boolean containsJustDigits(String text) {
        return text.matches("[0-9]*");
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCode that = (ProductCode) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
    
}