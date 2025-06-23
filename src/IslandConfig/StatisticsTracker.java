package IslandConfig;

import organism.Animal;

import java.util.*;

public class StatisticsTracker {
    private static final StatisticsTracker instance = new StatisticsTracker();

    private final Map<Class<? extends Animal>, Integer> births = new HashMap<>();
    private final Map<Class<? extends Animal>, Integer> deaths = new HashMap<>();
    private final List<PopulationRecord> history = new ArrayList<>();

    private StatisticsTracker() {}

    public static StatisticsTracker getInstance() {
        return instance;
    }

    public void recordBirth(Animal animal) {
        births.merge(animal.getClass(), 1, Integer::sum);
    }

    public void recordDeath(Animal animal) {
        deaths.merge(animal.getClass(), 1, Integer::sum);
    }

    public void printCycleSummary(int cycle, List<Animal> animals) {
        System.out.println("\n📈 Estadísticas del ciclo " + cycle);
        System.out.println("🐾 Animales vivos: " + animals.stream().filter(Animal::isAlive).count());

        System.out.println("🐣 Nacimientos:");
        if (births.isEmpty()) {
            System.out.println("   -");
        } else {
            births.forEach((cls, count) ->
                    System.out.printf("   - %s: +%d%n", cls.getSimpleName(), count));
        }

        System.out.println("💀 Muertes:");
        if (deaths.isEmpty()) {
            System.out.println("   -");
        } else {
            deaths.forEach((cls, count) ->
                    System.out.printf("   - %s: -%d%n", cls.getSimpleName(), count));
        }

        System.out.println("📋 Población actual:");
        Map<String, Integer> aliveCount = new TreeMap<>();
        for (Animal a : animals) {
            if (a.isAlive()) {
                String type = a.getClass().getSimpleName();
                aliveCount.merge(type, 1, Integer::sum);
            }
        }
        aliveCount.forEach((name, count) ->
                System.out.printf("   - %s: %d%n", name, count));
    }

    public void storeCycleRecord(int cycle, List<Animal> animals) {
        int alive = 0;
        Map<String, Integer> population = new TreeMap<>();

        for (Animal a : animals) {
            if (a.isAlive()) {
                alive++;
                String type = a.getClass().getSimpleName();
                population.merge(type, 1, Integer::sum);
            }
        }

        history.add(new PopulationRecord(
                cycle,
                alive,
                new HashMap<>(births),
                new HashMap<>(deaths),
                new HashMap<>(population)
        ));

        births.clear();
        deaths.clear();
    }

    public List<PopulationRecord> getHistory() {
        return history;
    }
}



