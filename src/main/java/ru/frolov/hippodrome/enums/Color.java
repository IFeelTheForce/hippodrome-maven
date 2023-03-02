package ru.frolov.hippodrome.enums;

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
        return EnumHelper.randomElement(values());
    }
}
