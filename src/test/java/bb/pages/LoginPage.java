package bb.pages;

import bb.common.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends BasePage {
    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'btn_login_first')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnLogin;

    public void clickOnLoginButton(){
        waitAndClickElement(btnLogin);
    }
}
