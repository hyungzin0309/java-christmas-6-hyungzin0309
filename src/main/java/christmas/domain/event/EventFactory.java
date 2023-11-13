package christmas.domain.event;

import christmas.domain.event.discountEvent.events.ChristmasDDayDiscountEvent;
import christmas.domain.event.discountEvent.events.SpecialDiscountEvent;
import christmas.domain.event.discountEvent.events.WeekdayDiscountEvent;
import christmas.domain.event.discountEvent.events.WeekendDiscountEvent;
import christmas.domain.event.giftEvent.ChampagneGiftEvent;
import java.util.ArrayList;
import java.util.List;

public class EventFactory {

    public static List<Event> createEvents() {
        List<Event> allEvents = new ArrayList<>();
        allEvents.add(new ChristmasDDayDiscountEvent());
        allEvents.add(new SpecialDiscountEvent());
        allEvents.add(new WeekdayDiscountEvent());
        allEvents.add(new WeekendDiscountEvent());
        allEvents.add(new ChampagneGiftEvent());
        return allEvents;
    }
}
