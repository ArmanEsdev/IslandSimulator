package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;
import organism.herbivores.Caterpillar;

import java.util.List;
import java.util.Map;

public class Duck extends Herbivore {

    public Duck(Location location) {
        super(1.0, 0.15, 4, location);
        this.maxAge = 6;
        this.maxStarvation = 3;
    }

    @Override
    public void act() {
        boolean ate = eat();
        move();
        reproduce();
        ageAndCheckDeath(ate);
    }

    @Override
    public double getMaxSaturation() {
        return 0.15;
    }

    @Override
    public Animal createOffspring(Location location) {
        Duck offspring = new Duck(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }

    @Override
    public boolean eat() {
        Location location = getLocation();
        if (location == null) return false;

        // 1️⃣ Intenta comer planta
        if (location.consumePlant()) {
            increaseSaturation(getMaxSaturation() * 0.25);
            return true;
        }

        // 2️⃣ Si no hay planta, busca una Caterpillar viva
        Map<Class<? extends Animal>, List<Animal>> animalsMap = location.getAnimals();
        List<Animal> animals = animalsMap.getOrDefault(Caterpillar.class, List.of());

        for (Animal animal : animals) {
            if (animal instanceof Caterpillar caterpillar && caterpillar.isAlive()) {
                double foodNeeded = getMaxSaturation() - getFoodLevel();
                double consumed = Math.min(caterpillar.getWeight(), foodNeeded);
                caterpillar.die();
                increaseSaturation(consumed);
                return true;
            }
        }

        return false;
    }
}





