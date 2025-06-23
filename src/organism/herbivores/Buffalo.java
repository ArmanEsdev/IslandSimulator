package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Buffalo extends Herbivore {

    public Buffalo(Location location) {
        super(700.0, 100.0, 3, location);
        this.maxAge = 25;
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
        return 100.0;
    }

    @Override
    public Buffalo createOffspring(Location location) {
        Buffalo offspring = new Buffalo(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}

