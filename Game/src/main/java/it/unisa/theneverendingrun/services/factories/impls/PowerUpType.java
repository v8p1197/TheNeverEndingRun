package it.unisa.theneverendingrun.services.factories.impls;

public enum PowerUpType {
    SWORD(true),
    SHIELD(true),
    MULTIPLIER(true);

    private boolean isJumpable;

    PowerUpType(boolean isJumpable) {
        this.isJumpable = isJumpable;
    }

    public boolean isJumpable() {
        return isJumpable;
    }
}


