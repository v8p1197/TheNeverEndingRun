package it.unisa.theneverendingrun.services.factories;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;

public interface GameFactory {


    /*AbstractScrollingBackground createBackground();

    AbstractHero createHero();

    Sprite createObstacle(SpriteType spriteType, float maxHeight, float maxWidth);

    */Sprite createEnemy(SpriteType spriteType, float maxHeight, float maxWidth);

}
