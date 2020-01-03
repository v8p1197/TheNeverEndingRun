package it.unisa.theneverendingrun.services.difficulty;

import it.unisa.theneverendingrun.services.meters.MetersEventManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DifficultyEventManager {

    /**
     *
     * A Map containing all the {@link DifficultyEventType} topics related to {@link DifficultyMeterListener} events as keys,
     * and all the {@link DifficultyListener} observers subscribed to the key topic as values
     */
    private Map<DifficultyEventType, List<DifficultyListener>> listeners = new HashMap<>();

    /**
     *
     * Instantiates an empty list of {@link DifficultyListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link DifficultyEventType} topics related to the {@link DifficultyMeterListener} events
     *               the {@link MetersEventManager} manages
     */
    public DifficultyEventManager(DifficultyEventType... topics) {
        if (topics == null) throw new NullPointerException("topic is null");
        if (topics.length == 0) throw new IllegalArgumentException("topics is empty");

        for (var topic : topics)
            listeners.put(topic, new LinkedList<>());
    }

    /**
     *
     * Subscribe Observer method: subscribes {@code listener} to the topic {@code eventType}
     *
     * @param eventType the topic to which subscribe {@code listener}
     * @param listener  the listener to subscribe to {@code eventType} topic
     */
    public void subscribe(DifficultyEventType eventType, DifficultyListener listener) {
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
    public void unsubscribe(DifficultyEventType eventType, DifficultyListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     *
     * Notify Observer method: notifies all the {@code listeners} subscribed to the {@code eventType} topic that the {@code difficulty}
     * variable has changed, asking them to update
     *
     * @param eventType  the topic whose subscribers will be notified
     * @param difficulty the observed parameter, containing its new value
     */
    public void notify(DifficultyEventType eventType, int difficulty) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users)
                listener.update(eventType, difficulty);
    }
}
