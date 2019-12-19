package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.engine.geom.CollisionBox;

public interface Spawnable {

    float getJumpHeight();

    float getSlideDistance();

    float getWidth();

    float getHeight();

    float getX();

    float getY();

    void setSize(float width, float height);

    boolean isJumpable();

    boolean isSlideable();

    boolean isMultipleSlideable();

    boolean isXAxisVisible();

    void draw(Batch batch);

    void draw(Batch batch, float alpha);

    void beginCollision(Hero hero);

    void endCollision(Hero hero);

    void setPosition(float x, float y);

    CollisionBox getCollisionBox();

}
