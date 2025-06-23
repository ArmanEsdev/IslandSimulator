package IslandConfig;

public class Island {
    private static Island instance;
    private Location[][] grid;

    private Island() {
    }

    public static Island getInstance() {
        if (instance == null) {
            instance = new Island();
        }
        return instance;
    }

    public void initialize(int width, int height, int maxPlantsPerCell) {
        grid = new Location[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Location(x, y, maxPlantsPerCell);
            }
        }
    }

    public boolean isInside(int x, int y) {
        return y >= 0 && y < getHeight() && x >= 0 && x < getWidth();
    }

    public Location getLocation(int x, int y) {
        return grid[y][x];
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public Location[][] getGrid() {
        return grid;
    }

    public Location getRandomLocation() {
        int y = (int) (Math.random() * getHeight());
        int x = (int) (Math.random() * getWidth());
        return getLocation(x, y);
    }
}





