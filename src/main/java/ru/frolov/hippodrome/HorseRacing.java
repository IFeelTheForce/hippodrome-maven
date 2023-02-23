package ru.frolov.hippodrome;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.frolov.hippodrome.enums.Event;
import ru.frolov.hippodrome.enums.HorseName;
import ru.frolov.hippodrome.enums.Season;
import ru.frolov.hippodrome.enums.Weather;
import ru.frolov.hippodrome.exceptions.IllegalOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Скачки лошадей.
 */
@Getter
@Setter
@EqualsAndHashCode
public class HorseRacing {
    /**
     * Ключ коэффициента силы лошади.
     */
    private static final String POWER_COEFF = "power coeff";
    /**
     * Ключ коэффициента выносливости лошади.
     */
    private static final String ENDURANCE_COEFF = "endurance coeff";
    /**
     * Ключ коэффициента возраста лошади.
     */
    private static final String AGE_COEFF = "age coeff";
    /**
     * Ключ коэффициента комфортного времени года лошади.
     */
    private static final String SEASON_COEFF = "season coeff";
    /**
     * Делитель для коэффициента силы лошади.
     */
    private static final double POWER_COEFF_DIVIDER = 100D;
    /**
     * Делитель для коэффициента выносливости лошади.
     */
    private static final double ENDURANCE_COEFF_DIVIDER = 100D;
    /**
     * Делитель для коэффициента погоды.
     */
    private static final double WEATHER_COEFF_DIVIDER = 100D;
    /**
     * Делитель для коэффициента времени года.
     */
    private static final double SEASON_COEFF_DIVIDER = 100D;
    /**
     * Делитель для коэффициента сложности покрытия ипподрома.
     */
    private static final double COVERAGE_COEFF_DIVIDER = 100D;
    /**
     * Делитель для коэффициента голода лошади.
     */
    private static final double HUNGER_COEFF_DIVIDER = 100D;
    /**
     * Делитель для коэффициента здоровья лошади.
     */
    private static final double HEALTH_COEFF_DIVIDER = 100D;
    /**
     * Базовый коэффициент сложности покрытия ипподрома.
     */
    private static final double COVERAGE_COEFF_BASE = 1D;
    /**
     * Базовый коэффициент погоды.
     */
    private static final double WEATHER_COEFF_BASE = 1D;
    /**
     * Базовый коэффициент времени года.
     */
    private static final double SEASON_COEFF_BASE = 1D;
    /**
     * Улучшение к коэффициенту времени года комфортного для лошади.
     */
    private static final double SEASON_HORSE_BUFF = 0.05D;
    /**
     * Вероятность возникновения случайного события.
     */
    private static final double EVENT_APPEAR_PROBABILITY = 0.05D;
    /**
     * Коэффициент увеличения голода лошади.
     */
    private static final double HUNGER_INCREASE_COEFF = 2D;
    /**
     * Максимальное количество случайных событий,
     * которые могут произойти с лошадью во время забега.
     */
    private static final int MAX_EVENT_NUMBER = 3;
    /**
     * Число лошадей в скачках по умолчанию.
     */
    public static final int DEFAULT_HORSE_COUNT = 5;
    /**
     * Ипподром, на котором происходят скачки
     */
    private Hippodrome hippodrome;
    /**
     * Лошади, которые участвуют в скачках. Представляет собой хэш-таблицу,
     * ключом в которой выступает лошадь, а в качестве значения используется
     * еще одна хэш-таблица с названиями коэффициентов и их значениями --
     * ключи и значения соответственно.
     */
    private HashMap<Horse, HashMap<String, Double>> horses;
    /**
     * Погода, при которой происходят скачки.
     */
    private Weather weather;
    /**
     * Время года, при котором происходят скачки.
     */
    private Season season;
    /**
     * Коэффициент сложности покрытия ипподрома.
     */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @EqualsAndHashCode.Exclude
    private double coverageCoeff;
    /**
     * Коэффициент сложности погоды, при которой происходят скачки.
     */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @EqualsAndHashCode.Exclude
    private double weatherCoeff;

