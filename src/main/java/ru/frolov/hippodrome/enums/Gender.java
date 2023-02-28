package ru.frolov.hippodrome.enums;

/**
 * Пол лошади.
 */
public enum Gender {
    MALE, FEMALE;

    private static final double[] intervals = EnumHelper.split(values().length);

    /**
     * Получить случайный гендер.
     *
     * @return Гендер лошади.
     */
    public static Gender random() {
        return EnumHelper.randomElement(intervals, values());
    }
}
