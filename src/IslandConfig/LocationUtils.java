package IslandConfig;

import java.util.*;


public class LocationUtils {

    public static List<Location> getAccessibleLocations(Location current, int range) {
        List<Location> result = new ArrayList<>();

        int x = current.getX();
        int y = current.getY();
        Location[][] map = Configuration.ISLAND_MAP;

        int height = map.length;
        int width = map[0].length;

        for (int dy = -range; dy <= range; dy++) {
            for (int dx = -range; dx <= range; dx++) {
                if (dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && ny >= 0 && nx < width && ny < height) {
                    result.add(map[ny][nx]);
                }
            }
        }

        return result;
    }
}
