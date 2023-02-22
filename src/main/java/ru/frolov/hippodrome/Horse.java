package ru.frolov.hippodrome;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.frolov.hippodrome.enums.AgePeriod;
import ru.frolov.hippodrome.enums.Color;
import ru.frolov.hippodrome.enums.Gender;
import ru.frolov.hippodrome.enums.HorseBreed;
import ru.frolov.hippodrome.enums.HorseName;

import java.util.Random;

/**
 * Лошадь.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Horse {
    /**
     * Максимальная скорость лошади в м/с.
     */
    public static final double MAX_SPEED_METERS_SECONDS = 24.4444D;
    /**
     * Дистанция, при которой увеличивается голод лошади.
     */
    public static final double HUNGER_INCREASE_DISTANCE = 50D;
    /**
     * Верхняя граница голода лошади.
     */
    public static final double HUNGER_UPPER_BOUND = 100D;
    /**
     * Нижняя граница голода лошади.
     */
    public static final double HUNGER_LOWER_BOUND = 0D;
    /**
     * Верхняя граница здоровья лошади.
     */
    public static final int HEALTH_UPPER_BOUND = 100;
    /**
     * Нижняя граница здоровья лошади.
     */
    public static final int HEALTH_LOWER_BOUND = 1;
    /**
     * Максимальный возраст лошади.
     */
    public static final int MAX_AGE = 30;
    /**
     * Минимальный возраст лошади.
     */
    public static final int MIN_AGE = 1;
    /**
     * Имя лошади.
     */
    private final String name;
    /**
     * Пол лошади.
     */
    private final Gender gender;
    /**
     * Порода лошади.
     */
    private final HorseBreed breed;
    /**
     * Цвет лошади.
     */
    private final Color color;
    /**
     * Период жизни лошади.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AgePeriod agePeriod;
    /**
     * Возраст лошади.
     */
    private int age;
    /**
     * Уровень голода лошади.
     */
    private double hunger = 0;
    /**
     * Уровень здоровья лошади.
     */
    private int health = 100;

    /**
     * Создать случайную лошадь.
     *
     * @return Лошадь.
     */
    public static Horse random() {
        return builder()
                .name(HorseName.random().getCyrillic())
                .gender(Gender.random())
                .breed(HorseBreed.random())
                .color(Color.random())
                .age(new Random().nextInt(1, 31))
                .build();

    }

    @Builder
    public Horse(String name, Gender gender, HorseBreed breed, Color color, int age) {
        this.name = name;
        this.gender = gender;
        this.breed = breed;
        this.color = color;
        setAge(age);
    }

    /**
     * Установить возраст лошади.
     *
     * @param age Возраст лошади.
     * @throws IllegalArgumentException Если возраст лошади меньше чем
     *                                  {@value #MIN_AGE} и больше чем {@value #MAX_AGE}.
     */
    public void setAge(int age) {
        if (age > MAX_AGE || age < MIN_AGE) {
            throw new IllegalArgumentException(String
                    .format("The age of the horse should be between %d and %d.", MIN_AGE, MAX_AGE));
        }
        this.age = age;
        agePeriod = AgePeriod.getPeriodByAge(age);
    }

    /**
     * Установить уровень голода лошади.
     *
     * @param hunger Уровень голода лошади.
     * @throws IllegalArgumentException Если голод лошади меньше чем
     *                                  {@value #HUNGER_LOWER_BOUND} и больше чем {@value #HUNGER_UPPER_BOUND}.
     */
    public void setHunger(double hunger) {
        if (hunger > HUNGER_UPPER_BOUND || hunger < HUNGER_LOWER_BOUND) {
            throw new IllegalArgumentException(String
                    .format("The hunger of the horse should be in the range from %.0f to %.0f.",
                            HUNGER_LOWER_BOUND, HUNGER_UPPER_BOUND));
        }
        this.hunger = hunger;
    }

    /**
     * Установить уровень здоровья лошади.
     *
     * @param health Уровень здоровья лошади.
     * @throws IllegalArgumentException Если здоровье лошади меньше чем
     *                                  {@value #HEALTH_LOWER_BOUND} и больше чем {@value #HEALTH_UPPER_BOUND}.
     */
    public void setHealth(int health) {
        if (health > HEALTH_UPPER_BOUND || health < HEALTH_LOWER_BOUND) {
            throw new IllegalArgumentException(String
                    .format("The health of the horse should be in the range from %d to %d.",
                            HEALTH_LOWER_BOUND, HEALTH_UPPER_BOUND));
        }
        this.health = health;
    }

    /**
     * Покормить лошади.
     */
    public void feed() {
        hunger = HUNGER_LOWER_BOUND;
    }

    /**
     * Вылечить лошадь.
     */
    public void treat() {
        health = HEALTH_UPPER_BOUND;
    }
}
