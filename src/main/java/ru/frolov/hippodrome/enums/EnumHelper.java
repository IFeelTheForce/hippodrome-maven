package ru.frolov.hippodrome.enums;

/**
 * Вспомогательный класс для случайного выбора элемента перечисления.
 */
final class EnumHelper {

    /**
     * Разбить отрезок [0, 1] на несколько одинаковых частей.
     *
     * @param segmentCount Количество частей.
     * @return Массив концов отрезков.
     */
    public static double[] split(int segmentCount) {
        final var segmentLength = 1.0 / segmentCount;
        final double[] spacing = new double[segmentCount + 1];
        for (int i = 0; i < spacing.length; i++) {
            spacing[i] = segmentLength * i;
        }
        return spacing;
    }

    /**
     * Выбрать случайный элемент массива.
     *
     * @param intervals Концы отрезков распределения элементов массива.
     * @param values    Массив значений.
     * @param <T>       Тип элементов массива.
     * @return Выбранный случайным образом элемент.
     */
    public static <T> T randomElement(double[] intervals, T[] values) {
        final var determiner = Math.random();
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i] < determiner && determiner <= intervals[i + 1]) {
                return values[i];
            }
        }
        return values[0];
    }
}
