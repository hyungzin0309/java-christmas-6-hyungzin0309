package christmas.domain.event.discountEvent.events;

import christmas.domain.event.discountEvent.DiscountEvent;
import christmas.domain.order.menu.MenuType;
import christmas.domain.order.Order;

public class WeekdayDiscountEvent extends DiscountEvent {

    private static final int DISCOUNT_AMOUNT = 2023;
    private static final String EVENT_NAME = "평일 할인";

    @Override
    protected boolean isApplicableEventFor(Order order) {
        return !order.isVisitDayWeekend();
    }

    @Override
    public int getDiscountAmount(Order order) {
        return DISCOUNT_AMOUNT * order.getMenuOrderQuantityByMenuType(MenuType.DESSERT);
    }

    @Override
    public String toString() {
        return EVENT_NAME;
    }
}
