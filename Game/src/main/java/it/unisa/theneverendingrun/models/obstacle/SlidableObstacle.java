package it.unisa.theneverendingrun.models.obstacle;

import com.badlogic.gdx.graphics.g2d.Batch;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.engine.geom.CollisionBox;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableObstacle implements Spawnable {


    private final Spawnable obstacle;
    private boolean isResized;

    public SlidableObstacle(Spawnable obstacle) {
        this.obstacle = obstacle;
        isResized = false;
        resizeObstacleToSlide();
    }

    private void resizeObstacleToSlide() {
        var minW = Math.min(getWidth(), getSlideDistance() * 0.5);
        var maxW = getSlideDistance() - 1;

        float newW = (float)ThreadLocalRandom.current().nextDouble(minW, maxW);
        setSize(newW, getHeight());
        isResized = true;
    }

    //***************************** getters *****************************//

    @Override
    public float getJumpHeight() {
        return obstacle.getJumpHeight();
    }

    @Override
    public float getSlideDistance() {
        return obstacle.getSlideDistance();
    }

    @Override
    public float getWidth() {
        return obstacle.getWidth();
    }

    @Override
    public float getHeight() {
        return obstacle.getHeight();
    }

    @Override
    public float getX() {
        return obstacle.getX();
    }

    @Override
    public float getY() {
        return obstacle.getY();
    }

    @Override
    public CollisionBox getCollisionBox() {
        return obstacle.getCollisionBox();
    }




    //***************************** setters *****************************//

    @Override
    public void setSize(float width, float height) {
        if(!isResized)
            obstacle.setSize(width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        obstacle.setPosition(x, y);
    }





    //***************************** check *****************************//

    @Override
    public boolean isJumpable() {
        return obstacle.isJumpable();
    }

    @Override
    public boolean isSlideable() {
        return true;
    }

    @Override
    public boolean isMultipleSlideable() {
        return getWidth() < getSlideDistance() / 2;
    }

    @Override
    public boolean isXAxisVisible() {
        return obstacle.isXAxisVisible();
    }


    //***************************** helpers *****************************//

    @Override
    public void beginCollision(Hero hero) {
        obstacle.beginCollision(hero);
    }

    @Override
    public void endCollision(Hero hero) {
        obstacle.endCollision(hero);
    }

    @Override
    public void draw(Batch batch) {
        obstacle.draw(batch);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        obstacle.draw(batch, alpha);
    }


}
