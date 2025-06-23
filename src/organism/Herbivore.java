package organism;

import IslandConfig.Location;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, double maxFood, int speed, Location location) {
        super(weight, maxFood, speed, location);
    }

    @Override
    public boolean eat() {
        Location location = getLocation();
        if (location != null && location.consumePlant()) {
            this.increaseSaturation(getMaxSaturation() * 0.3);
            return true;
        }
        return false;
    }
}





