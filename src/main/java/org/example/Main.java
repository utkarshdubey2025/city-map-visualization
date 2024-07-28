package org.example;

import org.example.model.City;
import org.example.model.Road;
import org.example.service.MapGenerator;
import org.example.service.MapVisualizer;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.setProperty("org.graphstream.ui", "swing");
        List<City> cities = MapGenerator.generateCities();
        List<Road> roads = MapGenerator.generateRoads(cities);

        // Print cities
        System.out.println("Cities:");
        for (City city : cities) {
            System.out.println(city);
        }

        // Visualize map
        MapVisualizer.visualizeMap(cities, roads);
    }
}