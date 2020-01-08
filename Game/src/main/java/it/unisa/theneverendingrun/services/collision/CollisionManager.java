package it.unisa.theneverendingrun.services.collision;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.Visitor;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.services.collision.visitors.BeginCollisionVisitor;
import it.unisa.theneverendingrun.services.collision.visitors.EndCollisionVisitor;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionManager {

    public static boolean wasOnObstacle;

    private List<Sprite> collidingSprites;
    private Visitor beginCollisionVisitor;
    private Visitor endCollisionVisitor;

    private Hero hero;

    public CollisionManager(Hero hero) {
        this.hero = hero;
        collidingSprites = new LinkedList<>();
        beginCollisionVisitor = new BeginCollisionVisitor(hero);
        endCollisionVisitor = new EndCollisionVisitor(hero);

        wasOnObstacle = false;
    }

    public void checkCollision(List<Sprite> sprites) {

        //TODO:Controlla
        collidingSprites.stream()
                .filter(sprite -> sprite != null && !CollisionUtils.areColliding(hero, sprite))
                .forEach(this::endCollision);

        collidingSprites = sprites.stream()
                .filter(sprite -> CollisionUtils.areColliding(hero, sprite) && sprite.isVisible())
                .peek(this::beginCollision)
                .collect(Collectors.toList());
    }

    private void beginCollision(Sprite sprite) {
        sprite.accept(beginCollisionVisitor);
    }

    private void endCollision(Sprite sprite) {
        sprite.accept(endCollisionVisitor);
    }


}
