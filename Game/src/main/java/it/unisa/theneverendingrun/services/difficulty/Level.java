package it.unisa.theneverendingrun.services.difficulty;

public enum Level {
    LEVEL_MAX(9);

    private int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
