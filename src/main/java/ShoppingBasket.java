import java.math.BigDecimal;
import java.util.List;

public class ShoppingBasket {

    public String checkout(List<String> shoppingBasket) {
        BigDecimal totalCost =
                shoppingBasket.stream()
                        .map(this::getPrice)
                        .reduce(new BigDecimal(0), BigDecimal::add);

        BigDecimal basketCostWithOffer = totalCost.subtract(buyOneGetOneOffer(shoppingBasket)).subtract(threeForTwoOffer(shoppingBasket));

        return String.format("£%s", basketCostWithOffer.divide(new BigDecimal(100)).setScale(2));
    }

    private BigDecimal buyOneGetOneOffer(List<String> shoppingBasket) {
        long fruitCount = shoppingBasket.stream().filter(fruit -> fruit.equals("Apple")).count();
        return new BigDecimal(fruitCount / 2).multiply(getPrice("Apple"));
    }

    private BigDecimal threeForTwoOffer(List<String> shoppingBasket) {
        long fruitCount = shoppingBasket.stream().filter(fruit -> fruit.equals("Orange")).count();
        return new BigDecimal(fruitCount / 3).multiply(getPrice("Orange"));
    }

    private BigDecimal getPrice(String fruit) {
        switch (fruit) {
            case "Apple":
                return new BigDecimal(60);
            case "Orange":
                return new BigDecimal(25);
            default:
                throw new IllegalArgumentException("invalid fruit");
        }
    }
}
