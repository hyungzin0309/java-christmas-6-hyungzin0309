package christmas.domain.event.discountEvent.events;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountEventTest {

    private SpecialDiscountEvent specialDiscountEvent;

    @BeforeEach
    public void setUp() {
        specialDiscountEvent = new SpecialDiscountEvent();
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17", "24", "31", "25"})
    @DisplayName("매주 일요일 혹은 크리스마스에 이벤트가 적용된다.")
    public void applicableAtSundayOrChristmas(int day) {
        Order weekendOrChristmasOrder = new Order(new VisitDay(day), null);

        assertTrue(specialDiscountEvent.isApplicableEventFor(weekendOrChristmasOrder));
    }

    @ParameterizedTest
    @ValueSource(strings = {"4", "8", "9", "26"})
    @DisplayName("이외의 날짜에는 이벤트가 적용되지 않는다.")
    public void notApplicable(int day) {
        Order order = new Order(new VisitDay(day), null);

        assertFalse(specialDiscountEvent.isApplicableEventFor(order));
    }
}