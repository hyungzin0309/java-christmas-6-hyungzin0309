package christmas.domain.event.discountEvent.events;

import christmas.domain.event.discountEvent.DiscountEvent;
import christmas.domain.order.Order;
import java.time.DayOfWeek;

public class SpecialDiscountEvent extends DiscountEvent {

    private static final int DISCOUNT_AMOUNT = 1000;
    private static final String EVENT_NAME = "특별 할인";

    @Override
    protected boolean isApplicableEventFor(Order order) {
        return order.getDayOfWeekOfVisitDay() == DayOfWeek.SUNDAY || order.isVisitDay(CHRISTMAS_DAY);
    }

    @Override
    public int getDiscountAmount(Order order) {
        return DISCOUNT_AMOUNT;
    }

    @Override
    public String toString() {
        return EVENT_NAME;
    }
}
