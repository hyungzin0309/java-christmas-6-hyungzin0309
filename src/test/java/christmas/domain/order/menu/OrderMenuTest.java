package christmas.domain.order.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.HashMap;

class OrderMenuTest {

    private Map<Menu, Integer> validOrderMenus;

    @BeforeEach
    void setUp() {
        validOrderMenus = new HashMap<>();
        validOrderMenus.put(Menu.MUSHROOM_SOUP, 1);
        validOrderMenus.put(Menu.T_BONE_STEAK, 1);
    }

    @Test
    @DisplayName("유효한 주문 생성")
    void testValidOrderCreation() {
        assertDoesNotThrow(() -> new OrderMenu(validOrderMenus));
    }

    @Test
    @DisplayName("전체 가격 계산")
    void testTotalPriceCalculation() {
        OrderMenu orderMenu = new OrderMenu(validOrderMenus);
        int expectedTotalPrice = Menu.MUSHROOM_SOUP.getPrice() + Menu.T_BONE_STEAK.getPrice();
        assertEquals(expectedTotalPrice, orderMenu.getTotalPrice());
    }

    @Test
    @DisplayName("특정 타입별 수량 계산")
    void testOrderQuantityByTypeCalculation() {
        OrderMenu orderMenu = new OrderMenu(validOrderMenus);
        assertEquals(1, orderMenu.getOrderQuantityByType(MenuType.APPETIZER));
        assertEquals(1, orderMenu.getOrderQuantityByType(MenuType.MAIN));
    }

    @Test
    @DisplayName("빈 주문 오류")
    void testEmptyOrderError() {
        Map<Menu, Integer> emptyOrderMenus = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(emptyOrderMenus));
    }

    @Test
    @DisplayName("모든 메뉴가 음료인 주문 오류")
    void testAllDrinksOrderError() {
        Map<Menu, Integer> onlyDrinksOrderMenus = new HashMap<>();
        onlyDrinksOrderMenus.put(Menu.RED_WINE, 2);
        onlyDrinksOrderMenus.put(Menu.CHAMPAGNE, 1);

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(onlyDrinksOrderMenus));
    }

    @Test
    @DisplayName("최소 수량 미만 주문 오류")
    void testMinimumQuantityOrderError() {
        Map<Menu, Integer> invalidQuantityOrderMenus = new HashMap<>();
        invalidQuantityOrderMenus.put(Menu.MUSHROOM_SOUP, 0);
        invalidQuantityOrderMenus.put(Menu.T_BONE_STEAK, 1);

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(invalidQuantityOrderMenus));
    }

    @Test
    @DisplayName("총 수량 초과 주문 오류")
    void testTotalQuantityExceedOrderError() {
        Map<Menu, Integer> exceedTotalQuantityOrderMenus = new HashMap<>();
        exceedTotalQuantityOrderMenus.put(Menu.TAPAS, 5);
        exceedTotalQuantityOrderMenus.put(Menu.T_BONE_STEAK, 5);
        exceedTotalQuantityOrderMenus.put(Menu.ZERO_COLA, 10);
        exceedTotalQuantityOrderMenus.put(Menu.CHAMPAGNE, 1);

        assertThrows(IllegalArgumentException.class, () -> new OrderMenu(exceedTotalQuantityOrderMenus));
    }

}
