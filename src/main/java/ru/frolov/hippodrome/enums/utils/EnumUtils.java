package ru.frolov.hippodrome.enums.utils;

import java.util.Random;

/**
 * Вспомогательный класс для случайного выбора элемента перечисления.
 */
final public class EnumUtils {
    /**
     * Выбрать случайный элемент массива.
     *
     * @param values Массив значений.
     * @param <T>    Тип элементов массива.
     * @return Выбранный случайным образом элемент.
     */
    public static <T> T randomChoose(T[] values) {
        return values[new Random().nextInt(values.length)];
    }
}
