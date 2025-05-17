package bb.pages;

import bb.common.BasePage;
import bb.utils.Utilities;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MoneyTransferPage extends BasePage {

    @AndroidFindBy(xpath = "//*[contains(@content-desc, 'Chuyển tiền')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtFundTransferTitle;

    @AndroidFindBy(xpath = "//*[@text = 'Số tài khoản hoặc số điện thoại']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtAccountNumberOrPhone;

    @AndroidFindBy(xpath = "//*[@text = 'Số tài khoản hoặc số điện thoại']/following-sibling::android.widget.LinearLayout/child::android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtInputAccountNumberOrPhone;

    @AndroidFindBy(xpath = "//*[@text = 'Số tiền']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtAmount;

    @AndroidFindBy(xpath = "//*[@text = 'Số tiền']/following-sibling::android.widget.LinearLayout/child::android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtInputAmount;

    @AndroidFindBy(xpath = "//*[@text = 'Nội dung chuyển tiền']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtMessage;

    @AndroidFindBy(xpath = "//*[@text = 'Nội dung chuyển tiền']/following-sibling::android.widget.LinearLayout/child::android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtInputMessage;

    @AndroidFindBy(xpath = "//*[@text = 'Tên người nhận']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtReceiverName;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='tpb.bb.hydrobank:id/btn_next']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnContinue;

    @AndroidFindBy(xpath = "//*[@text = 'XÁC NHẬN' or @text='Xác nhận' or @content-desc = 'Xác Nhận']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnConfirm;

    @AndroidFindBy(xpath = "//*[@resource-id = 'tpb.bb.hydrobank:id/dialogMessage']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtDialogMessage;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'imgPostCard') or contains(@resource-id, 'txtViewPostCard')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade imgPostCard;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id, 'tpb.bb.hydrobank:id/stickerIcon')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade imgStickerIcon;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'btnDone')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnDone;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'coachMarkMessage')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtCoachMarkMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Đặt lịch chuyển tiền']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtSchedulePaymentOrder;

    @AndroidFindBy(xpath = "//*[@text = 'Về giao diện ChatPay' or contains(@resource-id, 'btn_chaypay')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnChatPay;

    @AndroidFindBy(xpath = "//*[@resource-id = 'tpb.bb.hydrobank:id/icRight']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade imgContacts;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'tvMessageEmpty') or contains(@resource-id, 'no_phone_contact')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtMessageEmpty;

    @AndroidFindBy(xpath = "//*[@text = 'Chọn tài khoản từ danh bạ']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtChooseAccountFromContacts;

    @AndroidFindBy(xpath = "//*[contains(@resource-id , 'rcv_own_account')]//android.widget.TextView[contains(@resource-id , 'itemOwnAccountTpBankAccountNumber')]")
    @iOSXCUITFindBy(xpath = "")
    List<WebElementFacade> txtListYourAccount;

    @AndroidFindBy(xpath = "//*[contains(@resource-id , 'img_close_dialog')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade imgCloseContacts;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'rcv_recent_account')]//*[@resource-id = 'tpb.bb.hydrobank:id/txt_number_account']")
    @iOSXCUITFindBy(xpath = "")
    List<WebElementFacade> txtListRecentAccount;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'edt_search')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtSearchInContacts;

    @AndroidFindBy(xpath = "//*[@text='DANH BẠ ĐIỆN THOẠI']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnTelephoneDirectory;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'ic_remove')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnRemoveAccountNumberOrPhone;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'rv_suggest')]//android.widget.TextView")
    @iOSXCUITFindBy(xpath = "")
    List<WebElementFacade> txtSuggestedAmount;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'tvInputWarning')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtWarningMessage;

    @AndroidFindBy(xpath = "(//*[contains(@resource-id, 'number_account')])[1]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtFavouriteAccount;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'change_account')]")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnChangeAccount;

    public static String android_yourAccount = "//*[contains(@resource-id , 'itemOwnAccountTpBankAccountNumber') and @text = '%s']";
    public static String android_accountHolderInContacts = "//android.widget.TextView[contains(@resource-id, 'tv_name') and @text = '%s']";
    public static String android_accountInContacts = "//*[@resource-id = 'tpb.bb.hydrobank:id/txt_number_account' and @text = '%s']";

    // Tab interbank transfer
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Liên Ngân Hàng']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnInterbank;

    @AndroidFindBy(xpath = "//*[@resource-id='tpb.bb.hydrobank:id/edt_bank_name']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnSelectBank;

    @AndroidFindBy(xpath = "//*[@resource-id='tpb.bb.hydrobank:id/edt_search_bank']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtSearchBank;

    @AndroidFindBy(xpath = "//*[@text = 'Số tài khoản']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtAccount;

    @AndroidFindBy(xpath = "//*[@text = 'Số tài khoản']/following-sibling::android.widget.LinearLayout/child::android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtInputAccount;

    @AndroidFindBy(xpath = "//*[@text='TÔI ĐÃ HIỂU']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnGuaranteedTransfer;

    @AndroidFindBy(xpath = "//*[@text='Chuyển tiền đảm bảo']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtGuaranteedTransfer;

    @AndroidFindBy(xpath = "//*[@resource-id='tpb.bb.hydrobank:id/icRight']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade imgContactIcon;

    @AndroidFindBy(xpath = "//*[@resource-id='tpb.bb.hydrobank:id/edt_search']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtSearchContact;

    @AndroidFindBy(xpath = "//*[@text='CHUYỂN TIỀN THƯỜNG']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnStandardMoneyTransfer;

    @AndroidFindBy(xpath = "//*[@text = 'Tên người nhận']/following-sibling::android.widget.LinearLayout/child::android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtInputBeneficiaryName;

    String xpathBankResultSearch = "//*[@resource-id='tpb.bb.hydrobank:id/tvBankShortName' and @text= '%s']";
    String xpathOptSourceAccount = "//*[@resource-id='tpb.bb.hydrobank:id/txt_transfer_item_number_account' and @text='%s']";
    String xpathOptProvinceName = "//*[@resource-id='tpb.bb.hydrobank:id/itemTransferProvinceName' and @text='%s']";

    // Tab the ATM
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Thẻ ATM']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade btnTabATM;

    @AndroidFindBy(xpath = "//*[@text = 'Số thẻ ATM']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtATMNumber;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id = 'tpb.bb.hydrobank:id/edt_input']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtInputATMNumber;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='tpb.bb.hydrobank:id/edt_search_save_card']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade txtInputSearchATM;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tpb.bb.hydrobank:id/txt_transfer_name']")
    @iOSXCUITFindBy(xpath = "")
    WebElementFacade lblATMFirstResultInContact;

    public void inputAccountNumberOrPhone(String account) {
        txtAccountNumberOrPhone.waitUntilClickable().click();
        txtInputAccountNumberOrPhone.waitUntilVisible().sendKeys(account);
    }

    public void inputAmount(String amount) {
        txtAmount.waitUntilClickable().click();
        txtInputAmount.waitUntilVisible().sendKeys(amount);
    }

    public void inputMessage(String message) {
        txtMessage.waitUntilClickable().click();
        txtInputMessage.waitUntilVisible().clear();
        txtInputMessage.waitUntilVisible().sendKeys(message);
    }

    public void waitForReciverNameDisplayed() {
        txtAmount.waitUntilClickable().click();
        waitUntilElementVisible(30, txtReceiverName);
    }

    public void clickOnContinue() {
        btnContinue.waitUntilClickable().click();
    }

    public void clickOnConfirm() {
        if (txtGuaranteedTransfer.isVisible()) {
            btnGuaranteedTransfer.waitUntilClickable().click();
        }
        btnConfirm.waitUntilClickable().click();
    }

    public String getDialogMessage() {
        return txtDialogMessage.waitUntilVisible().getText();
    }

    public void clickOnPostCard() {
        imgPostCard.waitUntilClickable().click();
    }

    public void clickOnStickerIcon() {
        imgStickerIcon.waitUntilClickable().click();
    }

    public void selectGreetingCard(String cardName) {
        txtCoachMarkMessage.waitUntilClickable().click();
        if (Utilities.isCurrentPlatformAndroid()) {
            String xpath = "//android.widget.GridView[@resource-id = 'tpb.bb.hydrobank:id/rcvListCard']//android.widget.TextView[@text = '%s']";
            String card = String.format(xpath, cardName);
            findBy(card).waitUntilClickable().click();
        } else {
            // handle for ios
        }
    }

    public void clickOnDone() {
        btnDone.waitUntilClickable().click();
    }

    public void clickOnSchedulePaymentOrder() {
        txtSchedulePaymentOrder.waitUntilClickable().click();
    }

    public void clickOnBackToChatPay() {
        btnChatPay.waitUntilClickable().click();
    }

    public void clickOnContacts() {
        imgContacts.waitUntilEnabled().click();
    }

    public void clickOnCloseContacts() {
        imgCloseContacts.waitUntilClickable().click();
    }

    public String getMessageEmptyInContacts() {
        return txtMessageEmpty.waitUntilVisible().getText();
    }

    public void clickOnYourAccountInContacts(String account) {
        if (Utilities.isCurrentPlatformAndroid()) {
            String xpath = String.format(android_yourAccount, account);
            findBy(xpath).waitUntilVisible().click();
        } else {
            String xpath = String.format("", account);
            findBy(xpath).waitUntilVisible().click();
        }
    }

    public boolean verifyTitleInContacts() {
        return txtChooseAccountFromContacts.waitUntilVisible().isDisplayed();
    }

    public List<String> getListRecentAccount() {
        txtListRecentAccount.get(0).waitUntilVisible();
        List<String> recentAccounts = new ArrayList<>();
        for (WebElementFacade account : txtListRecentAccount) {
            recentAccounts.add(account.getText());
        }
        return recentAccounts;
    }

    public boolean verifyAccountInContacts(String account) {
        if (Utilities.isCurrentPlatformAndroid()) {
            String xpath = String.format(android_accountInContacts, account);
            return findBy(xpath).waitUntilVisible().isDisplayed();
        } else {
            // handle for ios
            String xpath = String.format("", account);
            return findBy(xpath).waitUntilVisible().isDisplayed();
        }
    }

    public void searchInContacts(String name) {
        txtSearchInContacts.waitUntilVisible().sendKeys(name);
    }

    public boolean verifyAccountHolderInContacts(String account) {
        if (Utilities.isCurrentPlatformAndroid()) {
            String xpath = String.format(android_accountHolderInContacts, account);
            return findBy(xpath).waitUntilVisible().isDisplayed();
        } else {
            String xpath = String.format("", account);
            return findBy(xpath).waitUntilVisible().isDisplayed();
        }
    }

    public void clickOnTelephoneDirectory() {
        btnTelephoneDirectory.waitUntilClickable().click();
    }

    public boolean verifyReceiverNameIsDisplayed() {
        return txtReceiverName.waitUntilVisible().isDisplayed();
    }

    public void clickOnRemoveAccountNumberOrPhone() {
        txtAccountNumberOrPhone.waitUntilClickable().click();
        btnRemoveAccountNumberOrPhone.waitUntilClickable().click();
    }

    public String getValueMoneyTransferField(String field) {
        String value = "";
        switch (field) {
            case "account":
                value = txtInputAccountNumberOrPhone.waitUntilVisible().getAttribute("text");
                break;
            case "amount":
                value = txtInputAmount.waitUntilVisible().getAttribute("text");
                break;
            case "message":
                value = txtInputMessage.waitUntilVisible().getAttribute("text");
                break;
        }
        return value;
    }

    public List<String> getSuggestedAmount() {
        return txtSuggestedAmount.stream()
                .map(i -> i.waitUntilVisible().getText())
                .collect(Collectors.toList());
    }

    public void clickOnMoneyTransferField(String field) {
        switch (field) {
            case "account":
                txtAccountNumberOrPhone.waitUntilClickable().click();
                break;
            case "amount":
                txtAmount.waitUntilClickable().click();
                break;
        }
    }

    public String getWarningMessage() {
        return txtWarningMessage.waitUntilVisible().getText();
    }

    public boolean checkContinueButtonIsEnable() {
        return btnContinue.waitUntilVisible().isEnabled();
    }

    public String getFavouriteAccount() {
        return txtFavouriteAccount.waitUntilVisible().getText();
    }

    public void clickOnChangeAccount() {
        btnChangeAccount.waitUntilClickable().click();
    }

    /**
     * TODO: Tính năng chuyển tiền liên ngân hàng
     * @author: landt4
     */

    /**
     * Chọn tài khoản nguồn muốn chuyển tiền
     *
     * @param sourceAccount: tài khoản nguồn
     * @return element tài khoản nguồn theo sourceAccount
     */
    public WebElementFacade selectSourceAccount(String sourceAccount) {
        if (sourceAccount == null || sourceAccount.isEmpty()) {
            throw new IllegalArgumentException("Invalid sourceAccount format.");
        }
        if (Utilities.isCurrentPlatformAndroid()) {
            return find(By.xpath(String.format(
                    xpathOptSourceAccount, sourceAccount.replaceAll("(\\d{4})(\\d{4})(\\d{3})", "$1 $2 $3"))));
        } else {
            throw new UnsupportedOperationException("iOS platform is not yet supported for this method.");
        }
    }

    public void clickOnTabInterbank() {
        btnInterbank.waitUntilClickable().click();
    }

    public void clickOnButtonSelectBank() {
        btnSelectBank.waitUntilClickable().click();
    }

    public void clickOnButtonSearchBank(String bankName) {
        txtSearchBank.waitUntilVisible().sendKeys(bankName);
    }

    /**
     * Lấy element liên ngân hàng
     *
     * @param bankName: tên ngân hàng muốn tìm kiếm
     * @return element của ngân hàng muốn chuyển tiền tới
     */
    public WebElementFacade getBankElementByName(String bankName) {
        if (bankName == null || bankName.isEmpty()) {
            throw new IllegalArgumentException("Invalid bankName, bankName not null");
        }
        if (Utilities.isCurrentPlatformAndroid()) {
            return find(By.xpath(String.format(xpathBankResultSearch, bankName)));
        } else {
            throw new UnsupportedOperationException("iOS platform is not yet supported for this method.");
        }
    }

    public void clickOnBankByName(String bankName) {
        getBankElementByName(bankName).waitUntilClickable().click();
    }

    public void inputAccount(String account) {
        txtAccount.waitUntilClickable().click();
        txtInputAccount.waitUntilVisible().sendKeys(account);
    }

    public void clickOnContactIcon() {
        imgContactIcon.waitUntilClickable().click();
    }

    public void chooseAccountFromContact(String keySearch) {
        txtSearchContact.waitUntilVisible().sendKeys(keySearch);
        selectSourceAccount(keySearch).waitUntilClickable().click();
    }

    public void clickButtonStandardMoneyTransfer() {
        if (btnStandardMoneyTransfer.isVisible()) {
            btnStandardMoneyTransfer.waitUntilClickable().click();
        }
    }

    public void selectProvince(String province) {
        if (Utilities.isCurrentPlatformAndroid()) {
            find(By.xpath(String.format(xpathOptProvinceName, province))).waitUntilClickable().click();
        } else {
            throw new UnsupportedOperationException("iOS platform is not yet supported for this method.");
        }
    }

    public void inputBeneficiaryName(String beneficiaryName) {
        txtInputBeneficiaryName.waitUntilClickable().sendKeys(beneficiaryName);
    }

    public void clickOnTabATMCard() {
        btnTabATM.waitUntilClickable().click();
    }

    public void inputATMNumber(String atmNumber, Boolean fromContact) {
        if (fromContact) {
            txtInputSearchATM.waitUntilClickable().click();
            txtInputSearchATM.sendKeys("NGUYEN VAN SAU");
            lblATMFirstResultInContact.waitUntilVisible().click();
        } else {
            txtATMNumber.waitUntilVisible().click();
            txtInputATMNumber.waitUntilVisible().sendKeys(atmNumber);
        }
    }

    public boolean getStatusFundTransferTitle() {
        return txtFundTransferTitle.waitUntilVisible().isVisible();
    }
}