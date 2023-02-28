package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

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
    WINSTON_C("Уинстон Си"),
    KNICKS_GO("Кникс Гоу"),
    ADAYAR("Адаяр");

    /**
     * Концы отрезков распределения имен лошадей.
     */
    private static final double[] intervals = EnumHelper.split(values().length);
    /**
     * Имя на русском.
     */
    private final String cyrillic;

    /**
     * Получить случайное имя.
     *
     * @return Имя лошади.
     */
    public static HorseName random() {
        return EnumHelper.randomElement(intervals, values());
    }

    /**
     * Получить случайное имя.
     *
     * @param exclude Исключенные имена.
     * @return Имя лошади.
     */
    public static HorseName random(String... exclude) {
        final var horseNames = Arrays.stream(values()).filter(horseName -> {
            for (String name : exclude) {
                if (name != null && name.equals(horseName.getCyrillic())) {
                    return false;
                }
            }
            return true;
        }).toList().toArray(new HorseName[0]);
        return EnumHelper.randomElement(EnumHelper.split(horseNames.length),
                horseNames);
    }
}
