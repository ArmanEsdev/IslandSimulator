package organism;

import IslandConfig.Location;
import IslandConfig.StatisticsTracker;

public abstract class Animal {

    protected double weight;
    protected double foodLevel;
    protected double maxFood;
    protected int speed;
    protected int age = 0;
    protected int maxAge = 100;
    protected int maxStarvation = 3;
    protected int starvation = 0;
    protected Location location;

    public Animal(double weight, double maxFood, int speed, Location location) {
        this.weight = weight;
        this.maxFood = maxFood;
        this.foodLevel = maxFood * 0.5;
        this.speed = speed;
        this.location = location;
    }

    public boolean isAlive() {
        return age < maxAge && starvation < maxStarvation;
    }

    public void move() {
        IslandConfig.Island island = IslandConfig.Island.getInstance();
        int currentX = location.getX();
        int currentY = location.getY();

        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};
        int dirX = dx[(int) (Math.random() * 3)];
        int dirY = dy[(int) (Math.random() * 3)];

        for (int step = 0; step < speed; step++) {
            int newX = currentX + dirX;
            int newY = currentY + dirY;

            if (island.isInside(newX, newY)) {
                location.removeOrganism(this);
                Location newLoc = island.getLocation(newX, newY);
                newLoc.addOrganism(this);
                location = newLoc;

                currentX = newX;
                currentY = newY;
            } else {
                break;
            }
        }
    }

    public void reproduce() {
        if (Math.random() < 0.05) { // 5% chance por ciclo
            Animal offspring = createOffspring(location);
            location.addOrganism(offspring);
            StatisticsTracker.getInstance().recordBirth(offspring);
        }
    }

    public void ageAndCheckDeath(boolean ateThisCycle) {
        age++;
        if (!ateThisCycle) starvation++;
        else starvation = 0;

        if (!isAlive()) {
            die();
        }
    }

    public void die() {
        StatisticsTracker.getInstance().recordDeath(this);
        this.age = maxAge + 1;
    }

    public Location getLocation() {
        return location;
    }

    public double getWeight() {
        return weight;
    }

    public double getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(double amount) {
        this.foodLevel = Math.min(maxFood, amount);
    }

    public double getMaxFood() {
        return maxFood;
    }

    public void increaseSaturation(double amount) {
        this.foodLevel = Math.min(getMaxSaturation(), this.foodLevel + amount);
    }

    public abstract double getMaxSaturation();
    public abstract boolean eat();
    public abstract void act();
    public abstract Animal createOffspring(Location location);
}






