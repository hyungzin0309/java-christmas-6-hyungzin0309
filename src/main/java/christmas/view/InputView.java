package christmas.view;

import static camp.nextstep.edu.missionutils.Console.*;
import static christmas.message.ErrorMessage.INVALID_DAY_INPUT;

import christmas.domain.order.VisitDay;
import christmas.message.Message;
import christmas.utils.ConvertUtils;

public class InputView {

    public static VisitDay readVisitDay() {
        System.out.println(Message.INPUT_DAY);
        while (true) {
            try {
                int day = ConvertUtils.convertToNum(readLine());
                return new VisitDay(day);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_DAY_INPUT);
            }
        }
    }

}
