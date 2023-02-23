package ru.frolov;

import ru.frolov.hippodrome.Horse;
import ru.frolov.hippodrome.HorseRacing;

public class Main {
    public static void main(String[] args) {
        HorseRacing racing = HorseRacing.random();
        System.out.println("Ипподром:");
        System.out.println(racing.getHippodrome());
        System.out.println();
        System.out.println("Погода:");
        System.out.println(racing.getWeather());
        System.out.println("Время года:");
        System.out.println(racing.getSeason());
        System.out.println();
        System.out.println("Лошади до забега:");
        for (Horse horse : racing.getHorses()) {
            System.out.println(horse);
        }
        System.out.println();
        final var results = racing.holdRace();
        System.out.println("Лошади после забега:");
        results.forEach((k, v) -> {
            System.out.println(k);
            System.out.printf("Время прохождения дистанции %.2f м = %.3f с%n",
                    racing.getHippodrome().getDistanceMeters(), v);
        });
    }
}
