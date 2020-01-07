package it.unisa.theneverendingrun.services.spawn.creation;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.spawn.SpawnEventListener;
import it.unisa.theneverendingrun.services.spawn.SpawnEventType;
import it.unisa.theneverendingrun.services.spawn.SpawnHolder;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class SpawnCreationManager implements SpawnEventListener {

    private SpawnHolder spawnHolder;
    private List<AbstractCreationTemplate> creationTemplates;
    private final int maxCapacity;
    private volatile AtomicInteger counter;
    private final float maxHeight;
    private final float maxWidth;

    public SpawnCreationManager(SpawnHolder spawnHolder, List<AbstractCreationTemplate> creationTemplates,
                                int maxCapacity, float maxHeight, float maxWidth) {

        this.spawnHolder = spawnHolder;
        this.creationTemplates = creationTemplates;
        this.maxCapacity = maxCapacity;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;

        this.counter = new AtomicInteger(0);
    }

    public void create() {
        if (isFull()) return;
        Collections.shuffle(creationTemplates);
        var template = creationTemplates.get(ThreadLocalRandom.current().nextInt(creationTemplates.size()));
        var sprite = template.create(maxHeight, maxWidth);
        spawnHolder.add(sprite.getKey(), sprite.getValue());
        counter.incrementAndGet();
    }

    private boolean isFull() {
        return counter.intValue() == maxCapacity;
    }

    @Override
    public void update(SpawnEventType type, Sprite value) {
        if (type == SpawnEventType.REMOVED)
            counter.decrementAndGet();
    }
}
