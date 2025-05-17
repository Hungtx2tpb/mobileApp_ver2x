package bb.defs;

//import bb.steps.CommonSteps;
import bb.steps.LoginSteps;
import bb.steps.MoneyTransferSteps;
import bb.utils.Constant;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import java.util.List;

public class MoneyTransferDefs {

    @Steps
    MoneyTransferSteps transferSteps;

    @Steps
    LoginSteps loginSteps;
//    @Steps
//    CommonSteps commonSteps;
//
//    @When("create Transfer_In_TPBank with Account {string}, Amount {string} and Message {string}")
//    public void userCreateTransferInTPB(String type, String amount, String message) {
//        String account = commonSteps.getValueFromDataFile(type, Constant.DATA_ACCOUNT);
//        transferSteps.createTransferInTPB(account, amount, message);
//    }

    @When("create Transfer_In_TPB with Account {string}, Amount {string} and Message {string}")
    public void userCreateTransferInTPBank(String account, String amount, String message) {
        transferSteps.createTransferInTPB(account, amount, message);
    }

//    @When("input Account or Phone_Number {string}")
//    public void userInputAccountNumberOrPhone(String type) {
//        String account = commonSteps.getValueFromDataFile(type, Constant.DATA_ACCOUNT);
//        transferSteps.inputAccountNumberOrPhone(account);
//    }

    @Then("verify the Error_Message display {string}")
    public void userCreateTransferInTPB(String message) {
        transferSteps.verifyTheErrorMessage(message);
        Serenity.takeScreenshot();
    }

    @When("click Confirm button on Money_Transfer page")
    public void userClickOnConfirm() {
        transferSteps.clickOnConfirm();
    }

    @When("click Schedule_Payment_Order")
    public void userClickOnSchedulePaymentOrder() {
        transferSteps.clickOnSchedulePaymentOrder();
    }

    @When("click back to chatPay button")
    public void userClickOnBackToChatPay() {
        transferSteps.clickOnBackToChatPay();
    }

    @When("select Greeting_Card {string}")
    public void userSelectsGreetingCard(String card) {
        transferSteps.selectGreetingCard(card);
    }

    @When("click Contacts button")
    public void userClickOnContacts() {
        transferSteps.clickOnContacts();
    }

    @When("click Close_Contacts button")
    public void userClickOnCloseContacts() {
        transferSteps.clickOnCloseContacts();
    }

    @When("click Telephone_Directory button")
    public void userClickOnTelephoneDirectory() {
        transferSteps.clickOnTelephoneDirectory();
    }

    @When("click remove Account or Phone_Number button")
    public void userClickOnRemoveAccountNumberOrPhone() {
        transferSteps.clickOnRemoveAccountNumberOrPhone();
    }

    @When("click contacts and select Your_Account")
    public void clickOnContactsAndSelectYourAccount(DataTable dataTable) {
        List<String> accounts = dataTable.asList(String.class);
        int size = accounts.size();
        for (int i = 1; i < size; i++) {
            transferSteps.clickOnContacts();
            transferSteps.clickOnAccountInContacts(accounts.get(i));
            if (size == 2) {
                break;
            }
        }
    }

    @Then("verify the Message_Empty display {string} in Contacts")
    public void verifyMessageEmptyInContacts(String message) {
        transferSteps.verifyMessageEmptyInContacts(message);
    }

    @Then("verify the Title_In_Contacts display")
    public void verifyTitleInContactsIsDisplayed() {
        transferSteps.verifyTitleInContactsIsDisplayed();
    }

    @Then("verify list Recent_Account display")
    public void verifyListRecentAccountIsDisplayed(DataTable dataTable) {
        List<String> accounts = dataTable.asList(String.class);
        transferSteps.verifyListRecentAccountIsDisplayed(accounts);
    }

//    @Then("verify list Recent_Account of Account_Holder {string} display")
//    public void verifyListRecentAccountIsDisplayed(String accountHolder) {
//        for (String account : commonSteps.getAccountsBlockInContacts(accountHolder)) {
//            transferSteps.verifyAccountInContactsIsDisplayed(account.replaceAll("(.{4})", "$1 ").trim());
//        }
//    }

