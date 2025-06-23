package IslandConfig;

import java.io.*;
import java.util.*;

public class IslandSettings {
    private static final IslandSettings instance = new IslandSettings();
    private static final String CONFIG_FILE = "island-config.properties";

    private int width = 10;
    private int height = 10;
    private int maxPlantsPerCell = 5;
    private int cycleDurationMillis = 1000;
    private final Map<String, Integer> initialAnimals = new HashMap<>();

    private IslandSettings() {
        initialAnimals.put("Rabbit", 20);
        initialAnimals.put("Wolf", 5);
    }

    public static IslandSettings getInstance() {
        return instance;
    }

    public void loadFromFile() {
        Properties props = new Properties();
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            props.load(reader);
            width = Integer.parseInt(props.getProperty("width", "10"));
            height = Integer.parseInt(props.getProperty("height", "10"));
            maxPlantsPerCell = Integer.parseInt(props.getProperty("maxPlantsPerCell", "5"));
            cycleDurationMillis = Integer.parseInt(props.getProperty("cycleDurationMillis", "1000"));

            initialAnimals.clear();
            for (String key : props.stringPropertyNames()) {
                if (key.startsWith("animal.")) {
                    String species = key.substring(7);
                    int count = Integer.parseInt(props.getProperty(key));
                    initialAnimals.put(species, count);
                }
            }
        } catch (IOException ignored) {}
    }

    public void saveToFile() {
        Properties props = new Properties();
        props.setProperty("width", String.valueOf(width));
        props.setProperty("height", String.valueOf(height));
        props.setProperty("maxPlantsPerCell", String.valueOf(maxPlantsPerCell));
        props.setProperty("cycleDurationMillis", String.valueOf(cycleDurationMillis));

        for (Map.Entry<String, Integer> e : initialAnimals.entrySet()) {
            props.setProperty("animal." + e.getKey(), String.valueOf(e.getValue()));
        }

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            props.store(writer, "Island Configuration");
        } catch (IOException ignored) {}
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getMaxPlantsPerCell() { return maxPlantsPerCell; }
    public int getCycleDurationMillis() { return cycleDurationMillis; }
    public Map<String, Integer> getInitialAnimalsPerSpecies() { return initialAnimals; }

    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
    public void setMaxPlantsPerCell(int m) { this.maxPlantsPerCell = m; }
    public void setCycleDurationMillis(int ms) { this.cycleDurationMillis = ms; }
}

