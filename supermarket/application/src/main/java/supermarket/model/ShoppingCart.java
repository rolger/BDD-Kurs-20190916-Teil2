package supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private final List<Article> articles = new ArrayList<>();

	public void addArticle(ProductCode code, double quantity) {
		articles.add(new Article(code, quantity));
	}

	public List<Article> getArticles() {
		return new ArrayList<>(articles);
	}

}
