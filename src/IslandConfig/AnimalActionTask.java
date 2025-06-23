package IslandConfig;

import organism.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalActionTask implements Runnable {

    private final List<Animal> animals;

    public AnimalActionTask(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public void run() {
        // Clon seguro para evitar ConcurrentModificationException
        List<Animal> copy = new ArrayList<>(animals);

        for (Animal animal : copy) {
            if (animal.isAlive()) {
                animal.act();
            }
        }
    }
}



