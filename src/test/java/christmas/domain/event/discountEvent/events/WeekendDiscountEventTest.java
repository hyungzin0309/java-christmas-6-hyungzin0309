package christmas.domain.event.discountEvent.events;

import static christmas.domain.order.menu.Menu.CHAMPAGNE;
import static christmas.domain.order.menu.Menu.CHOCOLATE_CAKE;
import static christmas.domain.order.menu.Menu.ICE_CREAM;
import static christmas.domain.order.menu.Menu.MUSHROOM_SOUP;
import static christmas.domain.order.menu.Menu.SEAFOOD_PASTA;
import static christmas.domain.order.menu.Menu.T_BONE_STEAK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import christmas.domain.order.menu.Menu;
import christmas.domain.order.menu.OrderMenu;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WeekendDiscountEventTest {


    private WeekendDiscountEvent weekendDiscountEvent;
    private VisitDay validVisit;

    @BeforeEach
    public void setUp() {
        weekendDiscountEvent = new WeekendDiscountEvent();
        validVisit = new VisitDay(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "9", "22", "30"})
    @DisplayName("주말 주문에 이벤트가 적용된다.")
    public void applicableAtWeekend(int day) {
        Order order = new Order(new VisitDay(day), null);

        assertTrue(weekendDiscountEvent.isApplicableEventFor(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "5", "10", "25"})
    @DisplayName("평일 주문에 이벤트가 적용되지 않는다.")
    public void notApplicableAtWeekday(int day) {
        Order order = new Order(new VisitDay(day), null);

        assertFalse(weekendDiscountEvent.isApplicableEventFor(order));
    }

    @Test
    @DisplayName("메인 메뉴 개당 2023원씩 할인한다.")
    public void benefitAmount() {
        Map<Menu, Integer> menuOrders = new HashMap<>();
        menuOrders.put(MUSHROOM_SOUP, 2);
        menuOrders.put(T_BONE_STEAK, 1); // 메인 1개
        menuOrders.put(SEAFOOD_PASTA, 2); // 메인 2개
        menuOrders.put(CHOCOLATE_CAKE, 1);
        menuOrders.put(ICE_CREAM, 2);

        Order order = new Order(validVisit, new OrderMenu(menuOrders));

        assertEquals(2023 * 3, weekendDiscountEvent.getDiscountAmount(order));
    }

    @Test
    @DisplayName("메인 메뉴 주문이 없으면 할인적용 되지 않는다.")
    public void applyNothing() {
        Map<Menu, Integer> menuOrders = new HashMap<>();
        menuOrders.put(ICE_CREAM, 1);
        menuOrders.put(MUSHROOM_SOUP, 2);
        menuOrders.put(CHAMPAGNE, 2);

        Order order = new Order(validVisit, new OrderMenu(menuOrders));

        assertEquals(0, weekendDiscountEvent.getDiscountAmount(order));
    }
}
