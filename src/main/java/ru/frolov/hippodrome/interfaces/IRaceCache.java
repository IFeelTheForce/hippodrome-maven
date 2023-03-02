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
