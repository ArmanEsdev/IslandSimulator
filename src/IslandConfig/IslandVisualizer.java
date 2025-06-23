package IslandConfig;

import organism.Animal;
import organism.Plant;

import java.util.*;

public class IslandVisualizer {

    private static final Map<String, String> speciesIcons = Map.ofEntries(
            Map.entry("Wolf", "🐺"),
            Map.entry("Rabbit", "🐰"),
            Map.entry("Fox", "🦊"),
            Map.entry("Bear", "🐻"),
            Map.entry("Boar", "🐗"),
            Map.entry("Duck", "🦆"),
            Map.entry("Mouse", "🐭"),
            Map.entry("Caterpillar", "🐛"),
            Map.entry("Goat", "🐐"),
            Map.entry("Sheep", "🐑"),
            Map.entry("Deer", "🦌"),
            Map.entry("Horse", "🐴"),
            Map.entry("Buffalo", "🦬"),
            Map.entry("Eagle", "🦅"),
            Map.entry("Boa", "🐍")
    );

    public static void render() {
        Island island = Island.getInstance();

        if (island.getGrid() == null) {
            System.out.println("⚠️ La isla aún no fue inicializada. Ejecutá la simulación primero.");
            return;
        }

        Location[][] grid = island.getGrid();
        System.out.println("\n🌍 Mapa de la isla:");
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location loc = grid[y][x];
                String symbol = getCellSymbol(loc);
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    private static String getCellSymbol(Location location) {
        Map<Class<?>, List<Object>> content = location.getOrganisms();
        Map<String, Integer> speciesCount = new HashMap<>();
        boolean hasPlants = false;

        for (Map.Entry<Class<?>, List<Object>> entry : content.entrySet()) {
            Class<?> cls = entry.getKey();
            List<Object> organisms = entry.getValue();

            if (Plant.class.isAssignableFrom(cls)) {
                hasPlants = !organisms.isEmpty();
            } else if (Animal.class.isAssignableFrom(cls)) {
                String name = cls.getSimpleName();
                speciesCount.put(name, organisms.size());
            }
        }

        String dominant = speciesCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        String icon = dominant == null ? "🐾" : speciesIcons.getOrDefault(dominant, "🐾");
        return hasPlants ? icon + "🌱" : icon + " ";
    }
}





