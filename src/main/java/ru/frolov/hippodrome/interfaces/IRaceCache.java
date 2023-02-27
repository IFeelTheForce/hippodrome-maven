package ru.frolov.hippodrome.interfaces;

import ru.frolov.hippodrome.Horse;
import ru.frolov.hippodrome.HorseRacing;

import java.util.Map;

public interface IRaceCache {

    /**
     * Создать скачки.
     *
     * @return Скачки.
     */
    HorseRacing generateNew();

    /**
     * Создать скачки.
     *
     * @param horseCount Количество лошадей.
     * @return Скачки.
     */
    HorseRacing generateNew(int horseCount);

    /**
     * Получить текущие скачки.
     *
     * @return Скачки.
     */
    HorseRacing getCurrentRace();

    /**
     * Получить результаты скачек.
     *
     * @return Результаты скачек.
     */
    Map<Horse, Double> getRaceResult();
}
