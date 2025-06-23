package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Sheep extends Herbivore {

    public Sheep(Location location) {
        super(70.0, 15.0, 3, location);
        this.maxAge = 15;
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
        return 15.0;
    }

    @Override
    public Sheep createOffspring(Location location) {
        Sheep offspring = new Sheep(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}


