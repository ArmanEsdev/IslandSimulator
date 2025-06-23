package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Mouse extends Herbivore {

    public Mouse(Location location) {
        super(0.05, 0.01, 1, location);
        this.maxAge = 5;
        this.maxStarvation = 2;
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
        return 0.01;
    }

    @Override
    public Mouse createOffspring(Location location) {
        Mouse offspring = new Mouse(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}


