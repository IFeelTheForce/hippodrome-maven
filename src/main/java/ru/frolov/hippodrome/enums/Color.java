package ru.frolov.hippodrome.enums;

import ru.frolov.hippodrome.enums.utils.EnumUtils;

/**
 * Цвет лошади.
 */
public enum Color {
    BLACK, BROWN, WHITE, BEIGE, RED;

    /**
     * Получить случайный цвет.
     *
     * @return Порода лошади.
     */
    public static Color random() {
        return EnumUtils.randomChoose(values());
    }
}
