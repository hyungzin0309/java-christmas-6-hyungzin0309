package christmas.domain.order.menu;

import static christmas.message.ErrorMessage.INVALID_MENU_INPUT;

import christmas.utils.ConvertUtils;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuOrdersConverter {

    private static final String ORDER_DELIMITER = ",";
    private static final String MENU_AMOUNT_DELIMITER = "-";

    public static Map<Menu, Integer> convertToMenuOrders(String menuInput) {
        try {
            return _convertToMenuOrders(menuInput);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(INVALID_MENU_INPUT);
        }
    }

    private static Map<Menu, Integer> _convertToMenuOrders(String menuInput) {
        return Arrays.stream(menuInput.split(ORDER_DELIMITER))
                .map(menus -> menus.split(MENU_AMOUNT_DELIMITER))
                .collect(Collectors.toMap(
                        menuAndQuantity -> Menu.getBy(menuAndQuantity[0]),
                        menuAndQuantity -> ConvertUtils.convertToNum(menuAndQuantity[1]),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(INVALID_MENU_INPUT);
                        }
                ));
    }

}
