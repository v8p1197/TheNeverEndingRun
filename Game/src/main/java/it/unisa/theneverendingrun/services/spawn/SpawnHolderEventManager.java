package it.unisa.theneverendingrun.services.spawn;

import it.unisa.theneverendingrun.models.Sprite;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * The handler for the events regarding
 * {@link SpawnHolder} events.
 * This class is delegated to subscribe and unsubscribe {@link SpawnEventListener} observers
 * to {@link SpawnEventType} topics
 * and notify them that an observed variable changed.
 */
public class SpawnHolderEventManager {

    /**
     *
     * A Map containing all the {@link SpawnEventType} topics related to
     * {@link SpawnHolder} events as keys,
     * and all the {@link SpawnEventListener} observers subscribed to the key topic as values
     */
    private Map<SpawnEventType, List<SpawnEventListener>> listeners = new HashMap<>();

    /**
     *
     * Instantiates an empty list of {@link SpawnEventListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link SpawnEventType} topics related to the
     *               {@link SpawnHolder} events
     *               that the {@link SpawnHolderEventManager} manages
     */
    public SpawnHolderEventManager(SpawnEventType... topics) {
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
    public void subscribe(SpawnEventType eventType, SpawnEventListener listener) {
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
    public void unsubscribe(SpawnEventType eventType, SpawnEventListener listener) {
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
    public void notify(SpawnEventType eventType, Sprite value) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users) {
                listener.update(eventType, value);
            }
    }
}
