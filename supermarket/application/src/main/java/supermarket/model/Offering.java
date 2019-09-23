package supermarket.model;

public interface Offering {
    Money calculateDiscount(Money basePrice, Article article);

    String getText();

    boolean matchesWithProductUnit(Unit productUnit);
}
