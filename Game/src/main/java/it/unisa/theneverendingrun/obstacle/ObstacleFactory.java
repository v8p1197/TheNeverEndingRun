package it.unisa.theneverendingrun.obstacle;

public class ObstacleFactory {
    private final int MAX_JUMP_HEIGHT;
    private final int MAX_SLIDE_DISTANCE;
    private final int STANDARD_HEIGHT;
    private float posX;
    private float posY;
    private ObstacleType t;

    public ObstacleFactory(int max_jump_height, int max_slide_distance, int standard_height) {
        MAX_JUMP_HEIGHT = max_jump_height;
        MAX_SLIDE_DISTANCE = max_slide_distance;
        STANDARD_HEIGHT = standard_height;
        this.t = null;
    }

    public AbstractForestComponent getObstacle(ObstacleType type) throws TypeNotPresentException {
        switch (type) {
            case Jumpable:
                this.t = ObstacleType.Jumpable;
                return new JumpableObstacle(STANDARD_HEIGHT, MAX_JUMP_HEIGHT, posX, posY);
            case Slidable:
                this.t = ObstacleType.Slidable;
                return new SlidableObstacle(STANDARD_HEIGHT, MAX_SLIDE_DISTANCE, posX, posY);
            case JumpableSlidable:
                this.t = ObstacleType.JumpableSlidable;
                return new JumpableSlidableObstacle(STANDARD_HEIGHT, MAX_JUMP_HEIGHT, MAX_SLIDE_DISTANCE, posX, posY);
        }
        throw new IllegalArgumentException("The obstacle type requested is not correct");
    }

    public ObstacleType getType() {
        return t;
    }


}
