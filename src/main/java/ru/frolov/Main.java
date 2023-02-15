package ru.frolov;

import ru.frolov.hippodrome.enums.Color;
import ru.frolov.hippodrome.enums.Gender;
import ru.frolov.hippodrome.Hippodrome;
import ru.frolov.hippodrome.Horse;
import ru.frolov.hippodrome.enums.HorseBreed;
import ru.frolov.hippodrome.HorseRacing;
import ru.frolov.hippodrome.enums.Season;
import ru.frolov.hippodrome.enums.Weather;
import ru.frolov.hippodrome.enums.Coverage;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Horse roach = Horse.builder()
                .name("Плотва")
                .gender(Gender.MALE)
                .breed(HorseBreed.MORGAN)
                .color(Color.BLACK)
                .age(12)
                .build();
        Horse cornflower = Horse.builder()
                .name("Василек")
                .gender(Gender.MALE)
                .breed(HorseBreed.ANDALUSIAN)
                .color(Color.BEIGE)
                .age(9)
                .build();
        Hippodrome neverland = Hippodrome.builder()
                .title("Neverland")
                .coverage(Coverage.LOOSE)
                .distanceMeters(500)
                .build();
        HorseRacing racing = HorseRacing.builder()
                .hippodrome(neverland)
                .horses(roach, cornflower)
                .weather(Weather.SNOW)
                .season(Season.WINTER)
                .build();
        System.out.println("Ипподром:");
        System.out.println(neverland);
        System.out.println();
        System.out.println("Лошади до забега:");
        System.out.println(roach);
        System.out.println(cornflower);
        System.out.println();
        HashMap<Horse, Double> results = racing.holdRace();
        System.out.println("Лошади после забега:");
        results.forEach((k, v) -> {
            System.out.println(k);
            System.out.printf("Время прохождения дистанции %.2f м = %.3f с%n",
                    racing.getHippodrome().getDistanceMeters(), v);
        });
    }
}