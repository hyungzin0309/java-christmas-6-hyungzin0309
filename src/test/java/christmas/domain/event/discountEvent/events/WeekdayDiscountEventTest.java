package christmas.domain.event.discountEvent.events;

import static christmas.domain.order.menu.Menu.*;
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

class WeekdayDiscountEventTest {

    private WeekdayDiscountEvent weekdayDiscountEvent;
    private VisitDay validVisit;

    @BeforeEach
    public void setUp() {
        weekdayDiscountEvent = new WeekdayDiscountEvent();
        validVisit = new VisitDay(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "5", "10", "25"})
    @DisplayName("평일 주문에 이벤트가 적용된다.")
    public void applicableAtWeekday(int day) {
        Order order = new Order(new VisitDay(day), null);

        assertTrue(weekdayDiscountEvent.isApplicableEventFor(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "9", "22", "30"})
    @DisplayName("주말 주문에 이벤트가 적용되지 않는다.")
    public void notApplicableAtWeekend(int day) {
        Order order = new Order(new VisitDay(day), null);

        assertFalse(weekdayDiscountEvent.isApplicableEventFor(order));
    }

    @Test
    @DisplayName("디저트 메뉴 개당 2023원씩 할인한다.")
    public void benefitAmount() {
        Map<Menu, Integer> menuOrders = new HashMap<>();
        menuOrders.put(T_BONE_STEAK, 1);
        menuOrders.put(MUSHROOM_SOUP, 2);
        menuOrders.put(CHOCOLATE_CAKE, 1); // 디저트 1개
        menuOrders.put(ICE_CREAM, 2); // 디저트 2개

        Order order = new Order(validVisit, new OrderMenu(menuOrders));

        assertEquals(2023 * 3, weekdayDiscountEvent.getDiscountAmount(order));
    }

    @Test
    @DisplayName("디저트 주문이 없으면 할인적용 되지 않는다.")
    public void applyNothing() {
        Map<Menu, Integer> menuOrders = new HashMap<>();
        menuOrders.put(T_BONE_STEAK, 1);
        menuOrders.put(MUSHROOM_SOUP, 2);

        Order order = new Order(validVisit, new OrderMenu(menuOrders));

        assertEquals(0, weekdayDiscountEvent.getDiscountAmount(order));
    }
}