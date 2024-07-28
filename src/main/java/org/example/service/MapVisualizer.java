package org.example.service;
import org.example.model.City;
import org.example.model.Road;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.List;


public class MapVisualizer {



        public static void visualizeMap(List<City> cities, List<Road> roads) {
            Graph graph = new SingleGraph("Map");

            // Add nodes with attributes
            for (City city : cities) {
                String cityName = city.getName();
                double longitude = city.getLongitude();
                double latitude = city.getLatitude();

                // Add node if not already present
                if (graph.getNode(cityName) == null) {
                    graph.addNode(cityName);
                    // Set node attributes
                    graph.getNode(cityName).setAttribute("ui.label", cityName);
                    graph.getNode(cityName).setAttribute("x", longitude);
                    graph.getNode(cityName).setAttribute("y", latitude);
                }
            }

            // Add edges with attributes
            for (Road road : roads) {
                String city1Name = road.getCity1().getName();
                String city2Name = road.getCity2().getName();
                int lanes = road.getLanes();
                String edgeId = city1Name + "-" + city2Name;

                // Ensure the nodes exist before adding an edge
                if (graph.getNode(city1Name) != null && graph.getNode(city2Name) != null) {
                    // Avoid adding duplicate edges
                    if (graph.getEdge(edgeId) == null) {
                        try {
                            graph.addEdge(edgeId, city1Name, city2Name, true); // true to make it a directed edge

                            // Set edge attributes
                            String color = getColorForLanes(lanes);
                            String style = "size: " + (lanes * 2) + "px; fill-color: " + color + ";";

                            graph.getEdge(edgeId).setAttribute("ui.label", lanes + " lanes");
                            graph.getEdge(edgeId).setAttribute("ui.style", style);

                        } catch (Exception e) {
                            System.err.println("Error adding edge: " + edgeId);
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.err.println("One or both nodes do not exist: " + city1Name + ", " + city2Name);
                }
            }

            // Display the graph
            try {
                graph.display();
            } catch (RuntimeException e) {
                System.err.println("Error displaying the graph.");
                e.printStackTrace();
            }
        }

        private static String getColorForLanes(int lanes) {
            switch (lanes) {
                case 4: return "black";
                case 3: return "blue";
                case 2: return "green";
                case 1: return "red";
                default: return "grey";
            }
        }


}
