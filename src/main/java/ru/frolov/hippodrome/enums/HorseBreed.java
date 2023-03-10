package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.frolov.hippodrome.enums.utils.EnumUtils;

/**
 * Порода лошади.
 */
@Getter
@ToString
@AllArgsConstructor
public enum HorseBreed {
    ANDALUSIAN("андалузская", 75, 75, Season.SUMMER),
    TENNESSEE("теннесси", 87, 60, Season.SPRING),
    MORGAN("морган", 60, 95, Season.AUTUMN),
    PAINTHORSE("пейнтхорс", 100, 47, Season.SUMMER),
    ALTAI("алтайская", 55, 100, Season.WINTER);

    /**
     * Название породы на русском.
     */
    private final String cyrillic;
    /**
     * Сила породы.
     */
    private final int power;
    /**
     * Выносливость породы.
     */
    private final int endurance;
    /**
     * Комфортное время года.
     */
    private final Season comfortableSeason;

    /**
     * Получить случайную породу лошади.
     *
     * @return Порода лошади.
     */
    public static HorseBreed random() {
        return EnumUtils.randomChoose(values());
    }
}
