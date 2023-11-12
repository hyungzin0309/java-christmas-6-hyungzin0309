package christmas.view;

import static camp.nextstep.edu.missionutils.Console.*;
import static christmas.message.ErrorMessage.INVALID_DAY_INPUT;
import static christmas.message.ErrorMessage.INVALID_MENU_INPUT;

import christmas.domain.order.menu.Menu;
import christmas.domain.order.menu.OrderMenu;
import christmas.domain.order.menu.MenuOrdersConverter;
import christmas.domain.order.VisitDay;
import christmas.message.Message;
import christmas.utils.ConvertUtils;
import java.util.Map;

public class InputView {

    public static VisitDay readVisitDay() {
        System.out.println(Message.INPUT_DAY);
        while (true) {
            try {
                int day = ConvertUtils.convertToNum(readLine());
                return new VisitDay(day);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_DAY_INPUT);
            }
        }
    }

    public static OrderMenu readOrderMenu() {
        System.out.println(Message.INPUT_MENU);
        while (true) {
            try {
                Map<Menu, Integer> menuOrders = MenuOrdersConverter.convertToMenuOrders(readLine());
                return new OrderMenu(menuOrders);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_MENU_INPUT);
            }
        }
    }

}
