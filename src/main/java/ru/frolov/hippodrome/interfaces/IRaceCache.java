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
     * @return
     */
    HorseRacing getCurrentRace();

    /**
     * Получить результаты скачек.
     * @return
     */
    Map<Horse, Double> getRaceResult();
}
