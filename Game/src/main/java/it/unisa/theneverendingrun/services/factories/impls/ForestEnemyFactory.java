package it.unisa.theneverendingrun.services.factories.impls;

import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.models.spawnables.decorators.JumpableSpawnable;
import it.unisa.theneverendingrun.models.spawnables.decorators.SlidableSpawnable;
import it.unisa.theneverendingrun.models.spawnables.enemy.impls.ForestEnemy;
import it.unisa.theneverendingrun.services.factories.SpawnableFactory;

public class ForestEnemyFactory implements SpawnableFactory {

    @Override
    public Spawnable createSlidableSpawnable(float jumpHeight, float slideDistance) {
        return new SlidableSpawnable(new ForestEnemy(null, jumpHeight, slideDistance));
    }

    @Override
    public Spawnable createJumpableSpawnable(float jumpHeight, float slideDistance) {
        return new JumpableSpawnable(new ForestEnemy(null, jumpHeight, slideDistance));
    }

    @Override
    public Spawnable createJumpableSlideableSpawnable(float jumpHeight, float slideDistance) {
        return new JumpableSpawnable(new SlidableSpawnable(new ForestEnemy(null, jumpHeight, slideDistance)));
    }
}
