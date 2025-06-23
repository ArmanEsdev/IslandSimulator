package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Boar extends Herbivore {

    public Boar(Location location) {
        super(400.0, 50.0, 2, location); // peso, comida máxima, velocidad, ubicación
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
        return 50.0;
    }

    @Override
    public Boar createOffspring(Location location) {
        Boar offspring = new Boar(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}


