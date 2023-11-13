package christmas.domain.event.giftEvent;

import christmas.domain.order.menu.Menu;
import christmas.domain.order.Order;

public class ChampagneGiftEvent extends GiftEvent {

    private static final int APPLICABLE_AMOUNT_MIN = 120000;
    private static final String EVENT_NAME = "증정 이벤트";

    @Override
    protected boolean isApplicableEventFor(Order order) {
        return order.getTotalPrice() >= APPLICABLE_AMOUNT_MIN;
    }

    @Override
    public Menu getGift() {
        return Menu.CHAMPAGNE;
    }

    @Override
    public String toString() {
        return EVENT_NAME;
    }
}
