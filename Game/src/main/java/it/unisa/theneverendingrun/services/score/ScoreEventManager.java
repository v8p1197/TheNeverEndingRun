package it.unisa.theneverendingrun.services.score;

import it.unisa.theneverendingrun.services.meters.MetersListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ScoreEventManager {

    /**
     *
     * A Map containing all the {@link ScoreEventType} topics related to {@link ScoreMetersListener} events as keys,
     * and all the {@link ScoreListener} observers subscribed to the key topic as values
     */
    private Map<ScoreEventType, List<ScoreListener>> listeners = new HashMap<>();

    /**
     *
     * Instantiates an empty list of {@link MetersListener} observers for each topic in {@code topics}.
     *
     * @param topics the {@link ScoreEventType} topics related to the {@link ScoreMetersListener} events
     *               the {@link ScoreEventManager} manages
     */
    public ScoreEventManager(ScoreEventType... topics) {
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
    public void subscribe(ScoreEventType eventType, ScoreListener listener) {
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
    public void unsubscribe(ScoreEventType eventType, ScoreListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    /**
     *
     * Notify Observer method: notifies all the {@code listeners} subscribed to the {@code eventType}
     * topic that the {@code score} variable has changed, asking them to update
     *
     * @param eventType the topic whose subscribers will be notified
     * @param score    the observed parameter, containing its new value
     */
    public void notify(ScoreEventType eventType, int score) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users) {
                listener.update(eventType, score);
            }
    }
}
