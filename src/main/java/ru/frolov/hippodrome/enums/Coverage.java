package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Покрытие ипподрома.
 */
@ToString
@Getter
@AllArgsConstructor
public enum Coverage {
    LOOSE("рыхлое", 30),
    SOFT("мягкое", 4),
    SOLID("твердое", 8),
    ROCKY("каменистое", 15),
    NORMAL("нормальное", 0);

    /**
     * Название покрытия на русском.
     */
    private final String cyrillic;
    /**
     * Коэффициент сложности покрытия.
     */
    private final int difficultyFactor;

    /**
     * Получить случайное покрытие.
     *
     * @return Покрытие ипподрома.
     */
    public static Coverage random() {
        return EnumHelper.randomElement(values());
    }
}
