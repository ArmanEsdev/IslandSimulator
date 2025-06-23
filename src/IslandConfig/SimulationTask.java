package IslandConfig;

import organism.Animal;

import java.util.ArrayList;
import java.util.List;

public class SimulationTask {

    public static void runAll() {
        // Recolectar todos los animales actualmente vivos en la isla
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : Island.getInstance().getGrid()) {
            for (Location loc : row) {
                for (Object obj : loc.getAllOrganisms()) {
                    if (obj instanceof Animal animal) {
                        allAnimals.add(animal);
                    }
                }
            }
        }

        Thread plants = new Thread(new PlantGrowthTask());
        Thread animals = new Thread(new AnimalActionTask(allAnimals));

        plants.start();
        animals.start();

        try {
            plants.join();
            animals.join();
        } catch (InterruptedException ignored) {}
    }
}





