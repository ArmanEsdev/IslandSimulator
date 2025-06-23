package IslandConfig;

import organism.Animal;
import organism.carnivores.*;
import organism.herbivores.*;

import java.util.Map;

import static java.util.Map.entry;

public class Configuration {

    public static final int ISLAND_WIDTH = 10;
    public static final int ISLAND_HEIGHT = 10;
    public static final int MAX_PLANTS_PER_LOCATION = 5;

    // 🌍 Referencia global al mapa de la isla
    public static Location[][] ISLAND_MAP;

    public static final Map<Class<? extends Animal>, Integer> INITIAL_ANIMALS = Map.ofEntries(
            entry(Wolf.class, 3),
            entry(Rabbit.class, 6),
            entry(Deer.class, 4),
            entry(Fox.class, 3),
            entry(Bear.class, 2),
            entry(Goat.class, 4),
            entry(Sheep.class, 4),
            entry(Duck.class, 3),
            entry(Mouse.class, 5),
            entry(Boa.class, 2),
            entry(Eagle.class, 2),
            entry(Horse.class, 2),
            entry(Boar.class, 3),
            entry(Buffalo.class, 1),
            entry(Caterpillar.class, 6)
    );
}
