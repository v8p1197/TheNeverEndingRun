package it.unisa.theneverendingrun.metersManager;

/**
 * The class that is delegated to properly updating the meter counter
 */
class MeterEditor {

    /**
     * {@code meters} field increases by 1 each time counter is equal to METERS_FACTOR
     */
    private final static int METERS_FACTOR = 5;

    /**
     * The meters travelled when the game begins
     */
    private final static int INITIAL_METERS = 0;

    /**
     * The handler for all the {@link MetersEventType} topics related to this class
     */
    static MetersEventManager events;

    /**
     * {@code counter} increases by 1 each time the update() method is called.
     * If it reaches {@code METERS_FACTOR}, it's set back to 0
     */
    private static int counter;

    /**
     * The observed variable that stores the total travelled meters
     */
    static int meters;

    static int getMetersFactor() {
        return METERS_FACTOR;
    }

    /**
     * {@code meters} getter
     *
     * @return the total travelled meters
     */
    static int getMeters() {
        return meters;
    }

    /**
     * {@code meters} setter: updates the {@code meters} field and notifies all the {@link MetersListener} observers
     *
     * @param meters the new meters value
     */
    private static void setMeters(int meters) {
        MeterEditor.meters = meters;
        events.notify(MetersEventType.METERS_CHANGED, getMeters());
    }

    /**
     * Updates the {@code counter} and {@code meters} fields depending on {@code METERS_FACTOR}
     */
    static void compute() {
        counter++;
        if (counter == METERS_FACTOR) {
            counter = 0;
            setMeters(getMeters() + 1);
        }
    }

    /**
     * Set the {@code counter} and {@code meters} fields back to their initial values
     */
    static void initialise() {
        counter = 0;
        setMeters(INITIAL_METERS);
    }
}
