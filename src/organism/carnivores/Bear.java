package organism.carnivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Carnivore;
import organism.herbivores.*;

import java.util.List;
import java.util.Map;

public class Bear extends Carnivore {

    public Bear(Location location) {
        super(500.0, 80.0, 2, location); // peso, comida máxima, velocidad, ubicación
        this.maxAge = 20;
        this.maxStarvation = 5;
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
        return 80.0;
    }

    @Override
    public Bear createOffspring(Location location) {
        Bear offspring = new Bear(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }


    @Override
    protected List<Class<? extends Animal>> getPreyTypes() {
        return List.of(
                Rabbit.class,
                Goat.class,
                Sheep.class,
                Boar.class,
                Mouse.class,
                Duck.class
        );
    }

    @Override
    protected Map<Class<? extends Animal>, Integer> getHuntingProbabilities() {
        return Map.of(
                Rabbit.class, 70,
                Goat.class, 55,
                Sheep.class, 60,
                Boar.class, 50,
                Mouse.class, 40,
                Duck.class, 45
        );
    }
}


