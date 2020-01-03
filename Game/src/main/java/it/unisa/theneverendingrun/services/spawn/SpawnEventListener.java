package it.unisa.theneverendingrun.services.spawn;

import it.unisa.theneverendingrun.models.Sprite;

/**
 * A common interface for all the subscribers interested in observing the
 * {@link Sprite} variables
 */
public interface SpawnEventListener {

    /**
     * The {@link SpawnEventListener}
     * listener reaction when the observed variables changes
     *
     * @param value the new value for the observed variable
     */
    void update(SpawnEventType type, Sprite value);
}
