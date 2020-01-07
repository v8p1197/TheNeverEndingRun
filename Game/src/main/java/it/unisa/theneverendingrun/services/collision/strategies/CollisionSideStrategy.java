package it.unisa.theneverendingrun.services.collision.strategies;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.hero.Hero;

public abstract class CollisionSideStrategy<T extends Sprite> {

    protected Hero hero;

    public CollisionSideStrategy(Hero hero) {
        this.hero = hero;
    }

    public abstract void collide(T sprite);
}
