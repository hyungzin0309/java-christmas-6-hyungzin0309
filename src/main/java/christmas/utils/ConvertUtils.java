package christmas.utils;

import static christmas.message.ErrorMessage.INVALID_NUM_INPUT;

public class ConvertUtils {

    public static int convertToNum(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUM_INPUT);
        }
    }

}
