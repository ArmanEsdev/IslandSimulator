package organism;

import IslandConfig.Location;

import java.util.*;

public abstract class Carnivore extends Animal {

    public Carnivore(double weight, double maxFood, int speed, Location location) {
        super(weight, maxFood, speed, location);
    }

    @Override
    public boolean eat() {
        boolean ateSomething = false;
        Map<Class<?>, List<Object>> localOrganisms = location.getOrganisms();
        List<Class<? extends Animal>> preyTypes = getPreyTypes();
        Map<Class<? extends Animal>, Integer> chance = getHuntingProbabilities();

        for (Class<? extends Animal> preyType : preyTypes) {
            List<Object> preyList = localOrganisms.getOrDefault(preyType, Collections.emptyList());

            Iterator<Object> iterator = preyList.iterator();
            while (iterator.hasNext() && getFoodLevel() < getMaxSaturation()) {
                Animal prey = (Animal) iterator.next();
                if (!prey.isAlive()) continue;

                int probability = chance.getOrDefault(preyType, 0);
                if (new Random().nextInt(100) < probability) {
                    increaseSaturation(prey.getWeight());
                    prey.die();
                    iterator.remove();
                    ateSomething = true;
                }
            }
        }

        return ateSomething;
    }

    protected abstract List<Class<? extends Animal>> getPreyTypes();
    protected abstract Map<Class<? extends Animal>, Integer> getHuntingProbabilities();
}





