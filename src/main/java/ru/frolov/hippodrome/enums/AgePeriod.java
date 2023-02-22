package ru.frolov.hippodrome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Период жизни лошади.
 */
@Getter
@AllArgsConstructor
@ToString
public enum AgePeriod {
    DEVELOPING(0.95),
    PRIME(1.0),
    PAST_PRIME(0.9),
    OLD(0.8);

    /**
     * Верхняя граница {@link #DEVELOPING}.
     */
    private static final int DEVELOPING_BOUND = 7;
    /**
     * Верхняя граница {@link #PRIME}.
     */
    private static final int PRIME_BOUND = 15;
    /**
     * Верхняя граница {@link #PAST_PRIME}.
     */
    private static final int PAST_PRIME_BOUND = 19;
    /**
     * Коэффициент силы лошади от периода жизни
     */
    private final double powerCoeff;

    /**
     * Получить период жизни лошади по ее возрасту.
     *
     * @param age Возраст лошади.
     * @return {@link AgePeriod} жизни лошади.
     * @throws IllegalArgumentException Если возраст лошади меньше единицы.
     */
    public static AgePeriod getPeriodByAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("The age of the horse cannot be less than 1.");
        }
        if (age <= DEVELOPING_BOUND) {
            return DEVELOPING;
        } else if (age <= PRIME_BOUND) {
            return PRIME;
        } else if (age <= PAST_PRIME_BOUND) {
            return PAST_PRIME;
        }
        return OLD;
    }
}