package org.example.service;
import org.example.model.City;
import org.example.model.Road;

import java.util.*;
public class MapGenerator {


        private static final int NUM_CITIES = 100;
        private static final Random random = new Random();

        public static List<City> generateCities() {
            List<City> cities = new ArrayList<>();
            for (int i = 0; i < NUM_CITIES; i++) {
                String name = "City" + (i + 1);
                double latitude = random.nextDouble() * 180 - 90;
                double longitude = random.nextDouble() * 360 - 180;
                cities.add(new City(name, latitude, longitude));
            }
            return cities;
        }

        public static List<Road> generateRoads(List<City> cities) {
            List<Road> roads = new ArrayList<>();
            List<City> connectedCities = new ArrayList<>();
            int maxNationalHighways = 4;
            int currentNationalHighways = 0;

            // Ensure each city is connected
            for (City city : cities) {
                List<City> possibleConnections = new ArrayList<>(cities);
                possibleConnections.remove(city);
                Collections.shuffle(possibleConnections);

                City targetCity = possibleConnections.get(0);
                int lanes = determineLanes(currentNationalHighways, maxNationalHighways);
                roads.add(new Road(city, targetCity, lanes));
                connectedCities.add(targetCity);
                currentNationalHighways += (lanes == 4) ? 1 : 0;
            }

            // Ensure there are no isolated cities
            for (City city : cities) {
                if (!connectedCities.contains(city)) {
                    City targetCity = cities.get(random.nextInt(cities.size()));
                    int lanes = determineLanes(currentNationalHighways, maxNationalHighways);
                    roads.add(new Road(city, targetCity, lanes));
                    connectedCities.add(city);
                    connectedCities.add(targetCity);
                    currentNationalHighways += (lanes == 4) ? 1 : 0;
                }
            }

            return roads;
        }

        private static int determineLanes(int currentNationalHighways, int maxNationalHighways) {
            if (currentNationalHighways < maxNationalHighways) {
                return 4; // National Highways
            }
            return random.nextInt(3) + 1; // 1 to 3 lanes
        }


}
