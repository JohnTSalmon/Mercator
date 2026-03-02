package po;

import com.mercator.CommonPageObject;
import com.mercator.Target;

public class Login extends CommonPageObject {

    // These could all be "data_test" but use other Targets also, to demonstrate flexibility of the framework
    private final Target EMAIL = data_test("username");
    private final Target PASS = id("password");
    private final Target LOGIN = name("login-button");

    public void login(String user, String password) {
        focus(EMAIL);
        compose(user);
        focus(PASS).compose(password);
        focus(LOGIN).click();
        // This will get around the System and Google pop up for the password breach if present
       // robot.keyPress(KeyEvent.VK_ENTER);

//        probe(MULTIPLE_SESSIONS)
//                .focus(LOGIN).click()


    }
}
