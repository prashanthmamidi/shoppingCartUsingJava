import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ShoppingBasketTest {

    ShoppingBasket basket = new ShoppingBasket();

    @Test
    public void name() {
        List<String> shoppingBasket = Arrays.asList("Apple", "Orange");
        String result = basket.checkout(shoppingBasket);
        Assert.assertEquals(result, "£0.85");
    }

    @Test
    public void BOGOF_forApple() {
        List<String> shoppingBasket = Arrays.asList("Apple", "Apple", "Orange");
        String result = basket.checkout(shoppingBasket);
        Assert.assertEquals(result, "£0.85");
    }

    @Test
    public void threeForTwoOffer_forOrange() {
        List<String> shoppingBasket = Arrays.asList("Apple", "Orange", "Orange", "Orange");
        String result = basket.checkout(shoppingBasket);
        Assert.assertEquals(result, "£1.10");
    }
}
