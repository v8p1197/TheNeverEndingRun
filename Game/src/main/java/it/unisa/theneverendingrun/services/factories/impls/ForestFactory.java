package it.unisa.theneverendingrun.services.factories.impls;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.background.impls.PlayStateBackground;
import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.hero.impls.ForestHero;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.factories.SpriteFactory;

public class ForestFactory implements GameFactory {

    private SpriteFactory obstacleFactory;
    private SpriteFactory enemyFactory;
    private int screenWidth;
    private int screenHeight;


    public ForestFactory(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        obstacleFactory = new ForestObstacleFactory();
        enemyFactory = new ForestEnemyFactory();
    }

    @Override
    public AbstractScrollingBackground createBackground() {
        return new PlayStateBackground(screenWidth, screenHeight);
    }


    @Override
    public AbstractHero createHero() {
        var hero = new ForestHero(PlayStateBackground.BASE_X * screenWidth, PlayStateBackground.BASE_Y * screenHeight);
        hero.flip(false, true);
        return hero;
    }

    @Override
    public Sprite createObstacle(SpriteType spriteType, float maxHeight, float maxWidth) {
        return createSprite(obstacleFactory, spriteType, maxHeight, maxWidth);
    }

    @Override
    public Sprite createEnemy(SpriteType spriteType, float maxHeight, float maxWidth) {
       return createSprite(enemyFactory, spriteType, maxHeight, maxWidth);
    }



    private Sprite createSprite(SpriteFactory spriteFactory, SpriteType spriteType, float maxHeight, float maxWidth) {
        switch (spriteType) {
            case JUMPABLE: return spriteFactory.createJumpableSprite(maxHeight);
            case SLIDABLE: return spriteFactory.createSlidableSprite(maxWidth);
            case JUMPABLE_SLIDABLE: return spriteFactory.createJumpableSlideableSprite(maxHeight, maxWidth);
        }

        throw new IllegalArgumentException("Sprite type is not valid");
    }
}
