package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableObstacle extends AbstractObstacle {

    private float maxSlideDistance;
    private float maxJumpDistance;

    public SlidableObstacle(Texture texture, int srcX, int srcY, float maxSlideDistance, float maxJumpDistance) {
        super(texture, srcX, srcY);
        setMaxSlideDistance(maxSlideDistance);
        setMaxJumpDistance(maxJumpDistance);
    }

    private float getMaxJumpDistance() {
        return this.maxJumpDistance;
    }

    private float getMaxSlideDistance() {
        return this.maxSlideDistance;
    }

    private void setMaxSlideDistance(float maxSlideDistance) {
        this.maxSlideDistance = maxSlideDistance;
    }

    private void setMaxJumpDistance(float maxJumpDistance) {
        this.maxJumpDistance = maxJumpDistance;
    }


    @Override
    public void generateDimensions() {
        var maxGap = getMaxSlideDistance() - 2;
        var minGap = (getMaxSlideDistance() / 2) - 2;

        var dimension = (float)ThreadLocalRandom.current().nextDouble(maxGap - minGap);
        setSize(dimension, getMaxJumpDistance());
    }

}
