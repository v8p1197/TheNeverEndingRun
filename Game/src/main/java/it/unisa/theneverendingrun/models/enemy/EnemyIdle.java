package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import it.unisa.theneverendingrun.services.EnemyAnimation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyIdle implements EnemyAnimation {

    @Override
    public Animation setAnimation() {
        var tVect = new com.badlogic.gdx.graphics.g2d.TextureRegion[12];
        for (int i = 1; i <= 12; i++) {
            TextureRegion t = new TextureRegion(new Texture("images/enemy/idle/wolf_idle_" + i + ".png"));
            tVect[i - 1] = t;
        }
        return new Animation(0.05f, tVect);
    }
}
