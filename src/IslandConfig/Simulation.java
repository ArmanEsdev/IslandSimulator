package IslandConfig;

import organism.PopulationFactory;

public class Simulation {
    private final IslandSettings settings;

    public Simulation(IslandSettings settings) {
        this.settings = settings;
    }

    public void initializeWorld() {
        Island.getInstance().initialize(
                settings.getWidth(),
                settings.getHeight(),
                settings.getMaxPlantsPerCell()
        );
        PopulationFactory.populate(settings);
    }


}


