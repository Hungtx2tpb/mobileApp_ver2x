package bb.steps;

import bb.pages.LoginPage;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.annotations.Step;

public class LoginSteps extends UIInteractionSteps {

    LoginPage loginPage;

    @Step
    public void clickOnLoginButton(){
        loginPage.clickOnLoginButton();
    }

}
