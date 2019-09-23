package supermarket.model;


import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;
import java.util.Map;

public class SupermarketTransformer implements TypeRegistryConfigurer {
    public Locale locale() {
        return Locale.ENGLISH;
    }

    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(ProductInfo.class,
                        (Map<String, String> row) -> {
                            String product = row.get("product");
                            Double price = Double.parseDouble(row.get("price"));

                            return new ProductInfo(product, price);
                        }
                )
        );

        typeRegistry.defineDataTableType(new DataTableType(ArticleInfo.class,
                        (Map<String, String> row) -> {
                            String product = row.get("product");
                            Double price = Double.parseDouble(row.get("quantity"));

                            return new ArticleInfo(product, price);
                        }
                )
        );
    }
}
