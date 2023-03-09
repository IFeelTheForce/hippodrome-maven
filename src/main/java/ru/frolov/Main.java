package ru.frolov;

import ru.frolov.hippodrome.models.Horse;
import ru.frolov.hippodrome.service.impl.RaceCacheImpl;

public class Main {
    public static void main(String[] args) {
        final RaceCacheImpl raceCache = new RaceCacheImpl();
        raceCache.generateNew();
        System.out.println("Ипподром:");
        System.out.println(raceCache.getCurrentRace().getHippodrome());
        System.out.println();
        System.out.println("Погода:");
        System.out.println(raceCache.getCurrentRace().getWeather());
        System.out.println("Время года:");
        System.out.println(raceCache.getCurrentRace().getSeason());
        System.out.println();
        System.out.println("Лошади до забега:");
        for (Horse horse : raceCache.getCurrentRace().getHorses()) {
            System.out.println(horse);
        }
        System.out.println();
        System.out.println("Лошади после забега:");
        raceCache.getRaceResult().forEach((k, v) -> {
            System.out.println(k);
            System.out.printf("Время прохождения дистанции %.2f м = %.3f с%n",
                    raceCache.getCurrentRace().getHippodrome().getDistanceMeters(), v);
        });
    }
}
