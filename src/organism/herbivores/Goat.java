package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Goat extends Herbivore {

    public Goat(Location location) {
        super(60.0, 10.0, 3, location);
        this.maxAge = 18;
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
        return 10.0;
    }

    @Override
    public Goat createOffspring(Location location) {
        Goat offspring = new Goat(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}



