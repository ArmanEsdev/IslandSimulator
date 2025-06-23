package organism.carnivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Carnivore;
import organism.herbivores.Rabbit;
import organism.herbivores.Mouse;
import organism.herbivores.Duck;

import java.util.List;
import java.util.Map;

public class Fox extends Carnivore {

    public Fox(Location location) {
        super(8.0, 2.0, 3, location);
        this.maxAge = 10;
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
        return 2.0;
    }

    @Override
    public Fox createOffspring(Location location) {
        Fox offspring = new Fox(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }


    @Override
    protected List<Class<? extends Animal>> getPreyTypes() {
        return List.of(Rabbit.class, Mouse.class, Duck.class);
    }

    @Override
    protected Map<Class<? extends Animal>, Integer> getHuntingProbabilities() {
        return Map.of(
                Rabbit.class, 70,
                Mouse.class, 90,
                Duck.class, 60
        );
    }
}



