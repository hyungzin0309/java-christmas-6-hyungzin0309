package christmas.domain.event;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public enum EventBadge {
    NOTING("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minBenefitAmount;

    EventBadge(String name, int minBenefitAmount) {
        this.name = name;
        this.minBenefitAmount = minBenefitAmount;
    }

    public int getMinBenefitAmount() {
        return minBenefitAmount;
    }

    public String getName() {
        return name;
    }

    public static Optional<EventBadge> getByBenefit(int benefitAmount) {
        List<EventBadge> badges = Arrays.asList(EventBadge.values());
        return badges.stream()
                .sorted(Comparator.comparingInt(EventBadge::getMinBenefitAmount).reversed())
                .filter(eventBadge -> benefitAmount > eventBadge.getMinBenefitAmount())
                .findFirst();
    }

}