    @When("search Account {string} in Contacts")
    public void userSearchInContacts(String message) {
        transferSteps.searchInContacts(message);
    }

    @Then("verify Account_Holder {string} display in Contacts")
    public void verifyAccountHolderInContactsIsDisplayed(String accountHolder) {
        transferSteps.verifyAccountHolderInContactsIsDisplayed(accountHolder);
    }

    @Then("verify Receiver_Name display")
    public void verifyReceiverNameIsDisplayed() {
        transferSteps.verifyReceiverNameIsDisplayed();
    }

    @Then("verify {string} field display {string}")
    public void verifyAccountNumberOrPhoneFieldEmpty(String field, String message) {
        transferSteps.verifyMoneyTransferFiled(field, message);
    }

    @When("click {string} field")
    public void userClickOnField(String field) {
        transferSteps.clickOnMoneyTransferField(field);
    }

    @Then("verify Suggested_Amount display")
    public void verifySuggestedAmountShouldBeDisplayed(DataTable dataTable) {
        List<String> amounts = dataTable.asList(String.class);
        transferSteps.verifySuggestedAmountIsDisplayed(amounts);
    }

    @Then("verify Warning_Message {string} in Amount field display")
    public void verifyWarningMessageShouldBeDisplayed(String message) {
        transferSteps.verifyWarningMessageIsDisplayed(message);
    }

    @Then("verify Continue button disabled")
    public void verifyContinueButtonShouldBeDisplayed() {
        transferSteps.verifyContinueButtonIsDisable();
    }

    @Then("verify Favourite_Account correct")
    public void verifyFavouriteAccountShouldBeCorrected() {
        transferSteps.verifyFavouriteAccountIsCorrected();
    }

    @When("click Change_Account button")
    public void userClickOnChangeAccount() {
        transferSteps.clickOnChangeAccount();
    }

    @When("create Interbank_Transfer with Account {string} from Contact_List, Amount {string} and Message {string}")
    public void userCreateInterbankTransferFromContact(String accountName, String amount, String message) {
        transferSteps.createTransferInterbankFromContact(accountName, amount, message);
        transferSteps.clickOnContinueButton();
    }

    @When("select Source_Account {string}")
    public void userSelectSourceAccount(String sourceAccount) {
        transferSteps.selectSourceAccount(sourceAccount);
    }

    @When("create Interbank_Transfer with bankName {string}, Account {string}, Amount {string} and Message {string}")
    public void userCreateInterbankTransfer(String bankName, String account, String amount, String message) {
        transferSteps.createTransferInterbank(bankName, account, amount, message);
        transferSteps.clickOnContinueButton();
    }

    @When("create Interbank_Transfer with bankName {string}, Account {string}, Amount {string} and Message {string}, isCitad {string}")
    public void userCreateInterbankTransfer(String bankName, String account, String amount, String message, String isCitad) {
        transferSteps.createTransferInterbank(bankName, account, amount, message, isCitad);
        transferSteps.clickOnContinueButton();
    }

    @Then("verify the Inline_Error_Message {string} display")
    public void verifyErrorMessage(String message) {
        transferSteps.verifyErrorMessage(message);
    }

    // ATM
    @When("create Atm_Transfer with Atm_Number {string}, Amount {string} and Message {string}")
    public void userCreateATMTransfer(String atmNumber, String amount, String message) {
        transferSteps.createATMTransfer(atmNumber, amount, message);
        transferSteps.clickOnContinueButton();
    }

    @When("create Atm_Transfer from contact with Atm_Number {string}, Amount {string} and Message {string}")
    public void userCreateATMTransferFromContact(String atmNumber, String amount, String message) {
        transferSteps.createATMTransferFromContact(atmNumber, amount, message);
        transferSteps.clickOnContinueButton();
    }

    @Then("verify Fund_Transfer page display")
    public void verifyFundTransferPageShouldBeDisplayed() {
        transferSteps.verifyFundTransferPageIsDisplay();
    }

    @When("input Amount {string} on Money_Transfer page")
    public void inputAmount(String amount) {
        transferSteps.inputAmount(amount);
    }

    @Given("user opens Ebank app")
    public void userOpenEbankApp(){
        loginSteps.clickOnLoginButton();
    }
}