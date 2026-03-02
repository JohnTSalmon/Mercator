package po;


import com.mercator.CommonPageObject;
import com.mercator.Target;
import dto.Person;
import utils.ListUtils;

import java.util.List;

import static com.mercator.HTML.Tag.H2;
import static utils.ListUtils.LIST_OPERATION.LOWEST;

public class Products extends CommonPageObject {

    private final Target HEADER_CONTAINER = className("app_logo");
    private final String HEADER = "Swag Labs";
    private final Target PRICE_CONTAINER = data_test("inventory-item-price");
    private final Target CART = data_test("shopping-cart-badge");
    private final Target CHECKOUT = id("checkout");
    private final Target FIRST_NAME = placeHolder("First Name");
    private final Target LAST_NAME = placeHolder("Last Name");
    private final Target POST_CODE = name("postalCode");
    private final Target CONTINUE = data_test("continue");
    private final Target FINISH = data_test("finish");
    private final String CONFIRMATION_MESSAGE =  "Thank you for your order!";

    public String findLowestPriceItem() {
        // Check we're on the page
        focus(HEADER_CONTAINER).contains(HEADER);
        List<String> prices = collect(PRICE_CONTAINER).collectionToList();
        String lowest = ListUtils.getListValue(prices, LOWEST);
        return lowest;
    }

    public void selectItem(String price) {
        collect(PRICE_CONTAINER).choose(price);
        // The "Add to cart" button is the following sibling
        traverse();
        click();
    }

    public void completePurchase(Person person) {
        focus(CART).click();
        focus(CHECKOUT).click();
        focus(FIRST_NAME).compose(person.getFirstName());
        focus(LAST_NAME).compose(person.getLastName());
        focus(POST_CODE).compose(person.getPostCode());
        focus(CONTINUE).click();
        focus(FINISH).click();
    }

    public void verifyConfirmationMessage(String message) {
        focus(tagWithText(H2, message));
    }
}

