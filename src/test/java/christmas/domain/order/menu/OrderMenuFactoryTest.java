package christmas.domain.order.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuFactoryTest {

    @Test
    void 유효한_입력() {
        String input = "양송이수프-1,티본스테이크-1";
        Map<Menu, Integer> orderMenus = OrderMenuFactory.create(input);

        assertAll(
                () -> assertEquals(1, orderMenus.get(Menu.MUSHROOM_SOUP)),
                () -> assertEquals(1, orderMenus.get(Menu.T_BONE_STEAK))
        );
    }

    @Test
    void 잘못된_메뉴_입력() {
        String invalidInput = "잘못된메뉴-1,양송이수프-2";
        assertThrows(IllegalArgumentException.class, () -> OrderMenuFactory.create(invalidInput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "----", "양송이수프-둘"})
    void 잘못된_형식의_입력(String input) {
        assertThrows(IllegalArgumentException.class, () -> OrderMenuFactory.create(input));
    }

    @Test
    void 중복_메뉴_입력() {
        String duplicateInput = "양송이수프-1,양송이수프-2";
        assertThrows(IllegalArgumentException.class, () -> OrderMenuFactory.create(duplicateInput));
    }
}
