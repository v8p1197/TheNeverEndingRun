package it.unisa.theneverendingrun.services.spawn;

import it.unisa.theneverendingrun.models.Sprite;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SpawnHolder {

    private SpawnHolderEventManager eventManager;
    private final Queue<Sprite> spawnQueue;

    public SpawnHolder() {
        spawnQueue = new ConcurrentLinkedQueue<>();
        eventManager = new SpawnHolderEventManager(SpawnEventType.REMOVED);
    }

    public boolean add(Sprite sprite) {
        if (sprite == null) throw new NullPointerException("sprite is null");
        return spawnQueue.offer(sprite);
    }

    public Sprite get() {
        var sprite = spawnQueue.poll();
        if (sprite != null)
            eventManager.notify(SpawnEventType.REMOVED, sprite);
        return sprite;
    }

    public Sprite view() {
        return spawnQueue.peek();
    }

    public SpawnHolderEventManager getEventManager() {
        return eventManager;
    }
}
