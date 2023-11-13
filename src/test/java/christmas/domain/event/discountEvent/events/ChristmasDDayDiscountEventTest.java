package christmas.domain.event.discountEvent.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDDayDiscountEventTest {

    private ChristmasDDayDiscountEvent christmasDDayDiscountEvent;

    @BeforeEach
    public void setUp() {
        christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "13", "25"})
    @DisplayName("12월 1일부터 12월 25일까지의 주문에 이벤트가 적용된다.")
    public void applicableCondition(int day) {
        Order order = new Order(new VisitDay(day), null);

        assertTrue(christmasDDayDiscountEvent.isApplicableEventFor(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"26", "28", "30"})
    @DisplayName("12월 1일부터 12월 25일 이외의 날짜에 주문시 이벤트가 적용되지 않는다.")
    public void notApplicableCondition(int day) {
        Order order = new Order(new VisitDay(day), null);

        assertFalse(christmasDDayDiscountEvent.isApplicableEventFor(order));
    }

    @Test
    @DisplayName("기본으로 1000원 할인 혜택이 주어지며, 주문날짜와 12월 1일의 일수 차이만큼 100씩 증가한 할인 혜택을 받는다.")
    void testGetDiscountAmount() {
        Order order = new Order(new VisitDay(20), null);

        long expectedDiscount = 1000 + (100 * (20 - 1));

        assertEquals(expectedDiscount, christmasDDayDiscountEvent.getDiscountAmount(order));
    }
}