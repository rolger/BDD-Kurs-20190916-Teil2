package supermarket.model;

import supermarket.model.Product;
import supermarket.model.ProductCode;

public interface ICatalog {

    Product getProductByNumber(final ProductCode number);

}
