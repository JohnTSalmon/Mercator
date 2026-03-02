package com.mercator.implementations.selenium;

import com.mercator.CommonPageInterface;
import com.mercator.HTML;


import com.mercator.Target;
import com.mercator.logger.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static com.mercator.HTML.Tag.HTML;
import static org.junit.jupiter.api.Assertions.assertTrue;
/////////////////////////////
// SELENIUM IMPLEMENTATION //
/////////////////////////////
public class CommonPage implements CommonPageInterface {
    Logger logger= new Logger();
    private static WebElement currentElement;
    private List<WebElement> currentElements = new ArrayList<>();
    private static WebDriver driver;
    private static WebDriverWait focusWait = null;
    private static CommonPage instance = null;
    private final int FOCUS_ELEMENT_PRESENT_TOLERANCE = 60;
    protected CommonPage() {
        super();
    }

    public static synchronized CommonPage getInstance() {
        if (instance == null) {
            instance = new CommonPage();
        }
        return instance;
    }

    @Override
    public void open() {
        log("Opening browser and initializing WebDriver...");
        Map<String, Object> prefs = new HashMap<>();
        ChromeOptions chromeOptions = new ChromeOptions();
        // Disable annoying password check popup
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(chromeOptions);
        focusWait = new WebDriverWait(driver, Duration.ofSeconds(FOCUS_ELEMENT_PRESENT_TOLERANCE));
    }

    @Override
    public void go(String url) {
        log("Navigating to URL: " + url);
        driver.get(url);
    }

    @Override
    public void close() {
            driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public void log(String msg) {
        logger.log(msg);
    }

    @Override
    public void traverse() {
        traverse(1);
    }

    public void traverse(int index) {
        currentElement = currentElement.findElement(By.xpath("following-sibling::*[" + index + "]"));
        log("traversed to : "+ currentElement.getTagName());

    }

    @Override
    public void focus(Target target) {
        log("Focusing on element with " + target.getType() + ": " + target.getValue());
        focus(getLocator(target));
    }

    @Override
    public void collect(Target target) {
            currentElements = driver.findElements(getLocator(target));
        }



    @Override
    public void click() {
            currentElement.click();
        }

    @Override
    public void clear() {
        currentElement.clear();
    }

    @Override
    public void compose(String content) {
        currentElement.sendKeys(content);
    }

    @Override
    public String get(HTML.ElementTrait ElementTrait) {
        return "";
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void matches(String text) {
        assertTrue(currentElement.getText().equals(text), "Expected text: '" + text + "' but found: '" + currentElement.getText() + "'");
    }

    @Override
    public void contains(String partialText) {
        assertTrue(currentElement.getText().contains(partialText), "Expected text: '" + partialText + "' but found: '" + currentElement.getText() + "'");
    }

    @Override
    public void choose(int index) {
        currentElement = currentElements.get(index);
    }

    @Override
    public void choose(String text) {
        for(WebElement element : currentElements) {
            if(element.getText().contains(text)) {
                currentElement = element;
                return;
            }
        }
        throw new NoSuchElementException("No element with text: '" + text + "' found in collection");
    }

    @Override
    public void choose(HTML.Index index) {
        choose(index.ordinal());
    }

    @Override
    public List<String> collectionToList() {
        List<String> textList = new ArrayList<>();
        log("Converting collection");
        for(WebElement element : currentElements) {
            textList.add(element.getText());
        }
        return textList;
    }

    private By getLocator(Target target) {
            By by = null;
        switch (target.getType()) {
            case ID:
                by = By.id(target.getValue());
                break;
            case DATA_TEST:
                by = By.cssSelector("[data-test='" + target.getValue() + "']");
                break;
            case CLASS:
                by = By.className(target.getValue());
                break;
            case NAME:
                by = By.name(target.getValue());
                break;
            case TAG_WITH_TEXT:
                by = By.xpath("//" + target.getTag().name().toLowerCase() + "[contains(normalize-space(text()), '" + target.getValue() + "')]");
                break;
            case PLACEHOLDER:
                by = By.xpath("//input[@placeholder='" + target.getValue() + "'] | //textarea[@placeholder='" + target.getValue() + "']");
                break;
            default :
                throw new IllegalArgumentException("Unsupported locator type: " + target.getType());
        }
        return by;
    }

    private void focus(By by) {
        try {
            currentElement = focusWait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (WebDriverException wde) {
            throw new NoSuchElementException("Element not found : " + by.toString(), wde);
        }
    }
}
