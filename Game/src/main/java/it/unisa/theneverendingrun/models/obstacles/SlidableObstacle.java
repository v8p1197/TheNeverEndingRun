package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableObstacle extends AbstractObstacle {

    private float maxSlideDistance;
    private float maxJumpHeight;

    public SlidableObstacle(Texture texture, int srcX, int srcY, float maxSlideDistance, float maxJumpHeight) {
        super(texture, srcX, srcY);
        setMaxSlideDistance(maxSlideDistance);
        setMaxJumpHeight(maxJumpHeight);
        generateDimensions();
    }

    public float getMaxSlideDistance() {
        return maxSlideDistance;
    }

    public void setMaxSlideDistance(float maxSlideDistance) {
        this.maxSlideDistance = maxSlideDistance;
    }

    public float getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxJumpHeight(float maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public void generateDimensions() {
        var maxGap = getMaxSlideDistance() - 2;
        var minGap = (getMaxSlideDistance() / 2) - 2;

        var dimension = (float)ThreadLocalRandom.current().nextDouble(minGap, maxGap);
        setSize(dimension, Gdx.graphics.getHeight());
    }

}
