package it.unisa.theneverendingrun.data;

public enum EnemyFrameType {
    WOLF(true),
    GOLEM(true)
    ;

    private boolean isJumpable;

    EnemyFrameType(boolean isJumpable) {
        this.isJumpable = isJumpable;
    }

    public boolean isJumpable() {
        return isJumpable;
    }
}
