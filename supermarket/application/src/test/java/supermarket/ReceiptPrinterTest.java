package supermarket;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;
import supermarket.checkout.CashRegister;
import supermarket.model.*;

import java.time.LocalDate;

import static org.approvaltests.Approvals.verify;
import static supermarket.model.ProductCode.buildNew;

public class ReceiptPrinterTest {

    private static final ProductCode apples = buildNew("00001");
    private static final ProductCode bread = buildNew("00002");
    private static final ProductCode milk = buildNew("00003");

    private static final LocalDate TRAINING_DATE = LocalDate.of(2019, 9, 17);

    private MemoryProductCatalog catalog;

    @Before
    public void setUp() throws Exception {
        catalog = new MemoryProductCatalog();
        catalog.add(new Product(apples, "Apfel", new Money(0.9)));
        catalog.add(new Product(bread, "Schwarzbrot", new Money(2.49)));
        catalog.add(new Product(milk, "Milch", new Money(1.01)));
    }

    @Test
    public void buyOneArticle() {
        ShoppingCart aCart = new ShoppingCart();
        aCart.addArticle(bread, 2);

        Receipt r = new CashRegister(catalog).checksOutArticlesFrom(aCart);

        verify(new ReceiptPrinter(TRAINING_DATE).printReceipt(r));
    }
    @Test
    public void buyThreeArticlesWithoutDiscount() {
        ShoppingCart aCart = new ShoppingCart();
        aCart.addArticle(apples, 0.75);
        aCart.addArticle(bread, 1);
        aCart.addArticle(milk, 1);

        Receipt r = new CashRegister(catalog).checksOutArticlesFrom(aCart);

        verify(new ReceiptPrinter(TRAINING_DATE).printReceipt(r));
    }
}
