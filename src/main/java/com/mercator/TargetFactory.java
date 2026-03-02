package com.mercator;

import java.util.StringTokenizer;

import static com.mercator.Target.Type.*;

public class TargetFactory {

    protected TargetFactory(){

    }

    public static Target data_test(String value) {
        return new Target(value, DATA_TEST);
    }

    public static Target className(String value ) {
        return  new Target(value, CLASS);
    }

    protected static Target classNames(String classNames )  {

        StringTokenizer st = new StringTokenizer(classNames);
        StringBuffer sb = new StringBuffer();

        while(st.hasMoreTokens()) {
            sb.append(".");
            sb.append(st.nextToken());
        }
        return  new Target(sb.toString(), CLASSES);
    }


    public static Target id(String value) {
        return new Target(value, ID);
    }

    public static Target tagWithText(HTML.Tag tag, String value) {
        return new Target(tag, value, TAG_WITH_TEXT);
    }

    public static Target tag(HTML.Tag tag) {
        return new Target(tag);
    }

    public static Target name(String value) {
        return new Target(value, NAME);
    }

    public static Target placeHolder(String value) {
        return new Target(value, PLACEHOLDER);
    }
}
