package christmas.domain.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    @DisplayName("구매 금액이 만원 이상인 주문에 이벤트 적용")
    public void applicableOrder() {
        Event testEvent = new TestEvent();
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);

        when(order1.getTotalPrice()).thenReturn(10000);
        when(order2.getTotalPrice()).thenReturn(15000);

        assertTrue(testEvent.isApplicableFor(order1));
        assertTrue(testEvent.isApplicableFor(order2));
    }

    @Test
    @DisplayName("구매 금액이 만원 미만인 주문에 이벤트 적용되지 않는다.")
    public void notApplicableOrder() {
        Event testEvent = new TestEvent();
        Order order = mock(Order.class);

        when(order.getTotalPrice()).thenReturn(9000);

        assertFalse(testEvent.isApplicableFor(order));
    }

    // 이벤트 최소 적용 금액 테스트를 위한 테스트용 Event 구현체
    class TestEvent extends Event {

        @Override
        public EventType getEventType() {
            return null;
        }

        @Override
        public int getBenefitAmount(Order order) {
            return 0;
        }

        @Override
        protected boolean isApplicableEventFor(Order order) {
            return true;
        }
    }
}