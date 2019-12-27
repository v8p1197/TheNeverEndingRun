package it.unisa.theneverendingrun.services.managers.spawnables;

import it.unisa.theneverendingrun.data.SpawnableType;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.services.managers.spawnables.SpawnableManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public abstract class SpawnableCreateManager {

    private SpawnableManager spawnableManager;
    private int maxCapacity;
    private int counter;

    public SpawnableCreateManager(SpawnableManager spawnableManager, int maxCapacity) {
        this.spawnableManager = spawnableManager;
        this.maxCapacity = maxCapacity;
        counter = 0;
    }

    SpawnableType getType() {
        final var spawnableTypesList = Arrays.asList(SpawnableType.values());
        Collections.shuffle(spawnableTypesList);
        return spawnableTypesList.get(ThreadLocalRandom.current().nextInt(0, spawnableTypesList.size()));
    }

    abstract Spawnable getSpawnable(SpawnableType spawnableType, float jumpHeight, float slideDistance);

    public void create(float jumpHeight, float slideDistance) {
        if (isFull()) return;
        var type = getType();
        var spawnable = getSpawnable(type, jumpHeight, slideDistance);
        spawnableManager.add(spawnable);
        ++counter;
    }

    public boolean isFull() {
        return counter == maxCapacity;
    }

    public void addSpace() {
        --counter;
    }
}
