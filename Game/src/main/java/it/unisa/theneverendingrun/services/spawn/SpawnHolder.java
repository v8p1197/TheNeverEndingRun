package it.unisa.theneverendingrun.services.spawn;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;

import java.util.AbstractMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SpawnHolder {

    private SpawnHolderEventManager eventManager;
    private final Queue<AbstractMap.SimpleEntry<SpriteType, Sprite>> spawnQueue;

    public SpawnHolder() {
        spawnQueue = new ConcurrentLinkedQueue<>();
        eventManager = new SpawnHolderEventManager(SpawnEventType.REMOVED);
    }

    public boolean add(SpriteType type, Sprite sprite) {
        if (type == null) throw new NullPointerException("type is null");
        if (sprite == null) throw new NullPointerException("sprite is null");

        return spawnQueue.offer(new AbstractMap.SimpleEntry<>(type, sprite));
    }

    public AbstractMap.SimpleEntry<SpriteType, Sprite> view() {
        return spawnQueue.peek();
    }

    public AbstractMap.SimpleEntry<SpriteType, Sprite> get() {
        var entry = spawnQueue.poll();
        if (entry != null && entry.getValue() != null)
            eventManager.notify(SpawnEventType.REMOVED, entry.getValue());
        return entry;
    }

    public SpawnHolderEventManager getEventManager() {
        return eventManager;
    }
}
