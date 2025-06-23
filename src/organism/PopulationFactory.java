package organism;

import IslandConfig.Island;
import IslandConfig.IslandSettings;
import IslandConfig.Location;

import java.util.Map;

public class PopulationFactory {
    public static void populate(IslandSettings settings) {
        Island island = Island.getInstance();
        Map<String, Integer> speciesMap = settings.getInitialAnimalsPerSpecies();

        for (Map.Entry<String, Integer> entry : speciesMap.entrySet()) {
            String species = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++) {
                Location location = island.getRandomLocation();
                Class<? extends Animal> clazz = AnimalFactory.getClassByName(species);
                if (clazz != null) {
                    Animal animal = AnimalFactory.create(clazz, location);
                    location.addOrganism(animal);
                }
            }
        }
    }
}

