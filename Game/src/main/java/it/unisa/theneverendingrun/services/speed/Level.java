package it.unisa.theneverendingrun.services.speed;

public enum Level {
    LEVEL_MAX(10);

    private int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
