package it.unisa.theneverendingrun.services.events.listener;

import it.unisa.theneverendingrun.data.SpawnableEventType;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.services.managers.spawnables.SpawnableManager;

/**
 * A common interface for all the subscribers interested in observing the
 * {@link SpawnableManager} variables
 */
public interface SpawnableEventListener {

    /**
     * The {@link SpawnableEventListener}
     * listener reaction when the observed variables changes
     *
     * @param value the new value for the observed variable
     */
    void update(SpawnableEventType type, Spawnable value);
}
