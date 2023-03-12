package ru.frolov.hippodrome.service;

import ru.frolov.hippodrome.models.Hippodrome;
import ru.frolov.hippodrome.models.Horse;
import ru.frolov.hippodrome.models.HorseRacing;

import java.util.Map;

public interface RaceCache {

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
     * Изменить ипподром, на котором происходят скачки.
     *
     * @param hippodrome Ипподром.
     * @return Скачки.
     */
    HorseRacing setHippodrome(Hippodrome hippodrome);

    /**
     * Вылечить лошадей.
     *
     * @return Скачки.
     */
    HorseRacing treatHorses();

    /**
     * Покормить лошадей.
     *
     * @return Скачки.
     */
    HorseRacing feedHorses();

    /**
     * Получить результаты скачек.
     *
     * @return Результаты скачек.
     */
    Map<Horse, Double> getRaceResult();
}
