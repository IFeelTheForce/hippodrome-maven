package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Имя лошади.
 */
@Getter
@ToString
@AllArgsConstructor
public enum HorseName {
    ROACH("Плотва"),
    CORNFLOWER("Василек"),
    BUCEPHALUS("Буцефал"),
    BRICKS_AND_MORTAR("Брикс Энд Мортар"),
    MAXIMUM_SECURITY("Максимум Секьюрити"),
    COUNTRY_HOUSE("Кантри Хауз"),
    COVFEFE("Ковфеф"),
    VINO_ROSSO("Вино Россо"),
    MITOLE("Митол"),
    WINSTON_C("Уинстон Си");

    /**
     * Концы отрезков распределения имен лошадей.
     */
    private static final double[] intervals = ElementSelection.split(values().length);
    /**
     * Имя на русском.
     */
    private final String cyrillic;

    /**
     * Получить случайное имя на русском.
     *
     * @return Имя лошади на русском.
     */
    public static HorseName random() {
        return ElementSelection.randomElement(intervals, values());
    }
}
