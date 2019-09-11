package supermarket.checkout;

import supermarket.model.ICatalog;
import supermarket.model.Product;
import supermarket.model.ProductCode;
import supermarket.model.Receipt;
import supermarket.model.ShoppingCart;

import java.time.LocalDate;

import static java.lang.String.format;

public class CheckoutService {
    private final String shopName;
    private final ICatalog catalog;
    private final ShoppingCart shoppingCart;

    public CheckoutService(String shopName, ICatalog catalog, ShoppingCart theCart) {
        this.shopName = shopName;
        this.catalog = catalog;
        this.shoppingCart = theCart;
    }

    public void onBarCode(String barcode, double quantity) {
        ProductCode code = new ProductCode(barcode);

        checkIfProductExists(barcode, code);
        shoppingCart.addArticle(code, quantity);
    }

    private void checkIfProductExists(String barcode, ProductCode code) {
        final Product product = catalog.getProductByNumber(code);
        if (product == null) {
            throw new IllegalArgumentException(format("The product with code %s doesn't exist in the catalog.", barcode));
        }
    }

    public String printReceipt(final CashRegister cashRegister, LocalDate receiptDate) {
        Receipt receipt = cashRegister.checkoutArticlesFrom(shoppingCart);
        return new ReceiptPrinter(shopName, receiptDate).printReceipt(receipt);
    }

}
