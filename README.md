# Java Application to generate and display a random map of 100 cities with roads connecting them

## Description:
* This Java application generates a random map consisting of 100 cities connected by roads and visualizes it using the GraphStream library. The visualization differentiates roads based on their lane types, which include National Highways, Inter-State Highways, Highways, and Main Roads.

## Project Structure

The project is organized into the following packages and classes:

- **`org.example.model`**
    - `City.java`: Defines the `City` class with attributes for name, latitude, and longitude.
    - `Road.java`: Defines the `Road` class with attributes for two cities and lane count.

- **`org.example.service`**
    - `MapGenerator.java`: Contains methods to generate a list of cities and connect them with roads based on specific constraints.
    - `MapVisualizer.java`: Handles the visualization of the generated map using GraphStream.

## Requirements

- Java Development Kit (JDK) 8 or higher
- GraphStream library


  
## Compile, Build and Run
. **Compile the code:**

   ```sh
   javac -cp path/to/graphstream.jar org/example/model/City.java org/example/model/Road.java org/example/service/MapGenerator.java org/example/service/MapVisualizer.java


