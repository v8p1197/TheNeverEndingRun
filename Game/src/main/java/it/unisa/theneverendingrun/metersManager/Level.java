package it.unisa.theneverendingrun.metersManager;

enum Level {
    LEVEL_MAX(10);

    private int value;

    Level(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
