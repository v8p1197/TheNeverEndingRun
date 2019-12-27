package it.unisa.theneverendingrun.services.managers.spawnables;

import it.unisa.theneverendingrun.data.SpawnableEventType;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.services.events.SpawnableEventManager;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SpawnableManager {

    private SpawnableEventManager eventManager;
    private final Queue<Spawnable> spawnableQueue;

    public SpawnableManager() {
        spawnableQueue = new ConcurrentLinkedQueue<>();
        eventManager = new SpawnableEventManager(SpawnableEventType.DRAWED);
    }

    public boolean add(Spawnable spawnable) {
        if (spawnable == null) throw new NullPointerException("spawnable is null");
        return spawnableQueue.offer(spawnable);
    }

    public Spawnable get() {
        var spawnable = spawnableQueue.poll();
        if (spawnable != null)
            eventManager.notify(SpawnableEventType.DRAWED, spawnable);
        return spawnable;
    }

    public Spawnable view() {
        return spawnableQueue.peek();
    }

    public SpawnableEventManager getEventManager() {
        return eventManager;
    }
}
