package ru.frolov.hippodrome.enums;

import ru.frolov.hippodrome.enums.utils.EnumUtils;

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
        return EnumUtils.randomChoose(values());
    }
}
