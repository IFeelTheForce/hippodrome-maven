package ru.frolov.hippodrome.enums;

import java.util.Random;

/**
 * Вспомогательный класс для случайного выбора элемента перечисления.
 */
final class EnumHelper {
    /**
     * Выбрать случайный элемент массива.
     *
     * @param values Массив значений.
     * @param <T>    Тип элементов массива.
     * @return Выбранный случайным образом элемент.
     */
    public static <T> T randomElement(T[] values) {
        return values[new Random().nextInt(values.length)];
    }
}
