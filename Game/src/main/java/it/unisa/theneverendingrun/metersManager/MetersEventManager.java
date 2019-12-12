package it.unisa.theneverendingrun.metersManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class MetersEventManager {

    /**
     * A Map containing all the {@link MetersEventType} topics related to {@link MeterEditor} events as keys,
     * and all the {@link MetersListener} listeners subscribed to the key topic as value.
     */
    private Map<MetersEventType, List<MetersListener>> listeners = new HashMap<>();

    /**
     * Instantiates an empty list of {@link MetersListener} listeners for each topic in {@code topics}.
     *
     * @param topics the {@link MetersEventType} topics related to {@link MeterEditor} events the {@link MetersEventManager}
     *               manages
     */
    public MetersEventManager(MetersEventType... topics) {
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
    public void subscribe(MetersEventType eventType, MetersListener listener) {
        var users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Unsubscribe Observer method: unsubscribes {@code listener} to the topic {@code eventType}
     *
     * @param eventType the topic to which unsubscribe {@code listener}
     * @param listener  the listener to unsubscribe to {@code eventType} topic
     */
    public void unsubscribe(MetersEventType eventType, MetersListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     * Notify Observer method: notifies all the listeners subscribed to the {@code eventType} topic that the meters
     * variable has changed, asking them to update
     *
     * @param eventType the topic whose subscribers will be notified
     * @param meters    the observed parameter, containing its new value
     */
    public void notify(MetersEventType eventType, int meters) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users) {
                listener.update(meters);
            }
    }
}
