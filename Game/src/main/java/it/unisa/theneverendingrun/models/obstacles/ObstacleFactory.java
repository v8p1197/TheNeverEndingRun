package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

public class ObstacleFactory {

    private final float MAX_JUMP_HEIGHT;
    private final float MAX_SLIDE_DISTANCE;
    private final float MAX_WIDTH;

    private Texture textureJumpable;
    private Texture textureSlidable;
    private Texture textureJumpableSlidable;

    public ObstacleFactory(float maxJumpHeight, float maxSlideDistance, float maxWidth) {
        MAX_JUMP_HEIGHT = maxJumpHeight;
        MAX_SLIDE_DISTANCE = maxSlideDistance;
        MAX_WIDTH = maxWidth;
        textureJumpable = new Texture("images/jumpable.png");
        textureJumpableSlidable = new Texture("images/jumpableSlidable.png");
        textureSlidable = new Texture("images/slidable.png");
    }

    public AbstractObstacle getObstacle(ObstacleType type, int srcX, int srcY) throws TypeNotPresentException {
        //todo assign a texture based on the type and dimension
        Texture texture;
        switch (type) {
            case Jumpable:
                return new JumpableObstacle(textureJumpable, srcX, srcY, MAX_JUMP_HEIGHT, MAX_WIDTH);
            case Slidable:
                return new SlidableObstacle(textureSlidable, srcX, srcY, MAX_SLIDE_DISTANCE, MAX_JUMP_HEIGHT);
            case JumpableSlidable:
                return new JumpableSlidableObstacle(textureJumpableSlidable, srcX, srcY, MAX_JUMP_HEIGHT, MAX_WIDTH, MAX_SLIDE_DISTANCE);
            default:
                throw new IllegalArgumentException("The obstacle type requested is not correct");
        }
    }
}
