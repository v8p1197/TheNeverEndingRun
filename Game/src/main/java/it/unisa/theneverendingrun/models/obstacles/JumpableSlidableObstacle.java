package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableSlidableObstacle extends AbstractObstacle {

    private double maxJumpHeight;

    private double maxWidth;

    private float maxSlideDistance;

    public JumpableSlidableObstacle(Texture texture, int srcX, int srcY, double maxJumpHeight, double maxWidth, float maxSlideDistance) {
        super(texture, srcX, srcY);
        setMaxSlideDistance(maxSlideDistance);
        setMaxJumpHeight(maxJumpHeight);
        setMaxWidth(maxWidth);
        generateDimensions();
    }

    private double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    private void setMaxJumpHeight(double maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    private double getMaxWidth() {
        return maxWidth;
    }

    private void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    private float getMaxSlideDistance() {
        return maxSlideDistance;
    }

    private void setMaxSlideDistance(float maxSlideDistance) {
        this.maxSlideDistance = maxSlideDistance;
    }

    @Override
    public void generateDimensions() {
        var maxGapHeight = getMaxJumpHeight();
        var minGapHeight = maxGapHeight / 2;

        System.out.println(maxGapHeight);

        var maxGapWidth = getMaxSlideDistance() - 2;
        var minGapWidth = Math.min(getMaxWidth() / 2, (getMaxSlideDistance() / 2) - 2);

        var randomHeight = (float) ThreadLocalRandom.current().nextDouble(minGapHeight, maxGapHeight);
        var randomWidth = (float) ThreadLocalRandom.current().nextDouble(minGapWidth, maxGapWidth);

        setSize(randomWidth, randomHeight);
    }


}
