package christmas;

import christmas.domain.event.Event;
import christmas.domain.event.EventFactory;
import christmas.domain.event.EventManager;
import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import christmas.domain.order.menu.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class EventPlannerApplication {

    public void execute() {
        OutputView.printGreeting();

        VisitDay visitDay = InputView.readVisitDay();
        OrderMenu orderMenu = InputView.readOrderMenu();
        Order order = new Order(visitDay, orderMenu);

        List<Event> allEvents = EventFactory.createEvents();
        EventManager manager = new EventManager(order, allEvents);

        printResult(order, manager);
    }

    private void printResult(Order order, EventManager eventManager) {
        OutputView.printResultMessage(order.getMonthOfVisitDay(), order.getDayOfVisitDay());
        OutputView.printOrderDetail(order.getOrderMenu().toString());
        OutputView.printTotalPriceBeforeDiscount(order.getTotalPrice());
        OutputView.printGifts(eventManager.getGifts());
        OutputView.printBenefitDetail(eventManager.getAppliedEvents(), order);
        OutputView.printTotalBenefit(eventManager.getTotalBenefitAmount());
        OutputView.printPaymentAmount(order.getTotalPrice() - eventManager.getTotalDiscountAmount());
        OutputView.printEventBadge(eventManager.getEventBadge());
    }

}
