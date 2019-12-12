package it.unisa.theneverendingrun.metersManager;

interface MetersListener {

    /**
     * The {@link MetersListener} listener reaction when the observed variable {@code meters} changes
     *
     * @param meters the new value for the observed variable
     */
    void update(int meters);
}
