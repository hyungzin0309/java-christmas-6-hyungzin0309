package christmas.view;

import static christmas.message.Message.BENEFIT_DETAIL;
import static christmas.message.Message.EVENT_BADGE;
import static christmas.message.Message.GIFT_MENU;
import static christmas.message.Message.MENU_NUM;
import static christmas.message.Message.NOTING;
import static christmas.message.Message.ORDER_DETAIL;
import static christmas.message.Message.RESULT;
import static christmas.message.Message.TOTAL_BENEFIT;
import static christmas.message.Message.TOTAL_PRICE_AFTER_DISCOUNT;
import static christmas.message.Message.TOTAL_PRICE_BEFORE_DISCOUNT;
import static christmas.message.Message.WON;

import christmas.domain.event.Event;
import christmas.domain.event.EventBadge;
import christmas.domain.order.Order;
import christmas.domain.order.menu.Menu;
import christmas.message.Message;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public static void printGreeting() {
        System.out.println(Message.WELCOME);
    }

    public static void printResultMessage(int month, int day) {
        System.out.printf(RESULT, month, day);
        System.out.println();
    }

    public static void printOrderDetail(String menuDescription) {
        System.out.println();
        System.out.println(ORDER_DETAIL);
        System.out.println(menuDescription);
    }

    public static void printTotalPriceBeforeDiscount(int totalPrice) {
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT);
        System.out.println(decimalFormat.format(totalPrice) + WON);
    }

    public static void printGifts(Map<Menu, Integer> gifts) {
        System.out.println();
        System.out.println(GIFT_MENU);
        if (gifts.isEmpty()) {
            printNothing();
        }
        if (gifts.size() > 0) {
            for (Menu gift : gifts.keySet()) {
                System.out.printf(MENU_NUM, gift.toString(), gifts.get(gift));
                System.out.println();
            }
        }
    }

    public static void printBenefitDetail(List<Event> appliedEvents, Order order) {
        System.out.println();
        System.out.println(BENEFIT_DETAIL);
        if (appliedEvents == null || appliedEvents.isEmpty()) {
            printNothing();
            return;
        }
        for (Event event : appliedEvents) {
            System.out.println(event.toString() + ": -" + decimalFormat.format(event.getBenefitAmount(order)) + WON);
        }
    }

    public static void printTotalBenefit(int totalBenefit) {
        System.out.println();
        System.out.println(TOTAL_BENEFIT);
        if (totalBenefit != 0) {
            System.out.println("-" + decimalFormat.format(totalBenefit) + WON);
        }
        if (totalBenefit == 0) {
            printNothing();
        }
    }

    public static void printPaymentAmount(int paymentAmount) {
        System.out.println();
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT);
        if (paymentAmount != 0) {
            System.out.println(decimalFormat.format(paymentAmount) + WON);
        }
        if (paymentAmount == 0) {
            printNothing();
        }
    }

    public static void printEventBadge(Optional<EventBadge> eventBadge) {
        System.out.println();
        System.out.println(EVENT_BADGE);
        if (eventBadge.isPresent()) {
            System.out.println(eventBadge.get().getName());
        }
        if (eventBadge.isEmpty()) {
            printNothing();
        }
    }

    public static void printNothing() {
        System.out.println(NOTING);
    }

}
