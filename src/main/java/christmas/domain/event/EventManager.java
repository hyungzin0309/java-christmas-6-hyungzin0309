package christmas.domain.event;

import christmas.domain.event.giftEvent.GiftEvent;
import christmas.domain.order.Order;
import christmas.domain.order.menu.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventManager {

    private final List<Event> appliedEvents;
    private final Order order;

    public EventManager(Order order, List<Event> allEvents) {
        this.order = order;
        this.appliedEvents = allEvents.stream()
                .filter(event -> event.isApplicableFor(order))
                .collect(Collectors.toList());
    }

    public List<Event> getAppliedEvents() {
        return appliedEvents;
    }
    
    public int getTotalDiscountAmount() {
        return appliedEvents.stream()
                .filter(event -> event.getEventType() == EventType.DISCOUNT)
                .mapToInt(event -> event.getBenefitAmount(order))
                .sum();
    }

    public int getTotalBenefitAmount() {
        return appliedEvents.stream()
                .mapToInt(event -> event.getBenefitAmount(order))
                .sum();
    }

    public Map<Menu, Integer> getGifts() {
        Map<Menu, Integer> gifts = new HashMap<>();
        for (Event event : appliedEvents) {
            if (event.getEventType() == EventType.GIFT) {
                Menu gift = ((GiftEvent) event).getGift();
                gifts.put(gift, gifts.getOrDefault(gift, 0) + 1);
            }
        }
        return gifts;
    }

    public Optional<EventBadge> getEventBadge() {
        return EventBadge.getByBenefit(getTotalBenefitAmount());
    }

}
