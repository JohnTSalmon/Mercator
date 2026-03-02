package com.mercator;

import lombok.Getter;

@Getter
public class Target {

    private String value;
    protected Type type;
    protected HTML.Tag tag;

    public enum Type {
        ID,
        CLASS,
        CLASSES,
        TAG,
        NAME,
        PLACEHOLDER,
        DATA_TEST,
        TAG_WITH_TEXT;
    }
    public Target(String value, Type type) {
        this.value = value;
        this.type = type;
    }

    public Target(String locator, String value, Type type)
    {
        this.value = value;
        this.type = type;
    }

    public Target(HTML.Tag tag) {
        this.tag = tag;
    }

    public Target(HTML.Tag tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public Target(HTML.Tag tag, Type type)
    {
        this.tag = tag;
        this.type = type;
    }

    public Target(HTML.Tag tag, String value, Type type) {
        this.tag = tag;
        this.value = value;
        this.type = type;
    }


}
