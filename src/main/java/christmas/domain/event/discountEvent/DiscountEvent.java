package christmas.domain.event.discountEvent;

import static christmas.domain.event.EventType.DISCOUNT;

import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;

public abstract class DiscountEvent extends Event {

    public EventType getEventType() {
        return DISCOUNT;
    }

    @Override
    public int getBenefitAmount(Order order) {
        return getDiscountAmount(order);
    }

    public abstract int getDiscountAmount(Order order);
}
