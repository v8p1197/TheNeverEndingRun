package it.unisa.theneverendingrun.data;

public enum EnemyFrameType {
    WOLF(true),
    GOLEM(true),
    WITCH(true)
    ;

    private boolean isJumpable;

    EnemyFrameType(boolean isJumpable) {
        this.isJumpable = isJumpable;
    }

    public boolean isJumpable() {
        return isJumpable;
    }
}
