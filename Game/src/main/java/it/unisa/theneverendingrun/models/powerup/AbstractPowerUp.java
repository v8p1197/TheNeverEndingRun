package it.unisa.theneverendingrun.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.SpawnableTypes;
import it.unisa.theneverendingrun.models.hero.Hero;

public class AbstractPowerUp extends Spawnable {

    private SpawnableTypes type;
    private Boolean collected;

    public AbstractPowerUp(Texture texture, SpawnableTypes type) {
        super(texture);
        this.type = type;
        this.collected = false;
    }

    public SpawnableTypes getType() {
        return type;
    }

    public Boolean isCollected() {
        return collected;
    }

    @Override
    public void beginCollision(Hero hero) {
        if (type == SpawnableTypes.Shield) {
            // hero.addShield();
        }
        this.collected = true;
    }

    @Override
    public void endCollision(Hero hero) {

    }
}
