package organism;

import IslandConfig.Location;

public abstract class Organism {
    protected double weight;
    protected Location location;

    public Organism(double weight, Location location) {
        this.weight = weight;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location newLocation) {
        this.location = newLocation;
    }

    public double getWeight() {
        return weight;
    }

    // Clases derivadas pueden implementar lógica específica (moverse, crecer, etc.)
}
