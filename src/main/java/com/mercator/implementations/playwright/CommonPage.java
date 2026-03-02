package com.mercator.implementations.playwright;

import com.mercator.CommonPageInterface;
import com.mercator.HTML;
import com.mercator.Target;
import com.mercator.logger.Logger;
import com.microsoft.playwright.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
///////////////////////////////
// PLAYWRIGHT IMPLEMENTATION //
///////////////////////////////
public class CommonPage implements CommonPageInterface {
    Logger logger= new Logger();
    private static Page page;
    private static CommonPage instance = null;
    private Locator currentElement;
    private List<Locator> currentElements;// Driver
    private Browser browser;

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
        try {
            Playwright playwright = Playwright.create();
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
            browser = playwright.chromium().launch(launchOptions);
            page = browser.newPage();
        }
        catch(PlaywrightException playwrightException) {
            log(playwrightException.getMessage());
        }
    }

    @Override
    public void go(String url) {
        log("Navigating to URL: " + url);
        page.navigate(url);
    }

    @Override
    public void close() {
        browser.close();

    }

    @Override
    public void quit() {
        page.close();
    }

    @Override
    public void log(String msg) {
        logger.log(msg);
    }

    @Override
    public void traverse() {
        log("traverse to next sibling");
        currentElement = currentElement.locator("//following-sibling::*");
    }

    public void traverse(int index) {
        currentElement = currentElement.locator("following-sibling::[" + index + "]");
        log("traversed to : "+ currentElement.textContent());
    }

    @Override
    public void focus(Target target) {
        log("Focusing on element with " + target.getType() + ": " + target.getValue());
        String locator = getLocator(target);
        currentElement = page.locator(locator);
    }

    @Override
    public void collect(Target target) {
        log("Collect " + target.getType() + ": " + target.getValue());
        currentElements = page.locator(getLocator(target)).all();
    }

    @Override
    public void click() {
        log("click");
        currentElement.click();
    }

    @Override
    public void clear() {
        currentElement.clear();
    }

    @Override
    public void compose(String content) {
        log("compose: " + content);
        currentElement.fill(content);
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
        assertTrue(currentElement.textContent().equals(text), "Expected text: '" + text + "' but found: '" + currentElement.textContent() + "'");
    }

    @Override
    public void contains(String partialText) {
        assertTrue(currentElement.textContent().contains(partialText), "Expected text: '" + partialText + "' but found: '" + currentElement.textContent() + "'");
    }

    @Override
    public void choose(int index) {
        currentElement = currentElements.get(index);
    }

    @Override
    public void choose(String text) {
        for(Locator element : currentElements) {
            if(element.textContent().contains(text)) {
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
        for(Locator element : currentElements) {
            textList.add(element.textContent());
        }
        return textList;
    }

    private String getLocator(Target target) {
        String by = null;
        switch (target.getType()) {
            case ID:
                by = "#"+target.getValue();
                break;
            case DATA_TEST:
                by = "[data-test='" + target.getValue() + "']";
                break;
            case CLASS:
                by = "."+target.getValue();
                break;
            case NAME:
                by = "[name='" + target.getValue() + "']";
                break;
            case TAG_WITH_TEXT:
                by = "//" + target.getTag().name().toLowerCase() + "[contains(normalize-space(text()), '" + target.getValue() + "')]";
                break;
            case PLACEHOLDER:
                // Can also use Page.getByPlaceholder()
                by = "//input[@placeholder='" + target.getValue() + "'] | //textarea[@placeholder='" + target.getValue() + "']";
                break;
            default :
                throw new IllegalArgumentException("Unsupported locator type: " + target.getType());
        }
        return by;
    }
}
