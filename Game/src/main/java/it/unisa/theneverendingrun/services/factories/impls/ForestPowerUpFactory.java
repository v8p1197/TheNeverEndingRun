package it.unisa.theneverendingrun.services.factories.impls;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.SpriteFactory;

public class ForestPowerUpFactory implements SpriteFactory {

    // TODO delete these 3 methods
    @Override
    public Sprite createSlidableSprite(float maxWidth) {
        return null;
    }

    @Override
    public Sprite createJumpableSprite(float maxHeight) {
        return null;
    }

    @Override
    public Sprite createJumpableSlideableSprite(float maxHeight, float maxWidth) {
        return null;
    }

    /*private static final Texture SWORD_TEXTURE = new Texture("images/forest/powerups/power_up_sword.png");
    private static final Texture SHIELD_TEXTURE = new Texture("images/forest/powerups/power_up_shield.png");
    private static final Texture MULTIPLIER_TEXTURE = new Texture("images/forest/powerups/power_up_x2.png");

    @Override
    public Sprite createSlidableSprite(float maxWidth) {
        throw new UnsupportedOperationException("No slide and jump enemy exists");
    }

    @Override
    public Sprite createJumpableSprite(float maxHeight) {
        return new JumpableSprite(new ForestPowerUp(createPowerUp()), maxHeight);
    }

    @Override
    public Sprite createJumpableSlideableSprite(float maxHeight, float maxWidth) {
        throw new UnsupportedOperationException("No slide and jump enemy exists");
    }

    private Texture createPowerUp() {

        var powerUps = Arrays.stream(PowerUpType.values()).filter(PowerUpType::isJumpable).collect(Collectors.toList());

        if (powerUps.size() == 0) throw new ArrayIndexOutOfBoundsException("Power ups is empty");

        Collections.shuffle(powerUps);
        var powerUpType = powerUps.get(ThreadLocalRandom.current().nextInt(powerUps.size()));
        switch (powerUpType) {
            case SWORD:
                return SWORD_TEXTURE;

            case SHIELD:
                return SHIELD_TEXTURE;

            case MULTIPLIER:
                return MULTIPLIER_TEXTURE;

            default:
                throw new InvalidParameterException("No valid power up");
        }
    }*/
}
