package IslandConfig;

import organism.Animal;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private final Simulation simulation;
    private final IslandSettings settings;

    public SimulationEngine(Simulation simulation, IslandSettings settings) {
        this.simulation = simulation;
        this.settings = settings;
    }

    public void run(int cycles) {
        for (int i = 1; i <= cycles; i++) {
            SimulationTask.runAll();
            System.out.println("🌀 Ciclo " + i + " completado.");

            List<Animal> allAnimals = collectAllAnimals();
            StatisticsTracker stats = StatisticsTracker.getInstance();
            stats.printCycleSummary(i, allAnimals);
            stats.storeCycleRecord(i, allAnimals);


            try {
                Thread.sleep(settings.getCycleDurationMillis());
            } catch (InterruptedException ignored) {}
        }
    }

    private List<Animal> collectAllAnimals() {
        List<Animal> result = new ArrayList<>();
        for (Location[] row : Island.getInstance().getGrid()) {
            for (Location loc : row) {
                for (Object obj : loc.getAllOrganisms()) {
                    if (obj instanceof Animal a) {
                        result.add(a);
                    }
                }
            }
        }
        return result;
    }
}





