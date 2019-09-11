package supermarket.checkout;

import supermarket.model.*;

import java.util.List;

public class CashRegister {

    private final ICatalog catalog;

    public CashRegister(ICatalog catalog) {
        this.catalog = catalog;
    }

    Receipt checkoutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();

        List<Article> articles = theCart.getArticles();
        for (Article article : articles) {
            final Product product = catalog.getProductByNumber(article.getId());
            Money price = product.getBasePrice().multiply(article.getQuantity());
            receipt.addItem(product, article.getQuantity(), price);
        }

        return receipt;
    }

}
