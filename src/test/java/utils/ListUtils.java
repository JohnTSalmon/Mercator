package utils;

import java.util.List;

public class ListUtils {

    public enum LIST_OPERATION {
        HIGHEST,
        LOWEST,
    }

    public static String getListValue(List<String> listItems, LIST_OPERATION operation) {
        double result = removeCurrencySymbol(listItems.getFirst());
        double value = 0;
        for (String item : listItems) {
            value = removeCurrencySymbol(item);
            switch (operation) {
                case HIGHEST: {
                    if (value > result) {
                        result = value;
                    }
                    break;
                }
                case LOWEST: {
                    if (value < result) {
                        result = value;
                    }
                    break;
                }
            }
        }
            return String.valueOf(result);
        }

    private static double removeCurrencySymbol(String value) {
        return Double.parseDouble(value.replace("$", "").replace("£", ""));
    }
}
