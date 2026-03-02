package steps;

import dto.Person;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import po.Login;
import po.Products;
import java.util.Map;

public class SauceDemo {

    Products products = new Products();
    Login loginPO = new Login();

    @DataTableType
    public dto.Login decodeLogin(Map<String, String> row) {
        return dto.Login.decode(row);
    }

    @DataTableType
    public dto.Person decode_Person(Map<String, String> row) {
        return dto.Person.decode(row);
    }

    @Given("I open a browser")
    public void iOpenABrowser() {
        loginPO.open();
    }

    @Given("I open the URL :")
    public void iOpenTheURL(String url) {
        loginPO.go(url);
    }

    @And("I login with the following credentials")
    public void iLoginWithTheFollowingCredentials(dto.Login login) {
        loginPO.login(login.getUser(),login.getPassword());
    }

    @When("I select the lowest price item")
    public void iSelectTheLowestPriceItem() {
        products.selectItem(products.findLowestPriceItem());
    }

    @And("I complete the purchase with the following details:")
    public void iCompleteThePurchaseWithTheFollowingDetails(Person person) {
        products.completePurchase(person);
    }

    @Then("I should see the following confirmation message:")
    public void iShouldSeeTheFollowingConfirmationMessage(String message) {
        products.verifyConfirmationMessage(message);
    }
}
