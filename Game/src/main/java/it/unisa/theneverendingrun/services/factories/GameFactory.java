package it.unisa.theneverendingrun.services.factories;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.Hero;

import java.util.List;

public interface GameFactory {

    AbstractScrollingBackground createBackground();

    Hero createHero();

    Sprite createObstacle(SpriteType spriteType);

    Sprite createEnemy();

    Sprite createPowerUp();

    List<Texture> getPowerUpTextures();
}
