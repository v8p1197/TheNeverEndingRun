package it.unisa.theneverendingrun.services.meters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * The handler for the events regarding {@link MeterEditor} events.
 * This class is delegated to subscribe and unsubscribe {@link MetersListener} observers to {@link MetersEventType} topics
 * and notify them that an observed variable changed.
 */
public class MetersEventManager {

    /**
     *
     * A Map containing all the {@link MetersEventType} topics related to {@link MeterEditor} events as keys,
     * and all the {@link MetersListener} observers subscribed to the key topic as values
     */
    private Map<MetersEventType, List<MetersListener>> listeners = new HashMap<>();

    /**
     *
     * Instantiates an empty list of {@link MetersListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link MetersEventType} topics related to the {@link MeterEditor} events
     *               the {@link MetersEventManager} manages
     */
    public MetersEventManager(MetersEventType... topics) {
        if (topics == null) throw new NullPointerException("topic is null");
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
    public void subscribe(MetersEventType eventType, MetersListener listener) {
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
    public void unsubscribe(MetersEventType eventType, MetersListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     *
     * Notify Observer method: notifies all the {@code listeners} subscribed to the {@code eventType} topic that the {@code meters}
     * variable has changed, asking them to update
     *
     * @param eventType the topic whose subscribers will be notified
     * @param meters    the observed parameter, containing its new value
     */
    public void notify(MetersEventType eventType, int meters) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users) {
                listener.update(eventType, meters);
            }
    }
}
