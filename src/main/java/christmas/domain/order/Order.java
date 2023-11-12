package christmas.domain.order;

import christmas.domain.order.menu.MenuType;
import christmas.domain.order.menu.OrderMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Order {

    private final VisitDay visitDay;
    private final OrderMenu orderMenu;

    public Order(VisitDay visitDay, OrderMenu orderMenu) {
        this.visitDay = visitDay;
        this.orderMenu = orderMenu;
    }

    public OrderMenu getOrderMenu() {
        return orderMenu;
    }

    public int getTotalPrice() {
        return orderMenu.getTotalPrice();
    }

    public int getMenuOrderQuantityByMenuType(MenuType type) {
        return orderMenu.getOrderQuantityByType(type);
    }

    public boolean isVisitDay(LocalDate date) {
        return visitDay.equals(date);
    }

    public boolean isVisitDayWeekend() {
        return visitDay.isWeekend();
    }

    public boolean isVisitDayBetween(LocalDate from, LocalDate to) {
        return visitDay.isDayBetween(from, to);
    }

    public long getDaysDifferenceFromVisit(LocalDate referenceDate) {
        return visitDay.getDaysDifferenceWith(referenceDate);
    }

    public DayOfWeek getDayOfWeekOfVisitDay() {
        return visitDay.getDayOfWeek();
    }

    public int getMonthOfVisitDay() {
        return visitDay.getMonth();
    }

    public int getDayOfVisitDay() {
        return visitDay.getDay();
    }

}
