package ru.frolov.hippodrome;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.frolov.hippodrome.enums.Coverage;
import ru.frolov.hippodrome.enums.Title;

import java.util.Random;

/**
 * Ипподром.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Hippodrome {
    private static final double MAX_DISTANCE_METERS = 1500D;
    /**
     * Минимальная дистанция ипподрома в метрах.
     */
    public static final double MIN_DISTANCE_METERS = 30D;
    /**
     * Название ипподрома.
     */
    private String title;
    /**
     * Покрытие ипподрома.
     */
    private Coverage coverage;
    /**
     * Дистанция ипподрома в метрах.
     */
    private double distanceMeters;

    /**
     * Создать случайный ипподром.
     *
     * @return Ипподром.
     */
    public static Hippodrome random() {
        return builder()
                .title(Title.random().getCyrillic())
                .coverage(Coverage.random())
                .distanceMeters(new Random().nextDouble(MIN_DISTANCE_METERS,
                        MAX_DISTANCE_METERS + 1D))
                .build();
    }

    public Hippodrome(String title, Coverage coverage, double distanceMeters) {
        this.title = title;
        this.coverage = coverage;
        setDistanceMeters(distanceMeters);
    }

    /**
     * Установить дистанцию ипподрома.
     *
     * @param distanceMeters Дистанция ипподрома в метрах.
     * @throws IllegalArgumentException Если дистанция ипподрома меньше
     *                                  чем {@value #MIN_DISTANCE_METERS} метров.
     */
    public void setDistanceMeters(double distanceMeters) {
        if (distanceMeters < MIN_DISTANCE_METERS) {
            throw new IllegalArgumentException(String
                    .format("The distance of the hippodrome cannot be less than %.0f. meters",
                            MIN_DISTANCE_METERS));
        }
        this.distanceMeters = distanceMeters;
    }
}
