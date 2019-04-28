package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Hotel {
    int id, rating;

    public Hotel(int idVal, int ratingVal) {
        this.id = idVal;
        this.rating = ratingVal;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return id + " " + rating;
    }
}

public class SortHotelRating {
    public static void main(String[] args) {
        List hotels = Arrays.asList(
                new Hotel(3000, 5),
                new Hotel(3000, 7),
                new Hotel(3000, 6),
                new Hotel(1000, 4),
                new Hotel(1000, 7),
                new Hotel(1000, 7),
                new Hotel(2000, 10),
                new Hotel(2000, 8),
                new Hotel(1000, 5),
                new Hotel(1000, 5),
                new Hotel(3000, 8),
                new Hotel(2000, 8)
        );

        Map<Integer, Double> mapResult = (Map<Integer, Double>) hotels.stream()
                .collect(Collectors.groupingBy(Hotel::getId, TreeMap::new, Collectors.averagingDouble(Hotel::getRating)));
        System.out.println("before sort : " + mapResult);

        Map<Integer, Double> sortedResult = mapResult.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));

        System.out.println("After sort : " + sortedResult);

        hotels.stream().collect(Collectors.groupingBy(Hotel::getId));
    }

}
