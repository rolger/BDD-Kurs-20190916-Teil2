package supermarket.model;

public class Buy2PayOnly1Offering implements Offering {

    @Override
    public Money calculateDiscount(Money basePrice, Article article) {
        int pieces = (int)article.getQuantity();
        int mod = pieces /2;
        return basePrice.multiply(mod).multiply(-1);
    }

    @Override
    public String getText() {
        return "  buy 2 pay 1";
    }

    @Override
    public boolean matchesWithProductUnit(Unit productUnit) {
        return Unit.PIECE.equals(productUnit);
    }

}
