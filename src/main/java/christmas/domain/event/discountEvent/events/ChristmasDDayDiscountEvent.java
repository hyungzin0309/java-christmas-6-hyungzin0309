package christmas.domain.event.discountEvent.events;

import christmas.domain.event.discountEvent.DiscountEvent;
import christmas.domain.order.Order;

public class ChristmasDDayDiscountEvent extends DiscountEvent {

    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int INCREASE_DISCOUNT_AMOUNT = 100;
    private static final String EVENT_NAME = "크리스마스 디데이 할인";

    @Override
    protected boolean isApplicableEventFor(Order order) {
        return order.isVisitDayBetween(EVENT_START_DAY, CHRISTMAS_DAY);
    }

    @Override
    public int getDiscountAmount(Order order) {
        long discountDay = order.getDaysDifferenceFromVisit(EVENT_START_DAY);
        return DEFAULT_DISCOUNT_AMOUNT + (INCREASE_DISCOUNT_AMOUNT * (int) discountDay);
    }

    @Override
    public String toString() {
        return EVENT_NAME;
    }
}
