package supermarket;

import supermarket.model.Product;
import supermarket.model.ProductCode;

public interface ICatalog {

    public Product getProductByNumber(final ProductCode number);

}
