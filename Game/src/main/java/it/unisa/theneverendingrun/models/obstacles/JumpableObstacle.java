package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableObstacle extends AbstractObstacle {

    private double maxJumpHeight;
    private double maxWidth;

    public JumpableObstacle(Texture texture, int srcX, int srcY, double maxJumpHeight, double maxWidth) {
        super(texture, srcX, srcY);
        setMaxJumpHeight(maxJumpHeight);
        setMaxWidth(maxWidth);
    }

    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxJumpHeight(double maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    public double getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    @Override
    public void generateDimensions() {
        var maxGapHeight = getMaxJumpHeight() - 2;
        var minGapHeight = getMaxJumpHeight() * 0.7;
        var maxGapWidth = getMaxWidth() - 1;
        var minGapWidth = getMaxWidth() / 2;

        var randomHeight = (float) ThreadLocalRandom.current().nextDouble(minGapHeight, maxGapHeight);
        var randomWidth = (float) ThreadLocalRandom.current().nextDouble(minGapWidth, maxGapWidth);
        setSize(randomWidth, randomHeight);
    }

}
