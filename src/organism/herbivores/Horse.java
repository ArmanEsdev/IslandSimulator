package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Horse extends Herbivore {

    public Horse(Location location) {
        super(400.0, 60.0, 4, location);
        this.maxAge = 20;
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
        return 60.0;
    }

    @Override
    public Horse createOffspring(Location location) {
        Horse offspring = new Horse(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}


