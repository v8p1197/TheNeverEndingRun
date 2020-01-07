package it.unisa.theneverendingrun.services.speed;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * The handler for the events regarding {@link SpeedDifficultyListener} events.
 * This class is delegated to subscribe and unsubscribe {@link SpeedListener} observers to
 * {@link SpeedEventType} topics and notify them that an observed variable changed.
 */
public class SpeedEventManager {

    /**
     *
     * A Map containing all the {@link SpeedEventType} topics related to {@link SpeedDifficultyListener} events as keys,
     * and all the {@link SpeedListener} observers subscribed to the key topic as values
     */
    private Map<SpeedEventType, List<SpeedListener>> listeners = new HashMap<>();

    /**
     *
     * Instantiates an empty list of {@link SpeedListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link SpeedEventType} topics related to the {@link SpeedDifficultyListener} events
     *               the {@link SpeedEventManager} manages
     */
    public SpeedEventManager(SpeedEventType... topics) {
        if (topics == null) throw new NullPointerException("topics is null");
        if (topics.length == 0) throw new IllegalArgumentException("topics is empty");

        for (var topic : topics) {
            this.listeners.put(topic, new LinkedList<>());
        }
    }

    /**
     *
     * Subscribe Observer method: subscribes {@code listener} to the topic {@code eventType}
     *
     * @param eventType the topic to which subscribe {@code listener}
     * @param listener  the listener to subscribe to {@code eventType} topic
     */
    public void subscribe(SpeedEventType eventType, SpeedListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.add(listener);
    }

    /**
     *
     * Unsubscribe Observer method: unsubscribes {@code listener} from the topic {@code eventType}
     *
     * @param eventType the topic from which unsubscribe {@code listener}
     * @param listener  the listener to unsubscribe from {@code eventType} topic
     */
    public void unsubscribe(SpeedEventType eventType, SpeedListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     *
     * Notify Observer method: notifies all the {@code listeners} subscribed to the {@code eventType}
     * topic that the {@code speed} variable has changed, asking them to update
     *
     * @param eventType the topic whose subscribers will be notified
     * @param speed    the observed parameter, containing its new value
     */
    public void notify(SpeedEventType eventType, float speed) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users) {
                listener.update(eventType, speed);
            }
    }
}