    /**
     * Создать случайные скачки.
     *
     * @return Скачки.
     */
    public static HorseRacing random() {
        return random(DEFAULT_HORSE_COUNT);
    }

    /**
     * Создать случайные скачки.
     *
     * @param horseCount Количество лошадей в скачках.
     * @return Скачки.
     */
    public static HorseRacing random(int horseCount) {
        if (horseCount < 1) {
            throw new IllegalArgumentException("The number of horses cannot be less than one.");
        }
        final var horses = new HashMap<Horse, String>(horseCount);
        for (int i = 0; i < horseCount; i++) {
            final var horse = Horse.random(HorseName.random(horses.values()
                    .toArray(new String[0])).getCyrillic());
            horses.put(horse, horse.getName());
        }
        return builder()
                .hippodrome(Hippodrome.random())
                .horses(horses.keySet().toArray(new Horse[0]))
                .weather(Weather.random())
                .season(Season.random())
                .build();
    }

    /**
     * @param hippodrome Ипподром.
     * @param weather    Погода.
     * @param season     Время года.
     * @param horses     Лошади, которые будут участвовать в скачках.
     */
    public HorseRacing(Hippodrome hippodrome, Weather weather, Season season, Horse... horses) {
        this.hippodrome = hippodrome;
        this.weather = weather;
        this.season = season;
        initHorses(horses);
        evaluateCoverageCoeff();
        evaluateWeatherCoeff();
    }

    /**
     * Инициализировать {@link #horses}.
     *
     * @param horses Лошади, которые будут участвовать в скачках.
     */
    private void initHorses(Horse... horses) {
        this.horses = new HashMap<>();
        addHorses(horses);
    }

    /**
     * Метод расчета коэффициентов, относящихся к лошади.
     *
     * @param horse Лошадь, для которой будут считаться коэффициенты.
     * @return Хэш-таблица названий и значений коэффициентов.
     */
    private HashMap<String, Double> evaluateHorseCoeffs(Horse horse) {
        final var powerCoeff = horse.getBreed().getPower() / POWER_COEFF_DIVIDER;
        final var enduranceCoeff = horse.getBreed().getEndurance() / ENDURANCE_COEFF_DIVIDER;
        final var ageCoeff = horse.getAgePeriod().getPowerCoeff();
        final var coeffs = new HashMap<String, Double>();
        coeffs.put(POWER_COEFF, powerCoeff);
        coeffs.put(ENDURANCE_COEFF, enduranceCoeff);
        coeffs.put(AGE_COEFF, ageCoeff);
        coeffs.put(SEASON_COEFF, evaluateSeasonCoeff(horse));
        return coeffs;
    }

    /**
     * Вычислить значение {@link #coverageCoeff}.
     */
    private void evaluateCoverageCoeff() {
        coverageCoeff = COVERAGE_COEFF_BASE -
                hippodrome.getCoverage().getDifficultyFactor() / COVERAGE_COEFF_DIVIDER;
    }

    /**
     * Вычислить значение {@link #weatherCoeff}.
     */
    private void evaluateWeatherCoeff() {
        weatherCoeff = WEATHER_COEFF_BASE - weather.getDifficultyFactor() / WEATHER_COEFF_DIVIDER;
    }

    /**
     * Вычислить значение коэффициента времени года для лошади.
     */
    private double evaluateSeasonCoeff(Horse horse) {
        final var seasonCoeff = SEASON_COEFF_BASE - season.getDifficultyFactor() / SEASON_COEFF_DIVIDER;
        if (season == horse.getBreed().getComfortableSeason()) {
            return Math.min(seasonCoeff + SEASON_HORSE_BUFF, SEASON_COEFF_BASE);
        } else {
            return seasonCoeff;
        }
    }

    /**
     * Вычислить значение коэффициента голода лошади.
     */
    private double evaluateHungerCoeff(Horse horse) {
        return (Horse.HUNGER_UPPER_BOUND - horse.getHunger()) / HUNGER_COEFF_DIVIDER;
    }

