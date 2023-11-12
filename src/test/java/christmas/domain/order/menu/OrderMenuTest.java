package christmas.domain.order.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.order.menu.Menu.*;
import static christmas.domain.order.menu.MenuType.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.HashMap;

class OrderMenuTest {

    private Map<Menu, Integer> validOrderMenus;

    @BeforeEach
    void setUp() {
        validOrderMenus = new HashMap<>();
        validOrderMenus.put(MUSHROOM_SOUP, 1);
        validOrderMenus.put(T_BONE_STEAK, 2);
        validOrderMenus.put(ZERO_COLA, 3);
    }

    @Test
    @DisplayName("유효한 주문 생성")
    void validOrderCreation() {
        assertDoesNotThrow(() -> new OrderMenu(validOrderMenus));
    }

    @Test
    @DisplayName("전체 가격 계산")
    void totalPriceCalculation() {
        OrderMenu orderMenu = new OrderMenu(validOrderMenus);
        int expectedTotalPrice =
                MUSHROOM_SOUP.getPrice() + (T_BONE_STEAK.getPrice() * 2) + (ZERO_COLA.getPrice() * 3);
        assertEquals(expectedTotalPrice, orderMenu.getTotalPrice());
    }

    @Test
    @DisplayName("특정 타입별 수량 계산")
    void orderQuantityByTypeCalculation() {
        OrderMenu orderMenu = new OrderMenu(validOrderMenus);
        assertEquals(1, orderMenu.getOrderQuantityByType(APPETIZER));
        assertEquals(2, orderMenu.getOrderQuantityByType(MAIN));
        assertEquals(3, orderMenu.getOrderQuantityByType(DRINK));
    }

    @Test
    @DisplayName("빈 주문 오류")
    void emptyOrderError() {
        Map<Menu, Integer> emptyOrderMenus = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(emptyOrderMenus));
    }

    @Test
    @DisplayName("모든 메뉴가 음료인 주문 오류")
    void allDrinksOrderError() {
        Map<Menu, Integer> onlyDrinksOrderMenus = new HashMap<>();
        onlyDrinksOrderMenus.put(RED_WINE, 2);
        onlyDrinksOrderMenus.put(CHAMPAGNE, 1);

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(onlyDrinksOrderMenus));
    }

    @Test
    @DisplayName("최소 수량 미만 주문 오류")
    void minimumQuantityOrderError() {
        Map<Menu, Integer> invalidQuantityOrderMenus = new HashMap<>();
        invalidQuantityOrderMenus.put(MUSHROOM_SOUP, 0);
        invalidQuantityOrderMenus.put(T_BONE_STEAK, 1);

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(invalidQuantityOrderMenus));
    }

    @Test
    @DisplayName("총 수량 초과 주문 오류")
    void totalQuantityExceedOrderError() {
        Map<Menu, Integer> exceedTotalQuantityOrderMenus = new HashMap<>();
        exceedTotalQuantityOrderMenus.put(TAPAS, 5);
        exceedTotalQuantityOrderMenus.put(T_BONE_STEAK, 5);
        exceedTotalQuantityOrderMenus.put(ZERO_COLA, 10);
        exceedTotalQuantityOrderMenus.put(CHAMPAGNE, 1);

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(exceedTotalQuantityOrderMenus));
    }

}
