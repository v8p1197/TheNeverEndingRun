package it.unisa.theneverendingrun.services.collision.strategies;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.hero.AbstractHero;

public abstract class CollisionSideStrategy<T extends Sprite> {

    protected AbstractHero hero;

    public CollisionSideStrategy(AbstractHero hero) {
        this.hero = hero;
    }

    public abstract void collide(T sprite);
}
