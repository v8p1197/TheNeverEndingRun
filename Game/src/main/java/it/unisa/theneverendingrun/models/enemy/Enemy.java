package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends AbstractEnemy {

    public Enemy(Texture texture, float width, float height) {
        super(texture);
        flip(false, true);
        super.setSize(width, height);
    }
}
