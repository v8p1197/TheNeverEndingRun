package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.services.EnemyAnimation;

public class EnemyAttack implements EnemyAnimation {

    @Override
    public Animation setAnimation() {
        var tVect = new com.badlogic.gdx.graphics.g2d.TextureRegion[10];
        for (int i = 1; i <= 10; i++) {
            TextureRegion t = new TextureRegion(new Texture("images/enemy/attack/wolf_attack_" + i + ".png"));
            tVect[i - 1] = t;
        }
        return new Animation(0.05f, tVect);
    }
}
