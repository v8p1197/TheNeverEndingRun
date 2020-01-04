package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import it.unisa.theneverendingrun.engine.GameStateType;
import it.unisa.theneverendingrun.models.hero.HeroStateType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Assets {

    public static final SoundEffects soundEffects;
    public static final Fonts fonts;

    static {
        soundEffects = new SoundEffects();
        fonts = new Fonts();
    }

    public static final class SoundEffects {

        public final Map<GameStateType, Music> musics;
        public final Map<HeroStateType, Sound> sounds;

        private SoundEffects() {
            musics = loadMusics();
            sounds = loadSounds();
        }

        private static Map<GameStateType, Music> loadMusics() {
            return Arrays.stream(GameStateType.values())
                    .filter(stateType -> stateType.getMusic() != null)
                    .collect(Collectors.toMap(k -> k, GameStateType::getMusic));
        }

        private static Map<HeroStateType, Sound> loadSounds() {
            return Arrays.stream(HeroStateType.values())
                    .filter(stateType -> stateType.getSound() != null)
                    .collect(Collectors.toMap(k -> k, HeroStateType::getSound));
        }
    }

    public static final class Fonts {

        public final BitmapFont scoreFont;
        public final BitmapFont meterFont;
        public final BitmapFont endScreenTitleFont;
        public final BitmapFont endScreenScoreFont;

        private Fonts() {
            scoreFont = loadScoreFont();
            meterFont = loadMeterFont();
            endScreenTitleFont = loadEndScreenTitleFont();
            endScreenScoreFont = loadEndScreenScoreFont();
        }

    /*
    static {
        scoreFont = loadScoreFont();
        meterFont = loadMeterFont();
        endScreenTitleFont = loadEndScreenTitleFont();
        endScreenScoreFont = loadEndScreenScoreFont();
    }
    */

        private static BitmapFont loadEndScreenScoreFont() {
            //Generate a font object for font.ttf at size 40px
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 24;
            parameter.flip = false;
            parameter.color = Color.WHITE;

            return applyCommonParameters(parameter);
        }

        private static BitmapFont loadEndScreenTitleFont() {
            //Generate a font object for font.ttf at size 40px
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 32;
            parameter.flip = false;
            parameter.color = Color.RED;

            return applyCommonParameters(parameter);
        }

        private static BitmapFont loadScoreFont() {
            //Generate a font object for font.ttf at size 40px
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 24;
            parameter.flip = false;
            parameter.color = Color.WHITE;
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 2;
            parameter.shadowOffsetX = 2;
            parameter.shadowOffsetY = 2;
            parameter.shadowColor = Color.BLACK;

            return applyCommonParameters(parameter);
        }

        private static BitmapFont loadMeterFont() {
            //Generate a font object for font.ttf at size 40px
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 18;
            parameter.flip = false;
            parameter.color = Color.WHITE;
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 2;
            parameter.shadowOffsetX = 1;
            parameter.shadowOffsetY = 1;
            parameter.shadowColor = Color.BLACK;

            return applyCommonParameters(parameter);
        }

        private static BitmapFont applyCommonParameters(FreeTypeFontGenerator.FreeTypeFontParameter parameter) {
            //The following settings allow the font to scale smoothly
            parameter.magFilter = Texture.TextureFilter.Linear;
            parameter.minFilter = Texture.TextureFilter.Linear;

            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arcade_font.ttf"));

            var font = generator.generateFont(parameter);
            font.setUseIntegerPositions(false);

            generator.dispose();

            return font;
        }
    }

}

