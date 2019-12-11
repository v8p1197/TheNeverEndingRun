package it.unisa.theneverendingrun;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.models.hero.ForestHero;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;


public class GameEngine extends BasicGame {

    public static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";
    private HandlingInput input;

    private Hero hero;
    private SpriteBatch spriteBatch;

    private static final int FRAME_COLS = 8;
    private static final int FRAME_ROWS = 1;

  //  private Texture walkSheet;

  //  float stateTime;

   // Animation<TextureRegion> walkAnimation;

    @Override
    public void initialise() {
        spriteBatch = new SpriteBatch();
        input = new HandlingInput();

        // Load the sprite sheet as a Texture
        var walkSheet = new Texture(Gdx.files.internal("stand.png"));
        hero = new ForestHero(walkSheet, 100,100);
        hero.flip(false, true);

       /* // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.05f, walkFrames);*/

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
       // stateTime = 0f;
    }

    @Override
    public void update(float delta) {
        //stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        hero.updateDelta(Gdx.graphics.getDeltaTime());
        input.getKeyWASD(hero);
        hero.move();
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
        //TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
      //  hero.setTexture(currentFrame.getTexture());
       // hero.setRegion(currentFrame);
    //    hero.setSize(128,128);
        spriteBatch.begin();
        hero.draw(spriteBatch);
      //  sspriteBatch.draw(hero); // Draw current frame at (50, 50)
        spriteBatch.end();
    }
}
