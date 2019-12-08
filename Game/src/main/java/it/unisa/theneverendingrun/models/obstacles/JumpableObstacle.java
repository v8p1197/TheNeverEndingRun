package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableObstacle extends AbstractObstacle {

    /**
     * Store the max jumpable height
     */
    private double maxJumpHeight;

    /**
     * Store the max width (of the hero)
     */
    private double maxWidth;

    /**
     * JumpableObstacle constructor. Set the texture, (x,y), max jumpable height, max width and generate the dimension.
     *
     * @param texture       texture of the object
     * @param srcX          coordinate x
     * @param srcY          coordinate y
     * @param maxJumpHeight max jumpable height
     */
    public JumpableObstacle(Texture texture, int srcX, int srcY, double maxJumpHeight, double maxWidth) {
        super(texture, srcX, srcY);
        this.maxJumpHeight = maxJumpHeight;
        this.maxWidth = maxWidth;
        generateDimensions();
    }

    /**
     * Generate randomly the dimension of the jumpable obstacles given min max gap
     */
    @Override
    public void generateDimensions() {
        var maxGapHeight = maxJumpHeight - 2;
        var minGapHeight = maxJumpHeight * 0.7;
        var maxGapWidth = maxWidth - 1;
        var minGapWidth = maxWidth / 2;

        var randomHeight = (float) ThreadLocalRandom.current().nextDouble(minGapHeight, maxGapHeight);
        var randomWidth = (float) ThreadLocalRandom.current().nextDouble(minGapWidth, maxGapWidth);
        setSize(randomWidth, randomHeight);
    }

}
