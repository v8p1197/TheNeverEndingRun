package it.unisa.theneverendingrun.services.factories.impls;

public enum ForestEnemyFrameType {
    WOLF(true),
    GOLEM(true),
    WITCH(true)
    ;

    private boolean isJumpable;

    ForestEnemyFrameType(boolean isJumpable) {
        this.isJumpable = isJumpable;
    }

    public boolean isJumpable() {
        return isJumpable;
    }
}
