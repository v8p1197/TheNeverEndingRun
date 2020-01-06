package it.unisa.theneverendingrun.services.meters;

import it.unisa.theneverendingrun.engine.GameEngine;

/**
 *
 * The class that is delegated to properly updating the meter counter
 */
public class MeterEditor {

    /**
     *
     * {@link MeterEditor#meters} field increases by 1 each time {@link MeterEditor#counter} is equal to METERS_FACTOR
     */
    public final static int METERS_FACTOR = 5;

    /**
     *
     * The meters travelled when the game begins
     */
    public final static int INITIAL_METERS = 0;

    /**
     *
     * The handler for all the {@link MetersEventType} topics related to this class
     */
    private MetersEventManager eventManager;

    /**
     *
     * Increases by 1 each time the {@link GameEngine#update(float)} method is called.
     * If it reaches {@link MeterEditor#METERS_FACTOR}, it's set back to 0
     */
    private int counter;

    /**
     *
     * The observed variable that stores the total travelled meters
     */
    private int meters;

    /**
     *
     * @see MeterEditor#setMeters(int)
     *
     * Set the {@link MeterEditor#counter} and {@link MeterEditor#meters} fields to their initial values
     */
    public MeterEditor() {
        eventManager = new MetersEventManager(MetersEventType.values());
        counter = 0;
        setMeters(INITIAL_METERS);
    }

    /**
     *
     * @see MeterEditor#meters
     *
     * @return the total travelled meters
     */
    public int getMeters() {
        return meters;
    }

    /**
     *
     * {@link MeterEditor#meters} setter: updates the {@code meters} field and notifies all the {@link MetersListener}
     * observers
     *
     * @param meters the new meters value
     */
    private void setMeters(int meters) {
        if (meters < this.meters)
            throw new IllegalArgumentException("The updated value of the meters cannot be less than the current value");

        this.meters = meters;
        eventManager.notify(MetersEventType.METERS_CHANGED, getMeters());
    }

    /**
     *
     * Updates the {@code counter} and {@code meters} fields depending on {@code METERS_FACTOR}
     */
    public void compute() {
        counter++;
        if (counter == METERS_FACTOR) {
            counter = 0;
            setMeters(getMeters() + 1);
        }
    }

    /**
     *
     * @see MeterEditor#eventManager
     *
     * @return the handler for all the {@link MetersEventType} topics related to this class
     */
    public MetersEventManager getEventManager() {
        return eventManager;
    }
}
