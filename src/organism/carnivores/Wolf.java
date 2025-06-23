package organism.carnivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Carnivore;
import organism.herbivores.*;

import java.util.List;
import java.util.Map;

public class Wolf extends Carnivore {

    public Wolf(Location location) {
        super(50.0, 8.0, 4, location);
        this.maxAge = 15;
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
        return 8.0;
    }

    @Override
    public Wolf createOffspring(Location location) {
        Wolf offspring = new Wolf(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }


    @Override
    protected List<Class<? extends Animal>> getPreyTypes() {
        return List.of(
                Rabbit.class, Goat.class, Sheep.class,
                Boar.class, Mouse.class, Duck.class
        );
    }

    @Override
    protected Map<Class<? extends Animal>, Integer> getHuntingProbabilities() {
        return Map.of(
                Rabbit.class, 60,
                Goat.class, 15,
                Sheep.class, 70,
                Boar.class, 15,
                Mouse.class, 80,
                Duck.class, 40
        );
    }
}






