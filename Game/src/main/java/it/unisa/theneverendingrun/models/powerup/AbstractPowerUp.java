package it.unisa.theneverendingrun.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.hero.Hero;

public class AbstractPowerUp extends Spawnable {

    public AbstractPowerUp(Texture texture) {
        super(texture);
    }

    @Override
    public void beginCollision(Hero hero) {
        
    }

    @Override
    public void endCollision(Hero hero) {

    }
}
