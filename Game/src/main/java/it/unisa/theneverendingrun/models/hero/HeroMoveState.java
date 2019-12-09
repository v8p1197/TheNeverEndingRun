package it.unisa.theneverendingrun.models.hero;


import it.unisa.theneverendingrun.models.SpriteHandler;
import org.mini2Dx.core.graphics.Sprite;

/**
 * The State representing if the hero is jumping, sliding, falling or none of them:
 * the states are called Idle, Jump, Slide and Fall.
 * Also is delegated to compute the sprite image
 */
public abstract class HeroMoveState {

    /**
     * The hero which move state is held
     */
    protected Hero hero;

    /**
     * A variable representing the fall step the hero actually is in, i.e. the speed the hero is falling down with
     */
    private int fallCount;

    /**
     * The Animator array (need to compute this as a circular queue)
     */
    private Sprite[] sprites;

    /**
     * The sprite circular array pointer. It it reaches the end of the array, it starts from the beginning
     */
    private int spriteCount;

    /**
     * Sets the hero for holding its move state
     *
     * @param hero the hero which move state is held
     */
    public HeroMoveState(Hero hero) {
        this.hero = hero;

        this.spriteCount = 0;
        this.sprites = SpriteHandler.getSprites(getSpritePath(), 51);

        computeImage();
    }

    /**
     * Updates the hero bottom-left coordinates and sprite
     */
    public void move() {
        updateCoordinates();
        computeImage();
    }

    /**
     * Updates the hero bottom-left coordinates depending on its current horizontal velocity.
     * (This means that if it is 0, the hero actually doesn't move)
     * Plus, makes the hero fall if it is above the ground
     */
    private void updateCoordinates() {
        if (hero.isRight()) {
            moveRight();
        } else if (hero.isLeft()) {
            moveLeft();
        }
    }

    /**
     * Makes the hero move to the right depending on its current horizontal velocity.
     */
    private void moveRight() {
        hero.setX(hero.getX() + hero.getDx());
    }

    /**
     * Makes the hero move to the left depending on its current horizontal velocity.
     */
    private void moveLeft() {
        hero.setX(hero.getX() - hero.getDx());
    }

    /**
     * Updates the hero sprite image, iterating on the images circular array.
     * If the hero is facing left, it flips the image horizontally
     */

    private void computeImage() {
        Sprite image = sprites[spriteCount];
        spriteCount = (spriteCount + 1) % sprites.length;

        if (hero.isLeft()) {
            image = flipImage(image);
        }

        hero.setSprite(image);
    }


    /**
     * Flips a sprite horizontally
     *
     * @return a new sprite flipped on x axe
     */
    private Sprite flipImage(Sprite image) {
        System.out.println("flipping");
        var flipped = new org.mini2Dx.core.graphics.Sprite(image);
        flipped.flip(true, false);
        return flipped;
    }

    /**
     * The reaction when the state tries to change to Idle
     */
    public abstract void onIdle();

    /**
     * The reaction when the state tries to change to Jump
     */
    public abstract void onJump();

    /**
     * The reaction when the state tries to change to Slide
     */
    public abstract void onSlide();

    /**
     * The reaction when the state tries to change to Fall
     */
    public abstract void onFall();

    /**
     * Computes the proper sprite source path depending on the current hero horizontal velocity
     *
     * @return the sprite source path
     */
    protected abstract String getSpritePath();

    public int getSpriteCount(){ return spriteCount;}
}

