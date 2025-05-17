package bb.common;

import bb.utils.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BasePage extends PageObject {
    private Dimension screenSize;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private static final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    private final Sequence scroll = new Sequence(finger, 1);
    private final Sequence tap = new Sequence(finger, 1);
    private final Sequence hold = new Sequence(finger, 1);
    private final Sequence longPress = new Sequence(finger, 1);
    private final Sequence swipe = new Sequence(finger, 1);
    Actions actions = new Actions(getAppiumDriver());

    protected AppiumDriver getAppiumDriver() {
        return (AppiumDriver) ((WebDriverFacade) getDriver()).getProxiedDriver();
    }

    protected AndroidDriver getAndroidDriver() {
        WebDriver driver = ((WebDriverFacade) getDriver()).getProxiedDriver();
        if (driver instanceof AndroidDriver) {
            return (AndroidDriver) driver;
        }
        throw new IllegalStateException("Current driver is not an AndroidDriver");
    }

    protected IOSDriver getIOSDriver() {
        WebDriver driver = ((WebDriverFacade) getDriver()).getProxiedDriver();
        if (driver instanceof IOSDriver) {
            return (IOSDriver) driver;
        }
        throw new IllegalStateException("Current driver is not an IOSDriver");
    }

    public Point getElementCenterPoint(WebElementFacade element) {
        Rectangle reg = element.getRect();
        return new Point(reg.getX() + reg.getWidth() / 2, reg.getY() + reg.getHeight() / 2);
    }

    public void waitAMilliSeconds(int milliseconds) {
        waitABit(milliseconds);
    }

    protected WebDriverWait getExplicitWait(int timeout) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    protected FluentWait<WebDriver> getFluentWait(int timeout) {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(50));
    }

    public void enterValueToNumberKeyBoardIOS(String number) {
        for (char digit : number.toCharArray()) {
            getAppiumDriver().findElement(By.id(String.valueOf(digit))).click();
        }
    }

    public void waitForAlertAndAccept() {
        getExplicitWait(15).until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
    }

    public String getAlertText() {
        getExplicitWait(15).until(ExpectedConditions.alertIsPresent());
        return getDriver().switchTo().alert().getText();
    }

    public static String changeCurrencyFormatVND(String balance) {
        double balanceDouble = Double.parseDouble(balance);
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(balanceDouble);
    }

    public void pressTab() {
        getAndroidDriver().pressKey(new KeyEvent(AndroidKey.TAB));
    }

    public void pressEnter() {
        getAndroidDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void pressBack() {
        getAppiumDriver().navigate().back();
    }

    public void scrollDownByAction() {
        if (screenSize == null) {
            screenSize = getDriver().manage().window().getSize();
        }
        int deviceHeight = screenSize.getHeight();
        int deviceWidth = screenSize.getWidth();
        int startX = deviceWidth / 2;
        int startY = (deviceHeight / 2) - 100;
        int endY = 0;
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(scroll));
    }

    public void hideKeyboard() {
        try {
            if (Utilities.isCurrentPlatformAndroid()) {
                getAndroidDriver().hideKeyboard();
            } else {
                getIOSDriver().hideKeyboard();
            }
        } catch (Exception e) {
            logger.error("Error while hiding keyboard", e);
        }
    }

    public void tapOnElementByAction(WebElementFacade e) {
        Point p = e.getLocation();
        int x = p.getX() + (e.getSize().getWidth() / 2);
        int y = p.getY() + (e.getSize().getHeight() / 2);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(tap));
    }

    public void holdOnElement(WebElementFacade e) {
        Point p = e.getLocation();
        int x = p.getX() + (e.getSize().getWidth() / 2);
        int y = p.getY() + (e.getSize().getHeight() / 2);
        hold.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        hold.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        hold.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), x, y));
        hold.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(hold));
    }

    public boolean checkIfElementIsPresent(WebElementFacade el) {
        try {
            return el.waitUntilVisible().isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilElementInvisibleByXpath(int timeoutInSeconds, String xpath) {
        getFluentWait(timeoutInSeconds)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilElementVisibleByXpath(int timeout, String xpath) {
        getExplicitWait(timeout).until(ExpectedConditions.visibilityOf($(xpath)));
    }

    public void tabOnCoordinate(int x, int y) {
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(tap));
    }

    public void logPressOnCoordinate(int x, int y) {
        longPress.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), x, y));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(longPress));
    }

    public void tabOnPercentLocation(double xPercent, double yPercent) {
        int heightOfScreen = getAppiumDriver().manage().window().getSize().getHeight();
        int widthOfScreen = getAppiumDriver().manage().window().getSize().getWidth();
        int x = (int) (widthOfScreen * xPercent / 100);
        int y = (int) (heightOfScreen * yPercent / 100);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(tap));
    }

    public WebElementFacade waitUntilElementInvisible(int timeout, WebElementFacade e) {
        getFluentWait(timeout).until(ExpectedConditions.invisibilityOf(e));
        return e;
    }

    public void waitUntilElementInvisibleById(int timeout, String id) {
        getExplicitWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
    }

    public WebElementFacade waitUntilElementVisible(int timeoutInSeconds, WebElementFacade e) {
        getFluentWait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(e));
        return e;
    }

    public void enterPasscodeAndroid(String number) {
        for (char digit : number.toCharArray()) {
            KeyEvent keyEvent = new KeyEvent(AndroidKey.valueOf("KEYCODE_" + digit));
            getAndroidDriver().pressKey(keyEvent);
        }
    }

    public void enterPasscodeByIdAndroid(String number) {
        for (char digit : number.toCharArray()) {
            String id = String.format("authenticationJourney_passcodeKeyboard_%sButton", digit);
            $(By.id(id)).waitUntilClickable().click();
        }
    }

    private Keys numpad(Character c) {
        return switch (c) {
            case '0' -> Keys.NUMPAD0;
            case '1' -> Keys.NUMPAD1;
            case '2' -> Keys.NUMPAD2;
            case '3' -> Keys.NUMPAD3;
            case '4' -> Keys.NUMPAD4;
            case '5' -> Keys.NUMPAD5;
            case '6' -> Keys.NUMPAD6;
            case '7' -> Keys.NUMPAD7;
            case '8' -> Keys.NUMPAD8;
            case '9' -> Keys.NUMPAD9;
            default -> throw new IllegalArgumentException("Invalid digit: " + c);
        };
    }

    public String formatNumber(String s) {
        return s.replaceAll("[^0-9]+", "");
    }

    public List<String> getTextListElement(By by) {
        return findAll(by).stream().map(WebElementFacade::getText).toList();
    }

    public List<String> getTextListElementFormatXpath(String xpathNeedFormat, List<String> listStringFormat) {
        return listStringFormat.stream()
                .map(s -> $(String.format(xpathNeedFormat, s)).getText())
                .toList();
    }

    public static String changeAccountNumberFormat(String s) {
        return s.replaceAll("(.{4})", "$1 ").trim();
    }

    public void waitUntilElementEnableByXpath(int timeout, String xpath) {
        getExplicitWait(timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public WebElementFacade waitElementEnable(int timeout, WebElementFacade e) {
        getFluentWait(timeout).until(ExpectedConditions.elementToBeClickable(e));
        return e;
    }

    public String getLastMonth(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return simpleDateFormat.format(cal.getTime());
    }

    public String getCurrentMonth(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    public void swipeToLeft(int x, int y) {
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), 0, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(swipe));
    }

    public void scrollDownToElementVisible(int index, WebElementFacade e) {
        int i = 0;
        while (!e.isCurrentlyVisible() && i < index) {
            scrollDownByAction();
            i++;
        }
    }

    public void swipeLeftOnElement(WebElementFacade e, int horizontally) {
        int x = e.getLocation().getX();
        int y = e.getLocation().getY();
        int width = e.getSize().getWidth();
        int height = e.getSize().getHeight();
        int startX = x + width - 10;
        int startY = y + height / 2;
        int endX = x + horizontally;
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, startY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(swipe));
    }

    public static String formatBlankSpace(String s) {
        return s.replaceAll("[\\s\\u00A0]+", "");
    }

    public void waitAndClickElement(WebElementFacade e) {
        e.waitUntilClickable().click();
    }

    public String waitAndGetTextElement(WebElementFacade e) {
        return e.waitUntilVisible().getText();
    }

    public void waitAndSendKeysElement(WebElementFacade e, String value) {
        e.waitUntilVisible().sendKeys(value);
    }

    public long randomNumber(long quantity) {
        Random rand = new Random();
        return rand.nextLong(quantity);
    }

    public void sendKeysByKeyboard(String value) {
        actions.sendKeys(value).perform();
    }
}