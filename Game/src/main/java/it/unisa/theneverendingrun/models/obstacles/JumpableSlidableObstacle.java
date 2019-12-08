package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.math.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableSlidableObstacle extends AbstractForestComponent {

    private final int MAX_JUMP_HEIGHT;
    private final int MAX_SLIDE_DISTANCE;
    private int width;
    private int height;
    private Rectangle rectBound;

    public JumpableSlidableObstacle(int STANDARD_HEIGHT, int MAX_JUMP_HEIGHT, int MAX_SLIDE_DISTANCE, float posX, float posY) {
        super(STANDARD_HEIGHT, posX, posY);
        this.MAX_JUMP_HEIGHT = MAX_JUMP_HEIGHT;
        this.MAX_SLIDE_DISTANCE = MAX_SLIDE_DISTANCE;
        this.generateDimensions();
    }

    @Override
    public void generateDimensions() {
        int randomHeight = ThreadLocalRandom.current().nextInt(super.getSTANDARD_HEIGHT(), MAX_JUMP_HEIGHT + 1);
        randomHeight = (randomHeight % super.getSTANDARD_HEIGHT()) * super.getSTANDARD_HEIGHT();
        int randomDistance = ThreadLocalRandom.current().nextInt(super.getSTANDARD_HEIGHT(), MAX_SLIDE_DISTANCE + 1);
        randomDistance = (randomDistance % super.getSTANDARD_HEIGHT()) * super.getSTANDARD_HEIGHT();
        width = randomDistance;
        height = randomHeight;
    }

    @Override
    public Rectangle getHitBox() {
        rectBound = new Rectangle();
        rectBound.setX((float) super.getComponentX());
        rectBound.setY((float) super.getComponentY());
        rectBound.height = (float) height;
        rectBound.width = (float) width;
        return rectBound;
    }


}
