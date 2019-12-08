package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;

public class Sprite extends org.mini2Dx.core.graphics.Sprite {

    /**
     * Store the collision box of the sprite
     */
    private CollisionBox collisionBox;

    /**
     * Sprite constructor. It will set the texture and generate an initial collisionBox
     *
     * @param texture Texture of the component
     */
    public Sprite(Texture texture) {
        super(texture);
        generateCollisionBox();
    }

    /**
     * Sprite constructor. It will set the texture, width and height and generate an initial collisionBox
     *
     * @param texture   Texture of the component
     * @param srcWidth  width of the sprite
     * @param srcHeight height of the sprite
     */
    public Sprite(Texture texture, int srcWidth, int srcHeight) {
        super(texture, srcWidth, srcHeight);
        generateCollisionBox();
    }

    /**
     * Sprite constructor. It will set the texture, coordinates x and y, width and height and generate an initial collisionBox
     *
     * @param texture   Texture of the component
     * @param srcWidth  width of the sprite
     * @param srcHeight height of the sprite
     * @param srcX      coordinate x
     * @param srcY      coordinate y
     */
    public Sprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
        generateCollisionBox();
    }

    /**
     * CollisionBox getter
     */
    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    /**
     * Generate a new collisionBox having the current coordinates (x,y) and the sprite width and height
     */
    private void generateCollisionBox() {
        collisionBox = new CollisionBox(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    /**
     * Position setter and generate new collisionBox
     *
     * @param x x coordinate
     * @param y y coordiante
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        generateCollisionBox();
    }

    /**
     * Coordinate x setter and generate new collisionBox
     *
     * @param x x coordinate
     */
    @Override
    public void setX(float x) {
        super.setX(x);
        generateCollisionBox();
    }

    /**
     * Coordinate y setter and generate new collisionBox
     *
     * @param y y coordinate
     */
    @Override
    public void setY(float y) {
        super.setY(y);
        generateCollisionBox();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        generateCollisionBox();
    }

    /*private Rectangle collisionBoxRect;

     public boolean areColliding(CollisionBox b) { return collisionBox.intersects(b);}

     public Rectangle getHitBox() {
        return collisionBoxRect;
    }

    private generateCollisionBoxRect() {
     collisionBox = componentSprite.getBoundingRectangle();
    }

    public boolean areColliding(Rectangle b){
        return collisionBoxRect.overlaps(b);
    }*/
}

