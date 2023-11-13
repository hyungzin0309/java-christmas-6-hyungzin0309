package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

class VisitDayTest {

    @Test
    @DisplayName("유효한 방문일 생성")
    void validVisitDayCreation() {
        VisitDay visitDay = new VisitDay(25);
        assertEquals(12, visitDay.getMonth());
        assertEquals(25, visitDay.getDay());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    @DisplayName("잘못된 방문일 생성 오류")
    void invalidVisitDayCreationError(int input) {
        assertThrows(IllegalArgumentException.class, () -> new VisitDay(input));
    }

    @Test
    @DisplayName("요일 가져오기")
    void getDayOfWeekTest() {
        VisitDay friday = new VisitDay(1); // 금요일 (2023-12-1)
        VisitDay wednesday = new VisitDay(13); // 수요일 (2023-12-13)
        VisitDay monday = new VisitDay(25); // 월요일 (2023-12-25)

        assertEquals(DayOfWeek.FRIDAY, friday.getDayOfWeek());
        assertEquals(DayOfWeek.WEDNESDAY, wednesday.getDayOfWeek());
        assertEquals(DayOfWeek.MONDAY, monday.getDayOfWeek());
    }

    @ParameterizedTest
    @ValueSource(strings = {"8", "16", "29"})
    @DisplayName("금요일과 토요일은 주말이다.")
    void weekendCheckTest(int day) {
        VisitDay fridayOrSaturday = new VisitDay(day);

        assertTrue(fridayOrSaturday.isWeekend());
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "11", "31"})
    @DisplayName("금요일과 토요일을 제외한 모든 요일들은 평일이다.")
    void weekdayCheckTest(int day) {
        VisitDay weekday = new VisitDay(day);

        assertFalse(weekday.isWeekend());
    }

    @Test
    @DisplayName("날짜 범위 검증")
    void dateRangeCheckTest() {
        LocalDate from = LocalDate.of(2023, 12, 20);
        LocalDate to = LocalDate.of(2023, 12, 30);

        VisitDay insideDay = new VisitDay(25); // 기준 범위 사이의 날짜
        VisitDay startDay = new VisitDay(20); // 기준 범위 시작일과 동일
        VisitDay endDay = new VisitDay(30); // 범위 종료일과 동일
        VisitDay outsideDay = new VisitDay(31); // 범위 밖의 날짜

        assertTrue(insideDay.isDayBetween(from, to));
        assertTrue(startDay.isDayBetween(from, to));
        assertTrue(endDay.isDayBetween(from, to));
        assertFalse(outsideDay.isDayBetween(from, to));
    }

    @Test
    @DisplayName("날짜 차이 계산 테스트")
    void dateDifferenceCalculationTest() {
        VisitDay visitDay = new VisitDay(25);
        LocalDate beforeDate = LocalDate.of(2023, 12, 20);
        LocalDate sameDate = LocalDate.of(2023, 12, 25);
        LocalDate afterDate = LocalDate.of(2023, 12, 30);

        assertEquals(5, visitDay.getDaysDifferenceWith(beforeDate));
        assertEquals(0, visitDay.getDaysDifferenceWith(sameDate)); // 참조 날짜와 동일
        assertEquals(-5, visitDay.getDaysDifferenceWith(afterDate)); // 참조 날짜가 이후
    }

    @Test
    @DisplayName("날짜 동등성 테스트")
    void dateEqualityTest() {
        VisitDay visitDay = new VisitDay(25);
        LocalDate localDate = LocalDate.of(2023, 12, 25);

        assertTrue(visitDay.equals(localDate));
    }
}
