package christmas.domain.order.menu;

import static christmas.domain.order.menu.MenuType.*;
import static christmas.message.ErrorMessage.INVALID_MENU_INPUT;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),
    ZERO_COLA("제로콜라", 3_000, DRINK),
    RED_WINE("레드와인", 60_000, DRINK),
    CHAMPAGNE("샴페인", 25_000, DRINK);

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public static Menu getBy(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException(INVALID_MENU_INPUT);
    }

    @Override
    public String toString() {
        return name;
    }

}
