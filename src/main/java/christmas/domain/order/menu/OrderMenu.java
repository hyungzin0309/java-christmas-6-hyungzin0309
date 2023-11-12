package christmas.domain.order.menu;

import static christmas.message.ErrorMessage.INVALID_MENU_INPUT;
import static christmas.message.Message.NUM;

import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenu {

    private static final int ORDER_QUANTITY_MIN = 1;
    private static final int TOTAL_QUANTITY_MAX = 20;

    private final Map<Menu, Integer> orderMenus;

    public OrderMenu(Map<Menu, Integer> orderMenus) {
        orderQuantityValidate(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void orderQuantityValidate(Map<Menu, Integer> orderMenus) {
        if (orderMenus.isEmpty()) {
            throwInvalidException();
        }
        if (orderMenus.keySet().stream().allMatch(menu -> menu.getMenuType() == MenuType.DRINK)) {
            throwInvalidException();
        }
        if (orderMenus.values().stream().anyMatch(amount -> amount < ORDER_QUANTITY_MIN)) {
            throwInvalidException();
        }
        if (TOTAL_QUANTITY_MAX < orderMenus.values().stream().mapToInt(Integer::intValue).sum()) {
            throwInvalidException();
        }
    }

    private void throwInvalidException() {
        throw new IllegalArgumentException(INVALID_MENU_INPUT);
    }

    public int getTotalPrice() {
        int totalAmount = 0;
        for (Menu menu : orderMenus.keySet()) {
            int price = menu.getPrice();
            int orderNum = orderMenus.get(menu);
            totalAmount += price * orderNum;
        }
        return totalAmount;
    }

    public int getOrderQuantityByType(MenuType type) {
        int count = 0;
        for (Menu menu : Menu.values()) {
            if (menu.getMenuType() == type) {
                count += orderMenus.getOrDefault(menu, 0);
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return orderMenus.entrySet()
                .stream()
                .map(entry -> entry.getKey().toString() + " " + entry.getValue() + NUM + "\n")
                .collect(Collectors.joining());
    }
}
