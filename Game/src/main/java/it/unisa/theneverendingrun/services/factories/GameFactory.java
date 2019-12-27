package it.unisa.theneverendingrun.services.factories;

import it.unisa.theneverendingrun.data.SpawnableType;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;

public interface GameFactory {

    AbstractBackground createBackground();

    Hero createHero();

    Spawnable createObstacle(SpawnableType spawnableType, float jumpHeight, float slideDistance);

    Spawnable createEnemy(SpawnableType spawnableType, float jumpHeight, float slideDistance);

}
