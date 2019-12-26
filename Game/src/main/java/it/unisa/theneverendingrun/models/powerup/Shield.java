package it.unisa.theneverendingrun.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.SpawnableTypes;


public class Shield extends AbstractPowerUp {

    public Shield(Texture texture, float width, float height) {
        super(texture, SpawnableTypes.Shield);
        super.setSize(width, height);
        
    }


}
