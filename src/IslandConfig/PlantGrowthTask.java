package IslandConfig;

public class PlantGrowthTask implements Runnable {

    private static final int PLANTS_PER_CYCLE = 2;

    @Override
    public void run() {
        for (Location[] row : Island.getInstance().getGrid()) {
            for (Location loc : row) {
                loc.growPlant(PLANTS_PER_CYCLE);
            }
        }
    }
}

