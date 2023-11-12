package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.LocalDate;

public abstract class Event {

    protected static final int EVENT_YEAR = 2023;
    protected static final int EVENT_MONTH = 12;
    protected static final LocalDate EVENT_START_DAY = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 1);
    protected static final LocalDate CHRISTMAS_DAY = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 25);
    private static final int VALID_TOTAL_PRICE_MIN = 10000;

    public boolean isApplicableFor(Order order) {
        return order.getTotalPrice() >= VALID_TOTAL_PRICE_MIN && isApplicableEventFor(order);
    }

    public abstract EventType getEventType();

    public abstract int getBenefitAmount(Order order);

    protected abstract boolean isApplicableEventFor(Order order);

}
