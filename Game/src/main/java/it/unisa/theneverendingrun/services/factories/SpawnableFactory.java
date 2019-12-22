package it.unisa.theneverendingrun.services.factories;

import it.unisa.theneverendingrun.models.spawnables.Spawnable;

public interface SpawnableFactory {

    Spawnable createSlidableSpawnable(float jumpHeight, float slideDistance);
    Spawnable createJumpableSpawnable(float jumpHeight, float slideDistance);
    Spawnable createJumpableSlideableSpawnable(float jumpHeight, float slideDistance);
}
