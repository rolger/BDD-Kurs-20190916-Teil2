package supermarket;

import org.junit.Before;
import org.junit.Test;
import supermarket.checkout.CashRegister;
import supermarket.checkout.CheckoutService;
import supermarket.model.Money;
import supermarket.model.Product;
import supermarket.model.ProductCode;
import supermarket.model.ShoppingCart;

import java.time.LocalDate;

import static org.approvaltests.Approvals.verify;
import static supermarket.model.ProductCode.buildFromBarcode;

public class SuperMarketTest {
    public static final String SHOP_NAME = "BEST SHOPPING";

    private static final ProductCode apples = buildFromBarcode("00001");
    private static final ProductCode bread = buildFromBarcode("00002");
    private static final ProductCode milk = buildFromBarcode("00003");

    private static final LocalDate TRAINING_DATE = LocalDate.of(2019, 9, 17);

    private MemoryProductCatalog catalog;
    private CheckoutService checkoutService;

    @Before
    public void setUp() {
        catalog = new MemoryProductCatalog();
        catalog.add(new Product(apples, "Apfel", new Money(0.9)));
        catalog.add(new Product(bread, "Schwarzbrot", new Money(2.49)));
        catalog.add(new Product(milk, "Milch", new Money(1.01)));

        checkoutService = new CheckoutService(SHOP_NAME, catalog, new ShoppingCart());
    }

    @Test
    public void buyOneArticle() {
        checkoutService.onBarCode(bread.getCode(), 2);

        verify(checkoutService.printReceipt(new CashRegister(catalog), TRAINING_DATE));
    }

    @Test
    public void buyThreeArticlesWithoutDiscount() {
        checkoutService.onBarCode(apples.getCode(), 0.73);
        checkoutService.onBarCode(bread.getCode(), 1);
        checkoutService.onBarCode(milk.getCode(), 2);

        verify(checkoutService.printReceipt(new CashRegister(catalog), TRAINING_DATE));
    }
}
