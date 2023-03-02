package ru.frolov.hippodrome.enums;

/**
 * Пол лошади.
 */
public enum Gender {
    MALE, FEMALE;

    /**
     * Получить случайный гендер.
     *
     * @return Гендер лошади.
     */
    public static Gender random() {
        return EnumHelper.randomElement(values());
    }
}
