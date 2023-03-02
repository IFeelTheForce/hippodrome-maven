package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Название ипподрома.
 */
@Getter
@ToString
@AllArgsConstructor
public enum Title {
    MEYDAN("Мейдан"),
    EPSOM("Эпсом"),
    VINCENNES("Венсенский"),
    FLEMINGTON("Флемингтон"),
    SOLVALLA("Солвалла");

    /**
     * Название на русском.
     */
    private final String cyrillic;

    /**
     * Получить случайное название.
     *
     * @return Название ипподрома.
     */
    public static Title random() {
        return EnumHelper.randomElement(values());
    }
}
