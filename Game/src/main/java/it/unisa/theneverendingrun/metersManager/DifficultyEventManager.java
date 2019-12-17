package it.unisa.theneverendingrun.metersManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class DifficultyEventManager {

    /**
     * A Map containing all the {@link DifficultyEventType} topics related to {@link DifficultyMeterListener} events as keys,
     * and all the {@link DifficultyListener} observers subscribed to the key topic as values
     */
    private Map<DifficultyEventType, List<DifficultyListener>> listeners = new HashMap<>();

    /**
     * Instantiates an empty list of {@link DifficultyListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link DifficultyEventType} topics related to the {@link DifficultyMeterListener} events
     *               the {@link MetersEventManager} manages
     */
    DifficultyEventManager(DifficultyEventType... topics) {
        for (var topic : topics)
            listeners.put(topic, new LinkedList<>());
    }

    /**
     * Subscribe Observer method: subscribes {@code listener} to the topic {@code eventType}
     *
     * @param eventType the topic to which subscribe {@code listener}
     * @param listener  the listener to subscribe to {@code eventType} topic
     */
    void subscribe(DifficultyEventType eventType, DifficultyListener listener) {
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
    void unsubscribe(DifficultyEventType eventType, DifficultyListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     * Notify Observer method: notifies all the {@code listeners} subscribed to the {@code eventType} topic that the {@code difficulty}
     * variable has changed, asking them to update
     *
     * @param eventType  the topic whose subscribers will be notified
     * @param difficulty the observed parameter, containing its new value
     */
    void notify(DifficultyEventType eventType, int difficulty) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users)
                listener.update(difficulty);
    }
}
