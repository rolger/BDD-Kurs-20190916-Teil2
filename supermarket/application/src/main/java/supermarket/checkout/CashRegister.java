package supermarket.checkout;

import supermarket.MemoryProductCatalog;
import supermarket.model.*;

import java.util.List;

public class CashRegister {

    private final MemoryProductCatalog catalog;

    public CashRegister(MemoryProductCatalog catalog) {
        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
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
