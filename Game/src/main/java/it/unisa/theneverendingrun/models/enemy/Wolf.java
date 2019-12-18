package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;

public class Wolf extends AbstractEnemy {

    private static Texture textureWolf = new Texture("images/enemy/wolf/idle/wolf_idle_1.png");


    public Wolf(float width, float height) {
        super(textureWolf);
        flip(false, true);
        super.setSize(width, height);
    }

    @Override
    public void setAnimations() {
        super.setAnimations();
    }
}
