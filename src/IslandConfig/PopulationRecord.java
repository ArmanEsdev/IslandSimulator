package IslandConfig;

import java.util.Map;

public record PopulationRecord(
        int cycle,
        int totalAlive,
        Map<Class<? extends organism.Animal>, Integer> births,
        Map<Class<? extends organism.Animal>, Integer> deaths,
        Map<String, Integer> aliveCount
) {}

