package christmas.domain.order;

import static christmas.message.ErrorMessage.INVALID_DAY_INPUT;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VisitDay {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    private final LocalDate visitDay;

    public VisitDay(int day) {
        try {
            visitDay = LocalDate.of(YEAR, MONTH, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_DAY_INPUT);
        }
    }

    public int getMonth() {
        return visitDay.getMonth().getValue();
    }

    public int getDay() {
        return visitDay.getDayOfMonth();
    }

    public DayOfWeek getDayOfWeek() {
        return visitDay.getDayOfWeek();
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isDayBetween(LocalDate from, LocalDate to) {
        return !visitDay.isBefore(from) && !visitDay.isAfter(to);
    }

    public long getDaysDifferenceWith(LocalDate referenceDate) {
        return ChronoUnit.DAYS.between(referenceDate, visitDay);
    }

    public boolean equals(LocalDate localDate) {
        return visitDay.equals(localDate);
    }

}
