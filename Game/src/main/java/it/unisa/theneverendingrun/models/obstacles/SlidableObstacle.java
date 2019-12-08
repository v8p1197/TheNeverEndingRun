package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableObstacle extends AbstractObstacle {

    private float maxSlideDistance;
    private float maxJumpDistance;

    public SlidableObstacle(Texture texture, int srcX, int srcY, float maxSlideDistance, float maxJumpHeight) {
        super(texture, srcX, srcY);
        setMaxSlideDistance(maxSlideDistance);
        setMaxJumpDistance(maxJumpHeight);
    }

    public float getMaxSlideDistance() {
        return maxSlideDistance;
    }

    public void setMaxSlideDistance(float maxSlideDistance) {
        this.maxSlideDistance = maxSlideDistance;
    }

    public float getMaxJumpDistance() {
        return maxJumpDistance;
    }

    public void setMaxJumpDistance(float maxJumpDistance) {
        this.maxJumpDistance = maxJumpDistance;
    }

    @Override
    public void generateDimensions() {
        var maxGap = getMaxSlideDistance() - 2;
        var minGap = (getMaxSlideDistance() / 2) - 2;

        var dimension = (float)ThreadLocalRandom.current().nextDouble(minGap, maxGap);
        setSize(dimension, getMaxJumpDistance());
    }

}
