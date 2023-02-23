package ru.frolov.hippodrome.enums;

/**
 * Пол лошади.
 */
public enum Gender {
    MALE, FEMALE;

    private static final double[] intervals = EnumElementSelection.split(values().length);

    /**
     * Получить случайный гендер.
     *
     * @return Гендер лошади.
     */
    public static Gender random() {
        return EnumElementSelection.randomElement(intervals, values());
    }
}
