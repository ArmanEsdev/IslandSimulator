package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Rabbit extends Herbivore {

    public Rabbit(Location location) {
        super(2.0, 0.45, 2, location);
        this.maxAge = 8;
        this.maxStarvation = 3;
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
        return 0.45;
    }

    @Override
    public Rabbit createOffspring(Location location) {
        Rabbit offspring = new Rabbit(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}


