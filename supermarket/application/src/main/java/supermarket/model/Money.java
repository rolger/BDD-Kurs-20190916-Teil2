package supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private final BigDecimal value;

    public Money(int value) {
        this(new BigDecimal(value));
    }

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money(double value)  {
        this(new BigDecimal(value));
    }

    public Money add(Money other) {
        return new Money(value.add(other.value));
    }

    public Money multiply(double multiplicator) {
        return new Money(value.multiply(new BigDecimal(multiplicator)));
    }

    public Money percentage(int p) {
        return new Money(value.multiply(new BigDecimal(p)).divide(new BigDecimal(100)));
    }

    public BigDecimal asBigDecimal() {
        return value;
    }

    public String format() {
        return String.format("%.2f", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
