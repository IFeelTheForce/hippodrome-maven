package ru.frolov.hippodrome.enums;

/**
 * Цвет лошади.
 */
public enum Color {
    BLACK, BROWN, WHITE, BEIGE, RED;
    /**
     * Концы отрезков распределения цветов лошадей.
     */
    private static final double[] intervals = ElementSelection.split(values().length);

    /**
     * Получить случайный цвет.
     *
     * @return Порода лошади.
     */
    public static Color random() {
        return ElementSelection.randomElement(intervals, values());
    }
}
