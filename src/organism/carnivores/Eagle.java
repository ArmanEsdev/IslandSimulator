package organism.carnivores;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;
import organism.Animal;
import organism.Carnivore;
import organism.herbivores.Duck;
import organism.herbivores.Rabbit;
import organism.herbivores.Mouse;

import java.util.List;
import java.util.Map;

public class Eagle extends Carnivore {

    public Eagle(Location location) {
        super(6.0, 1.5, 4, location); // peso, comida máxima, velocidad, ubicación
        this.maxAge = 12;
        this.maxStarvation = 3;
    }

    @Override
    public void act() {
        boolean ate = eat();
        move(); // puede incluir vuelo
        reproduce();
        ageAndCheckDeath(ate);
    }

    @Override
    public double getMaxSaturation() {
        return 1.5;
    }

    @Override
    public Eagle createOffspring(Location location) {
        Eagle offspring = new Eagle(location);
        StatisticsTracker.getInstance().recordBirth(offspring);
        return offspring;
    }


    @Override
    protected List<Class<? extends Animal>> getPreyTypes() {
        return List.of(Mouse.class, Rabbit.class, Duck.class);
    }

    @Override
    protected Map<Class<? extends Animal>, Integer> getHuntingProbabilities() {
        return Map.of(
                Mouse.class, 90,
                Rabbit.class, 70,
                Duck.class, 60
        );
    }
}



