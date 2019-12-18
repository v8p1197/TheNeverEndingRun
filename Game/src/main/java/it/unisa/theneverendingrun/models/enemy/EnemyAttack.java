package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.services.EnemyAnimation;

public class EnemyAttack implements EnemyAnimation {

    private TextureRegion[] tVect = new com.badlogic.gdx.graphics.g2d.TextureRegion[13];

    @Override
    public Animation setAnimation(String commonPath) {
        //if (tVect[0] == null)
        for (int i = 1; i <= 13; i++) {
            TextureRegion t = new TextureRegion(new Texture(commonPath + "attack_" + i + ".png"));
                tVect[i - 1] = t;
            }
        return new Animation(0.05f, tVect);

    }
}
