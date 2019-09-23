package supermarket.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Buy2PayOnly1OfferingTest {

    private final Buy2PayOnly1Offering objectUnderTest = new Buy2PayOnly1Offering();

    @Test
    public void shouldAcceptOnlyProductsWithUnitPieces() {
        assertThat(objectUnderTest.matchesWithProductUnit(Unit.KG)).isFalse();
        assertThat(objectUnderTest.matchesWithProductUnit(Unit.PIECE)).isTrue();
    }

    @Test
    public void calculatesDiscount() {
        Article article = new Article(new ProductCode("12345"), 1.0);
        assertThat(objectUnderTest.calculateDiscount(new Money(10), article)).isEqualTo(new Money(0));

        article = new Article(new ProductCode("12345"), 4.0);
        assertThat(objectUnderTest.calculateDiscount(new Money(10), article)).isEqualTo(new Money(-20));

        article = new Article(new ProductCode("12345"), 7.0);
        assertThat(objectUnderTest.calculateDiscount(new Money(10), article)).isEqualTo(new Money(-30));
    }

}
