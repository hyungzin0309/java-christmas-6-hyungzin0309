package christmas.domain.order.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuOrdersConverterTest {

    @Test
    @DisplayName("유효한 입력")
    void validInput() {
        String input = "양송이수프-1,티본스테이크-1";
        Map<Menu, Integer> orderMenus = MenuOrdersConverter.convertToMenuOrders(input);

        assertAll(
                () -> assertEquals(1, orderMenus.get(Menu.MUSHROOM_SOUP)),
                () -> assertEquals(1, orderMenus.get(Menu.T_BONE_STEAK))
        );
    }

    @Test
    @DisplayName("잘못된 메뉴 입력")
    void invalidMenuInput() {
        String invalidInput = "잘못된메뉴-1,양송이수프-2";
        assertThrows(IllegalArgumentException.class, () -> MenuOrdersConverter.convertToMenuOrders(invalidInput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "----", "양송이수프-둘"})
    @DisplayName("잘못된 형식 입력")
    void invalidForm(String input) {
        assertThrows(IllegalArgumentException.class, () -> MenuOrdersConverter.convertToMenuOrders(input));
    }

    @Test
    @DisplayName("중복된 메뉴 입력")
    void menuDuplicated() {
        String duplicateInput = "양송이수프-1,양송이수프-2";
        assertThrows(IllegalArgumentException.class, () -> MenuOrdersConverter.convertToMenuOrders(duplicateInput));
    }
}