    /**
     * Вычислить значение коэффициента здоровья лошади.
     */
    private double evaluateHealthCoeff(Horse horse) {
        return horse.getHealth() / HEALTH_COEFF_DIVIDER;
    }

    /**
     * Увеличить уровень голода лошади во время скачек.
     *
     * @param distance Дистанция, пройденная лошадью.
     * @param horse    Лошадь.
     */
    private void horseHungerIncrease(double distance, Horse horse) {
        if (horse.getHunger() != Horse.HUNGER_UPPER_BOUND) {
            final var hunger = horse.getHunger() + distance /
                    Horse.HUNGER_INCREASE_DISTANCE * HUNGER_INCREASE_COEFF;
            horse.setHunger(Math.min(hunger, Horse.HUNGER_UPPER_BOUND));
        }
    }

    /**
     * Уменьшить уровень здоровья лошади.
     *
     * @param healthDecrease Количество единиц здоровья, на которые уменьшится здоровье лошади.
     * @param horse          Лошадь
     */
    private void horseHealthDecrease(int healthDecrease, Horse horse) {
        if (horse.getHealth() != Horse.HEALTH_LOWER_BOUND) {
            final var health = horse.getHealth() - healthDecrease;
            horse.setHealth(Math.max(health, Horse.HEALTH_LOWER_BOUND));
        }
    }

    /**
     * Рассчитать время прохождения дистанции лошадью.
     *
     * @param distance Пройденная дистанция.
     * @param horse    Лошадь, проходящая дистанцию.
     * @param coeffs   Коэффициенты лошади.
     * @return Время.
     */
    private double evaluateTime(double distance, Horse horse, HashMap<String, Double> coeffs) {
        final var horseSpeed = Horse.MAX_SPEED_METERS_SECONDS * coeffs.get(POWER_COEFF) *
                coeffs.get(ENDURANCE_COEFF) * coeffs.get(AGE_COEFF) * coverageCoeff *
                coeffs.get(SEASON_COEFF) * weatherCoeff * evaluateHungerCoeff(horse) *
                evaluateHealthCoeff(horse);
        return distance / horseSpeed;
    }

    /**
     * Рассчитать время прохождения лошадью дистанции ипподрома.
     *
     * @param horse  Лошадь.
     * @param coeffs Коэффициенты лошади.
     * @return Время.
     */
    private double horseRaceTime(Horse horse, HashMap<String, Double> coeffs) {
        var eventCount = 0;
        var time = 0D;
        var currentDistance = 0D;
        var remainingDistance = hippodrome.getDistanceMeters();
        for (int i = 0; i < (int) hippodrome.getDistanceMeters(); i++) {
            currentDistance += 1D;
            Event event = eventCount < MAX_EVENT_NUMBER && Math.random() <= EVENT_APPEAR_PROBABILITY ?
                    Event.chooseRandomEvent() : null;
            if (currentDistance == Math.round(Horse.HUNGER_INCREASE_DISTANCE) || event != null) {
                time += evaluateTime(currentDistance, horse, coeffs);
                horseHungerIncrease(currentDistance, horse);
                remainingDistance -= currentDistance;
                currentDistance = 0D;
            }
            if (event != null) {
                horseHealthDecrease(event.getPenalty(), horse);
                eventCount++;
            }
        }
        return time + evaluateTime(remainingDistance, horse, coeffs);
    }

    /**
     * Провести скачки лошадей на ипподроме.
     *
     * @return Хэш-таблица, в которой ключи -- лошади, а значения -- время,
     * за которое лошадь проходит дистанцию ипподрома.
     */
    public Map<Horse, Double> holdRace() {
        if (horses.size() == 0) {
            throw new IllegalOperationException(
                    "The number of horses participating in the race cannot be less than one.");
        }
        final var times = new HashMap<Horse, Double>(horses.size());
        horses.forEach((k, v) -> times.put(k, horseRaceTime(k, v)));
        return times;
    }

    /**
     * Установить ипподром, на котором проходят скачки.
     *
     * @param hippodrome Ипподром.
     */
    public void setHippodrome(Hippodrome hippodrome) {
        this.hippodrome = hippodrome;
        evaluateCoverageCoeff();
    }

