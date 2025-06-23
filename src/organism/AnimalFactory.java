package organism;

import IslandConfig.Location;
import organism.carnivores.*;
import organism.herbivores.*;

import java.util.Map;
import java.util.Set;

public class AnimalFactory {

    private static final Map<String, Class<? extends Animal>> registry = Map.ofEntries(
            Map.entry("Wolf", Wolf.class),
            Map.entry("Fox", Fox.class),
            Map.entry("Bear", Bear.class),
            Map.entry("Boa", Boa.class),
            Map.entry("Eagle", Eagle.class),
            Map.entry("Rabbit", Rabbit.class),
            Map.entry("Deer", Deer.class),
            Map.entry("Duck", Duck.class),
            Map.entry("Mouse", Mouse.class),
            Map.entry("Caterpillar", Caterpillar.class),
            Map.entry("Horse", Horse.class),
            Map.entry("Goat", Goat.class),
            Map.entry("Boar", Boar.class),
            Map.entry("Sheep", Sheep.class),
            Map.entry("Buffalo", Buffalo.class)
    );

    public static Class<? extends Animal> getClassByName(String name) {
        return registry.get(name);
    }

    public static Set<String> getRegisteredSpecies() {
        return registry.keySet();
    }

    public static Animal create(Class<? extends Animal> species, Location location) {
        try {
            return species.getConstructor(Location.class).newInstance(location);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo crear instancia de " + species.getSimpleName(), e);
        }
    }
}



