package it.unisa.theneverendingrun.services.events;

import it.unisa.theneverendingrun.data.SpawnableEventType;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.services.events.listener.SpawnableEventListener;
import it.unisa.theneverendingrun.services.managers.spawnables.SpawnableManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * The handler for the events regarding
 * {@link SpawnableManager} events.
 * This class is delegated to subscribe and unsubscribe {@link SpawnableEventListener} observers
 * to {@link SpawnableEventType} topics
 * and notify them that an observed variable changed.
 */
public class SpawnableEventManager {

    /**
     *
     * A Map containing all the {@link SpawnableEventType} topics related to
     * {@link SpawnableManager} events as keys,
     * and all the {@link SpawnableEventListener} observers subscribed to the key topic as values
     */
    private Map<SpawnableEventType, List<SpawnableEventListener>> listeners = new HashMap<>();

    /**
     *
     * Instantiates an empty list of {@link SpawnableEventListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link SpawnableEventType} topics related to the
     *               {@link SpawnableManager} events
     *               that the {@link SpawnableEventManager} manages
     */
    public SpawnableEventManager(SpawnableEventType... topics) {
        if (topics == null) throw new NullPointerException("topic is null");
        if (topics.length == 0) throw new IllegalArgumentException("topics is empty");

        for (var topic : topics) {
            this.listeners.put(topic, new LinkedList<>());
        }
    }

    /**
     * Subscribe Observer method: subscribes {@code listener} to the topic {@code eventType}
     *
     * @param eventType the topic to which subscribe {@code listener}
     * @param listener  the listener to subscribe to {@code eventType} topic
     */
    public void subscribe(SpawnableEventType eventType, SpawnableEventListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.add(listener);
    }

    /**
     * Unsubscribe Observer method: unsubscribes {@code listener} from the topic {@code eventType}
     *
     * @param eventType the topic from which unsubscribe {@code listener}
     * @param listener  the listener to unsubscribe from {@code eventType} topic
     */
    public void unsubscribe(SpawnableEventType eventType, SpawnableEventListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     * Notify Observer method: notifies all the {@code listeners} subscribed to the {@code eventType} topic that the {@code meters}
     * variable has changed, asking them to update
     *
     * @param eventType the topic whose subscribers will be notified
     * @param value     the observed parameter, containing it's new value
     */
    public void notify(SpawnableEventType eventType, Spawnable value) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users) {
                listener.update(eventType, value);
            }
    }
}
