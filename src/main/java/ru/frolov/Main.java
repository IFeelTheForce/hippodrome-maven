package ru.frolov;

import ru.frolov.hippodrome.Hippodrome;
import ru.frolov.hippodrome.Horse;
import ru.frolov.hippodrome.HorseRacing;
import ru.frolov.hippodrome.enums.Season;
import ru.frolov.hippodrome.enums.Weather;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Horse firstHorse = Horse.random();
        Horse secondHorse = Horse.random();
        Hippodrome hippodrome = Hippodrome.random();
        HorseRacing racing = HorseRacing.builder()
                .hippodrome(hippodrome)
                .horses(firstHorse, secondHorse)
                .weather(Weather.SNOW)
                .season(Season.WINTER)
                .build();
        System.out.println("Ипподром:");
        System.out.println(hippodrome);
        System.out.println();
        System.out.println("Погода:");
        System.out.println(racing.getWeather());
        System.out.println("Время года:");
        System.out.println(racing.getSeason());
        System.out.println();
        System.out.println("Лошади до забега:");
        System.out.println(firstHorse);
        System.out.println(secondHorse);
        System.out.println();
        Map<Horse, Double> results = racing.holdRace();
        System.out.println("Лошади после забега:");
        results.forEach((k, v) -> {
            System.out.println(k);
            System.out.printf("Время прохождения дистанции %.2f м = %.3f с%n",
                    racing.getHippodrome().getDistanceMeters(), v);
        });
    }
}
