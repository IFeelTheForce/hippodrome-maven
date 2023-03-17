package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.frolov.hippodrome.enums.utils.EnumUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Имя лошади.
 */
@Getter
@ToString
@AllArgsConstructor
public enum HorseName {
    ROACH("Плотва"),
    CORNFLOWER("Василек"),
    BUCEPHALUS("Буцефал"),
    BRICKS_AND_MORTAR("Брикс Энд Мортар"),
    MAXIMUM_SECURITY("Максимум Секьюрити"),
    COUNTRY_HOUSE("Кантри Хауз"),
    COVFEFE("Ковфеф"),
    VINO_ROSSO("Вино Россо"),
    MITOLE("Митол"),
    WINSTON_C("Уинстон Си"),
    KNICKS_GO("Кникс Гоу"),
    ADAYAR("Адаяр");

    /**
     * Имя на русском.
     */
    private final String cyrillic;

    /**
     * Получить случайное имя.
     *
     * @return Имя лошади.
     */
    public static HorseName random() {
        return EnumUtils.randomChoose(values());
    }

    /**
     * Получить случайное имя.
     *
     * @param exclude Исключенные имена.
     * @return Имя лошади.
     */
    public static HorseName random(String... exclude) {
        final var names = Arrays.stream(exclude)
                .filter(Objects::nonNull)
                .toList();
        final var horseNames = Arrays.stream(values())
                .filter(horseName -> !names.contains(horseName.cyrillic))
                .toList()
                .toArray(HorseName[]::new);
        return EnumUtils.randomChoose(horseNames);
    }
}
