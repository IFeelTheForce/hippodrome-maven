package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Погода.
 */
@Getter
@AllArgsConstructor
@ToString
public enum Weather {
    SUNNY("солнечно", 5),
    RAIN("дождь", 10),
    SNOW("снег", 15),
    CLOUDY("пасмурно", 0),
    FOG("туман", 7);

    /**
     * Концы отрезков распределения погод.
     */
    private static final double[] intervals = EnumElementSelection.split(values().length);
    /**
     * Название погоды на русском.
     */
    private final String cyrillic;
    /**
     * Коэффициент сложности погоды.
     */
    private final int difficultyFactor;

    /**
     * Получить случайную погоду.
     *
     * @return Погода.
     */
    public static Weather random() {
        return EnumElementSelection.randomElement(intervals, values());
    }
}
