package it.unisa.theneverendingrun.models.enemy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EnemyEventManager {

    private Map<EnemyEventType, LinkedList<EnemyFightStateListener>> listeners = new HashMap<>();

    public EnemyEventManager(EnemyEventType... topics) {
        for (var topic : topics) {
            this.listeners.put(topic, new LinkedList<>());
        }
    }

    void subscribe(EnemyEventType eventType, EnemyFightStateListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.add(listener);
    }

    void unsubscribe(EnemyEventType eventType, EnemyFightStateListener listener) {
        var users = listeners.get(eventType);
        if (users != null)
            users.remove(listener);
    }

    void notify(EnemyEventType eventType, AbstractEnemy enemy) {
        var users = listeners.get(eventType);
        if (users != null)
            for (var listener : users)
                listener.update(eventType, enemy);
    }
}
