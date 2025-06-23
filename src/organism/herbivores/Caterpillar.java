package organism.herbivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar(Location location) {
        super(0.01, 0.0, 0, location); // peso, comida máxima, velocidad
        this.maxAge = 3;
        this.maxStarvation = 1;
    }

    @Override
    public void act() {
        boolean ate = eat(); // intenta comer planta
        reproduce();
        ageAndCheckDeath(ate);
    }

    @Override
    public double getMaxSaturation() {
        return 0.0; // no necesita comida (puede cambiarse si lo deseás)
    }

    @Override
    public Caterpillar createOffspring(Location location) {
        Caterpillar offspring = new Caterpillar(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }
}


