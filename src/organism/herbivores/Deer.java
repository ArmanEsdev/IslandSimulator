package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Herbivore;

public class Deer extends Herbivore {

    public Deer(Location location) {
        super(300.0, 50.0, 4, location);
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
        return 50.0;
    }

    @Override
    public Deer createOffspring(Location location) {
        Deer offspring = new Deer(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}




