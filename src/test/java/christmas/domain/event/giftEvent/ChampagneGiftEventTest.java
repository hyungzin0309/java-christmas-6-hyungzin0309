package christmas.domain.event.giftEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.order.Order;
import christmas.domain.order.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChampagneGiftEventTest {

    private final ChampagneGiftEvent champagneGiftEvent = new ChampagneGiftEvent();

    @ParameterizedTest
    @ValueSource(strings = {"120000", "130000", "200000"})
    @DisplayName("120000원 이상 구매 시 이벤트 적용")
    public void applicableCondition(int totalPrice) {
        Order order = mock(Order.class);

        when(order.getTotalPrice()).thenReturn(totalPrice);

        assertTrue(champagneGiftEvent.isApplicableEventFor(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"89000", "110000"})
    @DisplayName("120000원 미만 구매 시 이벤트 적용되지 않음")
    public void notApplicableCondition(int totalPrice) {
        Order order = mock(Order.class);

        when(order.getTotalPrice()).thenReturn(totalPrice);

        assertFalse(champagneGiftEvent.isApplicableEventFor(order));
    }

    @Test
    @DisplayName("혜택 금액은 샴페인 가격과 같다.")
    public void benefitAmount() {
        Order eventApplicableOrder = mock(Order.class);

        when(eventApplicableOrder.getTotalPrice()).thenReturn(130000);

        assertEquals(Menu.CHAMPAGNE.getPrice(), champagneGiftEvent.getBenefitAmount(eventApplicableOrder));
    }


}