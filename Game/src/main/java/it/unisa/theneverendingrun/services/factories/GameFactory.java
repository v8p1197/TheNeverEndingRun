package it.unisa.theneverendingrun.services.factories;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.AbstractHero;

public interface GameFactory {

    AbstractBackground createBackground();

    AbstractHero createHero();

    Sprite createObstacle(SpriteType spriteType, float maxHeight, float maxWidth);

    Sprite createEnemy(SpriteType spriteType, float maxHeight, float maxWidth);

}
