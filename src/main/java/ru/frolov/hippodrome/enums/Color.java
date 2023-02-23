package ru.frolov.hippodrome.enums;

/**
 * Цвет лошади.
 */
public enum Color {
    BLACK, BROWN, WHITE, BEIGE, RED;
    /**
     * Концы отрезков распределения цветов лошадей.
     */
    private static final double[] intervals = EnumElementSelection.split(values().length);

    /**
     * Получить случайный цвет.
     *
     * @return Порода лошади.
     */
    public static Color random() {
        return EnumElementSelection.randomElement(intervals, values());
    }
}
