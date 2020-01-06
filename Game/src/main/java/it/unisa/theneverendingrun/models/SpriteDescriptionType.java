package it.unisa.theneverendingrun.models;

public enum SpriteDescriptionType {

    HERO("HERO"),
    OBSTACLE("OBSTACLE"),
    ENEMY("ENEMY"),
    POWER_UP("POWER-UP"),
    ;

    private String name;

    SpriteDescriptionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
