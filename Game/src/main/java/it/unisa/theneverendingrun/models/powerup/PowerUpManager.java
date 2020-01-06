package it.unisa.theneverendingrun.models.powerup;

public final class PowerUpManager {
    private static PowerUpManager instance;
    private int swords;
    private int shield;
    private boolean multiplier;

    private PowerUpManager() {
        swords = 0;
        shield = 0;
        multiplier = false;

    }

    public static PowerUpManager getInstance() {
        if (instance == null) {
            instance = new PowerUpManager();
        }
        return instance;
    }

    public int getSwords() {
        return swords;
    }

    public void addSword() {
        if (swords < 3)
            swords++;
    }

    public void removeSword() {
        if (swords > 0)
            swords--;
    }

    public int getShield() {
        return shield;
    }

    public void addShield() {
        if (shield < 3)
            shield++;
    }

    public void removeShield() {
        if (shield > 0)
            shield--;
    }

    public boolean isMultiplier() {
        return multiplier;
    }

    public void setMultiplier(boolean multiplier) {
        if (this.multiplier != multiplier)
            this.multiplier = multiplier;
    }
}
