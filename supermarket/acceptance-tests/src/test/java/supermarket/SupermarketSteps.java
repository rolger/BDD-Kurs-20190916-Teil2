package supermarket;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import supermarket.checkout.CashRegister;
import supermarket.checkout.CheckoutService;
import supermarket.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.assertj.core.api.Assertions.assertThat;
import static supermarket.model.ProductCode.buildFromBarcode;

public class SupermarketSteps {

    private LocalDate shoppingDate;
    private MemoryProductCatalog catalog = new MemoryProductCatalog();
    private CheckoutService checkoutService;

    @Given("My shop (.*) sells this products")
    public void my_shop_sells_this_products(String shopName, List<ProductInfo> productInfoList) {
        checkoutService = new CheckoutService(shopName, catalog, new ShoppingCart());
        productInfoList.forEach(productInfo ->
                catalog.add(new Product(createRandomProductCode(), productInfo.getProduct(), new Money(productInfo.getPrice())))
        );
    }

    @Given("My shop (.*) offers this discounts")
    public void my_shop_offers_this_discounts(String shopName, io.cucumber.datatable.DataTable dataTable) {
    }

    private ProductCode createRandomProductCode() {
        return buildFromBarcode(leftPad(Integer.toString(new Random().nextInt(10000)), 5, "0"));
    }

    @Given("^I add (\\d+.\\d+) kg of (.*) to my cart$")
    public void i_add_kg_to_my_cart(Double quantity, String productName) {
        checkoutService.onBarCode(getProductCode(productName), quantity);
    }

    @Given("^I add (\\d+) pieces of (.*) to my cart$")
    public void i_add_pieces_to_my_cart(Integer quantity, String productName) {
        checkoutService.onBarCode(getProductCode(productName), quantity);
    }

    @Given("I add to my cart")
    public void i_add_to_my_cart(List<ArticleInfo> articleInfoList) {
        articleInfoList.forEach(articleInfo ->
                checkoutService.onBarCode(getProductCode(articleInfo.getProduct()), articleInfo.getQuantity())
        );
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

        assertThat(receipt).isEqualToIgnoringWhitespace(expectedReceipt);
    }

}
