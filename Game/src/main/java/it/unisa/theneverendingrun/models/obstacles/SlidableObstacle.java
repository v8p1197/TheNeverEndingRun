package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableObstacle extends AbstractObstacle {

    private final int MAX_SLIDE_DISTANCE;
    private int width;
    private int height;
    private Rectangle rectBound;

    public SlidableObstacle(Texture texture, int srcX, int srcY) {
        super(texture, srcX, srcY);
        generateDimensions();
    }

/*

    public SlidableObstacle(int STANDARD_HEIGHT, int MAX_SLIDE_DISTANCE, float posX, float posY) {
        super(STANDARD_HEIGHT, posX, posY);
        this.MAX_SLIDE_DISTANCE = MAX_SLIDE_DISTANCE;
        this.generateDimensions();
    }*/

    @Override
    public void generateDimensions() {
        int randomDistance = ThreadLocalRandom.current().nextInt(super.getSTANDARD_HEIGHT(), MAX_SLIDE_DISTANCE + 1);
        randomDistance = (randomDistance % super.getSTANDARD_HEIGHT()) * super.getSTANDARD_HEIGHT();
        width = randomDistance;
        height = super.getSTANDARD_HEIGHT();
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
