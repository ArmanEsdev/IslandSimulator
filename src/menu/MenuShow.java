package menu;

import IslandConfig.*;
import organism.Animal;
import organism.AnimalFactory;

import java.util.*;

public class MenuShow {
    private final Map<Integer, Runnable> options = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final IslandSettings settings = IslandSettings.getInstance();

    public MenuShow() {
        settings.loadFromFile(); // ✅ Carga automática desde .properties al arrancar

        options.put(1, this::startSimulation);
        options.put(2, this::showCurrentConfig);
        options.put(3, this::configureSettings);
        options.put(4, this::visualizeIslandMap);
        options.put(5, this::insertAnimalManually);
        options.put(6, this::showPopulationHistory);
        options.put(7, this::exitApp);
    }

    public void display() {
        int choice;
        do {
            System.out.println("\n🌴 Menú principal");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  1. 🧵 Iniciar simulación");
            System.out.println("  2. ⚙️ Mostrar configuración actual");
            System.out.println("  3. 🔧 Cambiar configuración");
            System.out.println("  4. 🌍 Ver mapa de la isla");
            System.out.println("  5. ➕ Insertar animal manualmente");
            System.out.println("  6. 📈 Ver historial de población");
            System.out.println("  7. 🚪 Salir");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("Selecciona una opción: ");
            choice = readInt();

            Runnable action = options.get(choice);
            if (action != null) action.run();
            else System.out.println("❌ Opción inválida.");
        } while (choice != 7);
    }

    private void startSimulation() {
        System.out.print("¿Cuántos ciclos deseas simular? ");
        int cycles = readInt();

        Simulation simulation = new Simulation(settings);
        simulation.initializeWorld();

        SimulationEngine engine = new SimulationEngine(simulation, settings);
        engine.run(cycles);
    }

    private void showCurrentConfig() {
        System.out.println("\n📌 Configuración actual:");
        System.out.println(" - Ancho de la isla        : " + settings.getWidth());
        System.out.println(" - Alto de la isla         : " + settings.getHeight());
        System.out.println(" - Máx. plantas por celda  : " + settings.getMaxPlantsPerCell());
        System.out.println(" - Duración del ciclo (ms) : " + settings.getCycleDurationMillis());
        System.out.println(" - Animales por especie    : " + settings.getInitialAnimalsPerSpecies());
    }

    private void configureSettings() {
        System.out.print("Nuevo ancho de isla: ");
        settings.setWidth(readInt());

        System.out.print("Nuevo alto de isla: ");
        settings.setHeight(readInt());

        System.out.print("Máx. plantas por celda: ");
        settings.setMaxPlantsPerCell(readInt());

        System.out.print("Duración por ciclo (ms): ");
        settings.setCycleDurationMillis(readInt());

        // ✅ Nueva sección: editar animales por especie
        System.out.println("\n🧬 Configuración de animales iniciales:");
        Map<String, Integer> speciesMap = settings.getInitialAnimalsPerSpecies();

        for (String species : AnimalFactory.getRegisteredSpecies()) {
            System.out.print("Cantidad de " + species + " (actual " + speciesMap.getOrDefault(species, 0) + "): ");
            int amount = readInt();
            if (amount > 0) {
                speciesMap.put(species, amount);
            } else {
                speciesMap.remove(species); // eliminar si es 0 o negativo
            }
        }

        settings.saveToFile();
        System.out.println("✅ Configuración actualizada y guardada.");
    }


    private void visualizeIslandMap() {
        IslandVisualizer.render();
    }

    private void insertAnimalManually() {
        System.out.println("\n🦣 Inserción manual de animal:");

        Set<String> speciesList = AnimalFactory.getRegisteredSpecies();
        System.out.println("Especies disponibles: " + speciesList);
        System.out.print("Especie a insertar: ");
        String species = scanner.next();

        if (!speciesList.contains(species)) {
            System.out.println("❌ Especie no reconocida.");
            return;
        }

        System.out.print("Coordenada X (0 - " + (settings.getWidth() - 1) + "): ");
        int x = readInt();
        System.out.print("Coordenada Y (0 - " + (settings.getHeight() - 1) + "): ");
        int y = readInt();

        try {
            Location location = Island.getInstance().getLocation(x, y);
            Class<? extends Animal> clazz = AnimalFactory.getClassByName(species);
            Animal animal = AnimalFactory.create(clazz, location);
            location.addOrganism(animal);

            System.out.println("✅ " + species + " insertado en (" + x + ", " + y + ")");
        } catch (Exception e) {
            System.out.println("⚠️ Error al insertar el animal: " + e.getMessage());
        }
    }

    private void showPopulationHistory() {
        List<PopulationRecord> history = StatisticsTracker.getInstance().getHistory();

        if (history.isEmpty()) {
            System.out.println("📭 No hay historial aún. Ejecuta la simulación primero.");
            return;
        }

        System.out.println("\n📊 Historial de población por ciclo:");
        for (PopulationRecord record : history) {
            System.out.println("🌀 Ciclo " + record.cycle());
            System.out.println(" - Total vivos : " + record.totalAlive());

            System.out.println(" - Nacimientos:");
            record.births().forEach((cls, count) ->
                    System.out.printf("     • %-12s +%d%n", cls.getSimpleName(), count)
            );

            System.out.println(" - Muertes:");
            record.deaths().forEach((cls, count) ->
                    System.out.printf("     • %-12s -%d%n", cls.getSimpleName(), count)
            );

            System.out.println(" - Población viva:");
            record.aliveCount().forEach((type, count) ->
                    System.out.printf("     • %-12s %d%n", type, count)
            );

            System.out.println();
        }
    }

    private void exitApp() {
        System.out.println("👋 Cerrando simulador. ¡Hasta la próxima!");
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ingresa un número válido: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}







