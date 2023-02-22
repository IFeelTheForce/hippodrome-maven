package ru.frolov.hippodrome.enums;

/**
 * Пол лошади.
 */
public enum Gender {
    MALE, FEMALE;

    private static final double[] intervals = ElementSelection.split(values().length);

    /**
     * Получить случайный гендер.
     *
     * @return Гендер лошади.
     */
    public static Gender random() {
        return ElementSelection.randomElement(intervals, values());
    }
}
