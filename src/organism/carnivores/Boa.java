package organism.carnivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Carnivore;
import organism.herbivores.*;

import java.util.List;
import java.util.Map;

public class Boa extends Carnivore {

    public Boa(Location location) {
        super(15.0, 3.0, 1, location);
        this.maxAge = 12;
        this.maxStarvation = 4;
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
        return 3.0;
    }

    @Override
    public Boa createOffspring(Location location) {
        Boa offspring = new Boa(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }


    @Override
    protected List<Class<? extends Animal>> getPreyTypes() {
        return List.of(
                Rabbit.class, Fox.class, Duck.class, Mouse.class
        );
    }

    @Override
    protected Map<Class<? extends Animal>, Integer> getHuntingProbabilities() {
        return Map.of(
                Rabbit.class, 20,
                Fox.class, 15,
                Duck.class, 10,
                Mouse.class, 40
        );
    }
}

