package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.frolov.hippodrome.enums.utils.EnumUtils;

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
        return EnumUtils.randomChoose(values());
    }
}
