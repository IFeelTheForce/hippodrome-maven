package ru.frolov;

import ru.frolov.hippodrome.service.impl.RaceCacheImpl;

public class Main {
    public static void main(String[] args) {
        final var cache = new RaceCacheImpl();
//        cache.generateNew();
//        cache.getRaceResult().forEach((k, v) -> {
//            System.out.println(k);
//            System.out.println(v);
//        });
//        System.out.println();
//        cache.treatHorses();
        cache.feedHorses();
        cache.getRaceResult().forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }
}