    /**
     * Получить лошадей, участвующих в скачках.
     *
     * @return Массив лошадей.
     */
    public Horse[] getHorses() {
        return horses.keySet().toArray(new Horse[0]);
    }

    /**
     * Установить лошадей, которые будут участвовать в скачках.
     *
     * @param horses Лошади.
     */
    public void setHorses(Horse... horses) {
        initHorses(horses);
    }

    /**
     * Добавить лошадей, которые будут участвовать в скачках.
     *
     * @param horses Лошади.
     */
    public void addHorses(Horse... horses) {
        for (Horse horse : horses) {
            this.horses.put(horse, evaluateHorseCoeffs(horse));
        }
    }

    /**
     * Убрать лошадей из скачек.
     *
     * @param horses Лошади.
     */
    public void removeHorses(Horse... horses) {
        for (Horse horse : horses) {
            this.horses.remove(horse);
        }
    }

    /**
     * Установить погоду.
     *
     * @param weather Погода, при которой будут происходить скачки.
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
        evaluateWeatherCoeff();
    }

    /**
     * Установить время года.
     *
     * @param season Время года, в которое будут происходить скачки.
     */
    public void setSeason(Season season) {
        this.season = season;
        horses.forEach((k, v) -> v.replace(SEASON_COEFF, evaluateSeasonCoeff(k)));
    }

    /**
     * Покормить лошадей.
     */
    public void feedHorses() {
        for (Horse horse : horses.keySet()) {
            horse.feed();
        }
    }

    /**
     * Вылечить лошадей.
     */
    public void treatHorses() {
        for (Horse horse : horses.keySet()) {
            horse.treat();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HorseRacing{")
                .append("\nhippodrome=")
                .append(hippodrome)
                .append(",\nhorses=[");
        horses.keySet().forEach(v -> {
            builder.append(v)
                    .append("\n");
        });
        builder.append("]")
                .append(",\nweather=")
                .append(weather)
                .append(",\nseason=")
                .append(season)
                .append("}");
        return builder.toString();
    }

    /**
     * Получить строитель объектов класса {@link HorseRacing}.
     *
     * @return Объект класса-строителя {@link HorseRacingBuilder}.
     */
    public static HorseRacingBuilder builder() {
        return new HorseRacingBuilder();
    }

    /**
     * Класс-строитель объектов {@link HorseRacing}.
     */
    public static class HorseRacingBuilder {
        /**
         * @see HorseRacing#hippodrome
         */
        private Hippodrome hippodrome;
        /**
         * @see HorseRacing#horses
         */
        private Horse[] horses;
        /**
         * @see HorseRacing#weather
         */
        private Weather weather;
        /**
         * @see HorseRacing#season
         */
        private Season season;

        /**
         * Установить ипподром, на котором проходят скачки.
         *
         * @param hippodrome Ипподром.
         * @return Объект класса строителя {@link HorseRacingBuilder}.
         */
        public HorseRacingBuilder hippodrome(Hippodrome hippodrome) {
            this.hippodrome = hippodrome;
            return this;
        }

        /**
         * Установить лошадей, которые участвуют в скачках.
         *
         * @param horses Лошади.
         * @return Объект класса строителя {@link HorseRacingBuilder}.
         */
        public HorseRacingBuilder horses(Horse... horses) {
            this.horses = horses;
            return this;
        }

        /**
         * Установить погоду.
         *
         * @param weather Погода.
         * @return Объект класса строителя {@link HorseRacingBuilder}.
         */
        public HorseRacingBuilder weather(Weather weather) {
            this.weather = weather;
            return this;
        }

        /**
         * Установить время года.
         *
         * @param season Время года.
         * @return Объект класса строителя {@link HorseRacingBuilder}.
         */
        public HorseRacingBuilder season(Season season) {
            this.season = season;
            return this;
        }

        /**
         * Создать объект класса {@link HorseRacing}.
         *
         * @return Объект класса {@link HorseRacing}.
         */
        public HorseRacing build() {
            return new HorseRacing(hippodrome, weather, season, horses);
        }
    }
}
