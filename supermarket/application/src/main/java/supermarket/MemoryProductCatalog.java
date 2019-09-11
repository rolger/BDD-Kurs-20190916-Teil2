package supermarket;

import supermarket.model.Product;
import supermarket.model.ProductCode;

import java.util.Collection;
import java.util.HashMap;


public class MemoryProductCatalog implements ICatalog {
    private HashMap<ProductCode, Product> products = new HashMap<>();

    public Product getProductByNumber(final ProductCode number) {
        if (number == null) {
            throw new IllegalArgumentException("The ProductCode must not be null.");
        }
        return products.get(number);
    }

    public void add(Product product) {
        products.put(product.getCode(), product);
    }

    public void addAll(Collection<Product> additionalProducts) {
        additionalProducts.forEach(this::add);
    }

}