package bb.steps;

//import bb.pages.CommonPage;
import bb.pages.MoneyTransferPage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MoneyTransferSteps extends UIInteractionSteps {

    MoneyTransferPage moneyTransferPage;
//    CommonPage commonPage;

    @Step
    public void inputAccountNumberOrPhone(String account) {
        moneyTransferPage.inputAccountNumberOrPhone(account);
    }

    @Step
    public void clickOnConfirm() {
        moneyTransferPage.clickOnConfirm();
    }

    @Step
    public void inputAmount(String amount) {
        moneyTransferPage.inputAmount(amount);
    }

    @Step
    public void inputMessage(String message) {
        moneyTransferPage.inputMessage(message);
    }

    @Step
    public void waitForReceiverNameDisplayed() {
        moneyTransferPage.waitForReciverNameDisplayed();
    }

//    @Step
//    public void clickOnContinue() {
//        commonPage.clickOnContinue();
//    }

    @Step
    public void createTransferInTPB(String account, String amount, String message) {
        inputAccountNumberOrPhone(account);
        waitForReceiverNameDisplayed();
        inputAmount(amount);
        moneyTransferPage.hideKeyboard();
        inputMessage(message);
        moneyTransferPage.hideKeyboard();
    }

    @Step
    public void verifyTheErrorMessage(String message) {
        assertThat(moneyTransferPage.getDialogMessage(), is(message));
    }

    @Step
    public void clickOnPostCard() {
        moneyTransferPage.clickOnPostCard();
    }

    @Step
    public void selectGreetingCard(String card) {
        clickOnPostCard();
        moneyTransferPage.clickOnStickerIcon();
        moneyTransferPage.selectGreetingCard(card);
        moneyTransferPage.clickOnDone();
    }

    @Step
    public void clickOnSchedulePaymentOrder() {
        moneyTransferPage.clickOnSchedulePaymentOrder();
    }

    @Step
    public void clickOnBackToChatPay() {
        moneyTransferPage.clickOnBackToChatPay();
    }

    @Step
    public void clickOnContacts() {
        moneyTransferPage.clickOnContacts();
    }

    @Step
    public void verifyMessageEmptyInContacts(String message) {
        assertThat(moneyTransferPage.getMessageEmptyInContacts(), is(message));
    }

    @Step
    public void clickOnAccountInContacts(String account) {
        moneyTransferPage.clickOnYourAccountInContacts(account);
    }

    @Step
    public void verifyTitleInContactsIsDisplayed() {
        assertThat(moneyTransferPage.verifyTitleInContacts(), is(true));
    }

    @Step
    public void clickOnCloseContacts() {
        moneyTransferPage.clickOnCloseContacts();
    }

    @Step
    public void verifyAccountInContactsIsDisplayed(String account) {
        assertThat(moneyTransferPage.verifyAccountInContacts(account), is(true));
    }

    @Step
    public void verifyAccountHolderInContactsIsDisplayed(String accountHolder) {
        assertThat(moneyTransferPage.verifyAccountHolderInContacts(accountHolder), is(true));
    }

    @Step
    public void searchInContacts(String account) {
        moneyTransferPage.searchInContacts(account);
    }

    @Step
    public void verifyListRecentAccountIsDisplayed(List<String> accounts) {
        assertThat(moneyTransferPage.getListRecentAccount(), is(accounts));
    }

    @Step
    public void clickOnTelephoneDirectory() {
        moneyTransferPage.clickOnTelephoneDirectory();
    }

    @Step
    public void verifyReceiverNameIsDisplayed() {
        assertThat(moneyTransferPage.verifyReceiverNameIsDisplayed(), is(true));
    }

    @Step
    public void clickOnRemoveAccountNumberOrPhone() {
        moneyTransferPage.clickOnRemoveAccountNumberOrPhone();
    }

    @Step
    public void verifyMoneyTransferFiled(String field, String value) {
        assertThat(moneyTransferPage.getValueMoneyTransferField(field), is(value));
    }

    @Step
    public void verifySuggestedAmountIsDisplayed(List<String> listAmount) {
        assertThat(moneyTransferPage.getSuggestedAmount(), is(listAmount));
    }

    @Step
    public void clickOnMoneyTransferField(String field) {
        moneyTransferPage.clickOnMoneyTransferField(field);
    }

    @Step
    public void verifyWarningMessageIsDisplayed(String message) {
        assertThat(moneyTransferPage.getWarningMessage(), is(message));
    }

    @Step
    public void verifyContinueButtonIsDisable() {
        assertThat(moneyTransferPage.checkContinueButtonIsEnable(), is(false));
    }

    @Step
    public void verifyFavouriteAccountIsCorrected() {
        String account = Serenity.sessionVariableCalled("beneficiaryAccount");
        assertThat(moneyTransferPage.getFavouriteAccount(), is(account));
    }

    @Step
    public void clickOnChangeAccount() {
        moneyTransferPage.clickOnChangeAccount();
    }

    @Step
    public void selectSourceAccount(String sourceAccount) {
        moneyTransferPage.selectSourceAccount(sourceAccount);
    }

    @Step
    public void createTransferInterbankFromContact(String accountName, String amount, String message) {
        moneyTransferPage.clickOnContactIcon();
        moneyTransferPage.chooseAccountFromContact(accountName);
        moneyTransferPage.inputAmount(amount);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputMessage(message);
        moneyTransferPage.hideKeyboard();
    }

    @Step
    public void createTransferInterbank(String bankName, String account, String amount, String message) {
        moneyTransferPage.clickOnTabInterbank();
        moneyTransferPage.clickOnButtonSelectBank();
        moneyTransferPage.clickOnButtonSearchBank(bankName);
        moneyTransferPage.clickOnBankByName(bankName);
        moneyTransferPage.inputAccount(account);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputAmount(amount);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputMessage(message);
        moneyTransferPage.hideKeyboard();
    }

    public void createTransferInterbank(String bankName, String account, String amount, String message, String isCitad) {
        moneyTransferPage.clickOnTabInterbank();
        moneyTransferPage.clickOnButtonSelectBank();
        moneyTransferPage.clickOnButtonSearchBank(bankName);
        moneyTransferPage.clickOnBankByName(bankName);
        moneyTransferPage.inputAccount(account);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.clickButtonStandardMoneyTransfer();
        moneyTransferPage.selectProvince("HÀ NỘI");
        moneyTransferPage.inputBeneficiaryName("Nguyen Van A");
        moneyTransferPage.inputAmount(amount);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputMessage(message);
        moneyTransferPage.hideKeyboard();
    }

    @Step
    public void clickOnContinueButton() {
        moneyTransferPage.clickOnContinue();
    }

    @Step
    public void verifyErrorMessage(String message) {
        assertThat(moneyTransferPage.getDialogMessage().contains(message), is(true));
    }

    // ATM
    @Step
    public void createATMTransfer(String atmNumber, String amount, String message) {
        moneyTransferPage.clickOnTabATMCard();
        moneyTransferPage.inputATMNumber(atmNumber, false);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputAmount(amount);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputMessage(message);
        moneyTransferPage.hideKeyboard();
    }

    @Step
    public void createATMTransferFromContact(String atmNumber, String amount, String message) {
        moneyTransferPage.clickOnTabATMCard();
        moneyTransferPage.clickOnContacts();
        moneyTransferPage.inputATMNumber(atmNumber, true);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputAmount(amount);
        moneyTransferPage.hideKeyboard();
        moneyTransferPage.inputMessage(message);
        moneyTransferPage.hideKeyboard();
    }

    @Step
    public void verifyFundTransferPageIsDisplay() {
        assertThat(moneyTransferPage.getStatusFundTransferTitle(), is(true));
    }
}