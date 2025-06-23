package IslandConfig;

import organism.Animal;
import organism.Plant;

import java.util.*;

public class Location {
    private final int x, y;
    private final int maxPlants;
    private final Map<Class<?>, List<Object>> organisms = new HashMap<>();

    public Location(int x, int y, int maxPlants) {
        this.x = x;
        this.y = y;
        this.maxPlants = maxPlants;
        organisms.put(Plant.class, new ArrayList<>());
    }

    public void addOrganism(Object obj) {
        organisms.computeIfAbsent(obj.getClass(), k -> new ArrayList<>()).add(obj);
    }

    public void removeOrganism(Object obj) {
        List<Object> list = organisms.get(obj.getClass());
        if (list != null) list.remove(obj);
    }

    public Collection<Object> getAllOrganisms() {
        List<Object> all = new ArrayList<>();
        for (List<Object> list : organisms.values()) {
            all.addAll(list);
        }
        return all;
    }

    public Map<Class<?>, List<Object>> getOrganisms() {
        return organisms;
    }

    public void growPlant(int amount) {
        List<Object> plants = organisms.get(Plant.class);
        for (int i = 0; i < amount && plants.size() < maxPlants; i++) {
            plants.add(new Plant(this));
        }
    }

    public boolean consumePlant() {
        List<Object> plants = organisms.get(Plant.class);
        if (plants != null && !plants.isEmpty()) {
            plants.remove(0);
            return true;
        }
        return false;
    }

    public Map<Class<? extends Animal>, List<Animal>> getAnimals() {
        Map<Class<? extends Animal>, List<Animal>> result = new HashMap<>();

        for (Map.Entry<Class<?>, List<Object>> entry : organisms.entrySet()) {
            if (Animal.class.isAssignableFrom(entry.getKey())) {
                @SuppressWarnings("unchecked")
                Class<? extends Animal> animalType = (Class<? extends Animal>) entry.getKey();
                List<Object> objList = entry.getValue();

                List<Animal> animalList = new ArrayList<>();
                for (Object o : objList) {
                    if (o instanceof Animal a) {
                        animalList.add(a);
                    }
                }

                result.put(animalType, animalList);
            }
        }
        return result;
    }

    public int getX() { return x; }

    public int getY() { return y; }
}











