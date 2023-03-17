package ru.frolov.hippodrome.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.frolov.hippodrome.exceptions.IllegalOperationException;
import ru.frolov.hippodrome.models.Hippodrome;
import ru.frolov.hippodrome.models.Horse;
import ru.frolov.hippodrome.models.HorseRacing;
import ru.frolov.hippodrome.service.RaceCache;

import java.util.Map;
import java.util.Optional;

/**
 * Кэш гонок.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RaceCacheImpl implements RaceCache {
    private HorseRacing horseRacing;

    public HorseRacing generateNew() {
        horseRacing = HorseRacing.random();
        return horseRacing;
    }

    /**
     * Создать скачки.
     *
     * @param horseCount Количество лошадей.
     * @return Скачки.
     */
    public HorseRacing generateNew(int horseCount) {
        horseRacing = HorseRacing.random(horseCount);
        return horseRacing;
    }

    @Override
    public HorseRacing getCurrentRace() {
        return Optional.ofNullable(horseRacing)
                .orElseThrow(() -> new IllegalOperationException(
                        "The race cannot be empty"
                ));
    }

    @Override
    public HorseRacing setHippodrome(Hippodrome hippodrome) {
        Optional.ofNullable(horseRacing)
                .ifPresentOrElse(it -> it.setHippodrome(hippodrome),
                        () -> {
                            throw new IllegalOperationException("The race cannot be empty");
                        });
        return horseRacing;
    }

    @Override
    public HorseRacing treatHorses() {
        Optional.ofNullable(horseRacing)
                .ifPresentOrElse(HorseRacing::treatHorses,
                        () -> {
                            throw new IllegalOperationException("The race cannot be empty");
                        });
        return horseRacing;
    }

    @Override
    public HorseRacing feedHorses() {
        Optional.ofNullable(horseRacing)
                .ifPresentOrElse(HorseRacing::feedHorses,
                        () -> {
                            throw new IllegalOperationException("The race cannot be empty");
                        });
        return horseRacing;
    }

    @Override
    public Map<Horse, Double> getRaceResult() {
        return Optional.ofNullable(horseRacing)
                .map(HorseRacing::startRace)
                .orElseThrow(() -> {
                    throw new IllegalOperationException("The race cannot be empty");
                });
    }
}
