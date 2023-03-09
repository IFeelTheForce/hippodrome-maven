package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.frolov.hippodrome.enums.utils.EnumUtils;

/**
 * Время года.
 */
@Getter
@ToString
@AllArgsConstructor
public enum Season {
    SUMMER("лето", 0),
    AUTUMN("осень", 10),
    WINTER("зима", 15),
    SPRING("весна", 5);

    /**
     * Название времени года на русском.
     */
    private final String cyrillic;
    /**
     * Коэффициент сложности времени года.
     */
    private final int difficultyFactor;

    /**
     * Получить случайное время года.
     *
     * @return Время года.
     */
    public static Season random() {
        return EnumUtils.randomChoose(values());
    }
}
