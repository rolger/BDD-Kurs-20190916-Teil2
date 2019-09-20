package supermarket;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import supermarket.checkout.CashRegister;
import supermarket.checkout.CheckoutService;
import supermarket.model.Money;
import supermarket.model.Product;
import supermarket.model.ProductCode;
import supermarket.model.ShoppingCart;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static supermarket.model.ProductCode.buildFromBarcode;

public class SupermarketSteps {

    private LocalDate shoppingDate;
    private MemoryProductCatalog catalog = new MemoryProductCatalog();
    private CheckoutService checkoutService;

    @Given("My shop is called (.*)$")
    public void my_shop_is_called(String shopName) {
         checkoutService = new CheckoutService(shopName, catalog, new ShoppingCart());
    }

    @Given("^This products in my catalog$")
    public void this_Products(List<ProductInfo> productInfoList) {
        productInfoList.forEach(productInfo -> {
            catalog.add(new Product(createRandomProductCode(), productInfo.getProduct(), new Money(productInfo.getPrice())));
        });
    }

    private ProductCode createRandomProductCode() {
        return buildFromBarcode(StringUtils.leftPad(Integer.toString(new Random().nextInt(10000)), 5, "0"));
    }

    @Given("^I add (\\d+.\\d+) kg of (.*) to my cart$")
    public void i_add_kg_of_Brot_to_my_cart(Double amount, String productName) {
        checkoutService.onBarCode(getProductCode(productName), amount);
    }

    @Given("^I add (\\d+) pieces of (.*) to my cart$")
    public void i_add_pieces_of_Milch_to_my_cart(Integer amount, String productName) {
        checkoutService.onBarCode(getProductCode(productName), amount);
    }

    private String getProductCode(String productName) {
        final Product product = catalog.getProductByName(productName);
        if (product == null) {
            throw new IllegalArgumentException("Didn't find product with name: " + productName);
        }
        return product.getCode().getCode();
    }

    @When("^I checkout on (.*)$")
    public void i_checkout_on(String date) {
        this.shoppingDate = LocalDate.parse(date);
    }

    @Then("I want to see the check")
    public void i_want_to_see_the_check(String expectedReceipt) {
        final String receipt = checkoutService.printReceipt(new CashRegister(catalog), shoppingDate);

        Assertions.assertThat(receipt).isEqualToIgnoringWhitespace(expectedReceipt);
    }

}
