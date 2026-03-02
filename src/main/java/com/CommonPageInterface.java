package com.mercator;

import java.util.List;

public interface CommonPageInterface {

    void choose(int index);
    void choose(String text);
    void choose(HTML.Index index);
    void clear();
    void click();
    void close();
    void collect(Target target);
    void compose(String content);
    void contains(String partialText);
    void focus(Target target);
    String get(HTML.ElementTrait ElementTrait);
    String getTitle();
    String getUrl();
    void go(String url);
    void matches(String text);
    void open();
    void quit();
    void log(String msg);
    void traverse();
    void traverse(int index);
    List<String> collectionToList();
}
