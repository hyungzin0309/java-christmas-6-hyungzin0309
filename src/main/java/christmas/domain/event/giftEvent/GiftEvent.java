package christmas.domain.event.giftEvent;

import static christmas.domain.event.EventType.GIFT;

import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.order.menu.Menu;
import christmas.domain.order.Order;

public abstract class GiftEvent extends Event {

    public EventType getEventType() {
        return GIFT;
    }

    @Override
    public int getBenefitAmount(Order order) {
        return getGift().getPrice();
    }

    public abstract Menu getGift();
}
