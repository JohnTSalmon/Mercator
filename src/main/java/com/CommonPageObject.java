package com.mercator;


import com.mercator.implementations.playwright.CommonPage;

import java.util.List;

public class CommonPageObject extends TargetFactory {

    // Singleton
    private final CommonPage commonPage = CommonPage.getInstance();

    public CommonPageObject open() {
        commonPage.open();
        return this;
    }

    public CommonPageObject traverse(int  index) {
        commonPage.choose(index);
        return this;
    }

    public CommonPageObject traverse() {
        commonPage.traverse();
        return this;
    }

    public CommonPageObject choose(HTML.Index index) {
        commonPage.choose(index);
        return this;
    }



    public CommonPageObject go(String url) {
        commonPage.go(url);
        return this;
    }

    public List<String> collectionToList() {
        return commonPage.collectionToList();
    }

    public CommonPageObject focus(Target target) {
        commonPage.focus(target);
        return this;
    }

    public CommonPageObject collect(Target target) {
        commonPage.collect(target);
        return this;
    }
    public CommonPageObject click() {
        commonPage.click();
        return this;
    }

    public CommonPageObject choose(int index) {
        commonPage.choose(index);
        return this;
    }

    public CommonPageObject choose(String text) {
        commonPage.choose(text);
        return this;
    }

    public CommonPageObject compose(String keysToSend) {
        commonPage.compose(keysToSend);
        return this;
    }

    public CommonPageObject matches(String text) {
        commonPage.matches(text);
        return this;
    }

    public CommonPageObject contains(String text) {
        commonPage.contains(text);
        return this;
    }
}
