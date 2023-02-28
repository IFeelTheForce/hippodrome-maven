package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Событие.
 */
@Getter
@AllArgsConstructor
@ToString
public enum Event {
    NOTHING("ничего", 0.9, 0),
    TWISTED_LEG("подвернутая нога", 0.04, 4),
    STRETCHING("растяжение", 0.01, 17),
    DISLOCATION("вывих", 0.01, 12),
    FALL("падение", 0.025, 6),
    FRACTURE("перелом", 0.005, 90);

    /**
     * Концы отрезков распределения вероятностей событий.
     */
    private static final double[] intervals;
    /**
     * Название события на русском.
     */
    private final String cyrillic;
    /**
     * Вероятность возникновения события.
     */
    private final double probability;
    /**
     * Штраф к здоровью лошади от события.
     */
    private final int penalty;

    static {
        intervals = new double[values().length + 1];
        for (int i = 1; i < intervals.length; i++) {
            intervals[i] = intervals[i - 1] + values()[i - 1].getProbability();
        }
    }

    /**
     * Получить событие случайным образом.
     *
     * @return Выбранное случайным образом событие.
     */
    public static Event random() {
        int index = 0;
        double determiner = Math.random();
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i] < determiner && determiner <= intervals[i + 1]) {
                index = i;
                break;
            }
        }
        return values()[index];
    }
}
