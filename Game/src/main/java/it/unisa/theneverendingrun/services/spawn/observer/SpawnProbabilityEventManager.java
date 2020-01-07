package it.unisa.theneverendingrun.services.spawn.observer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SpawnProbabilityEventManager {

    /**
     *
     * A Map containing all the {@link SpawnProbabilityEventType} topics related to
     * {@link SpawnProbabilityDifficultyListener} events as keys,
     * and all the {@link SpawnProbabilityListener} observers subscribed to the key topic as values
     */
    private Map<SpawnProbabilityEventType, List<SpawnProbabilityListener>> listeners = new HashMap<>();

    /**
     *
     * Instantiates an empty list of {@link SpawnProbabilityListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link SpawnProbabilityEventType} topics related to the
     *               {@link SpawnProbabilityDifficultyListener} events the {@link SpawnProbabilityEventManager} manages
     */
    public SpawnProbabilityEventManager(SpawnProbabilityEventType... topics) {
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
    public void subscribe(SpawnProbabilityEventType eventType, SpawnProbabilityListener listener) {
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
    public void unsubscribe(SpawnProbabilityEventType eventType, SpawnProbabilityListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     *
     * Notify Observer method: notifies all the {@code listeners} subscribed to the {@code eventType}
     * topic that the {@code spawnProbability} variable has changed, asking them to update
     *
     * @param eventType the topic whose subscribers will be notified
     * @param spawnProbability the observed parameter, containing its new value
     */
    public void notify(SpawnProbabilityEventType eventType, int spawnProbability) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users) {
                listener.update(eventType, spawnProbability);
            }
    }
}