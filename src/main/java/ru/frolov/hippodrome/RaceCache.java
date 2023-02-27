package ru.frolov.hippodrome;

import lombok.ToString;
import ru.frolov.hippodrome.exceptions.IllegalOperationException;
import ru.frolov.hippodrome.interfaces.IRaceCache;

import java.util.Map;

/**
 * Кэш гонок.
 */
@ToString
public class RaceCache implements IRaceCache {
    private HorseRacing horseRacing;

    public HorseRacing generateNew() {
        horseRacing = HorseRacing.random();
        return horseRacing;
    }

    @Override
    public HorseRacing generateNew(int horseCount) {
        horseRacing = HorseRacing.random(horseCount);
        return horseRacing;
    }

    @Override
    public HorseRacing getCurrentRace() {
        if (horseRacing == null) {
            throw new IllegalOperationException(
                    "It is not possible to get the current race, as it is necessary to create it.");
        }
        return horseRacing;
    }

    @Override
    public Map<Horse, Double> getRaceResult() {
        if (horseRacing == null) {
            throw new IllegalOperationException(
                    "It is not possible to get the race result, as it is necessary to create a race.");
        }
        return horseRacing.holdRace();
    }
}
