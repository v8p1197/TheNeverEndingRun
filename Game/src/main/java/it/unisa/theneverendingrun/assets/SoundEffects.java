package it.unisa.theneverendingrun.assets;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import it.unisa.theneverendingrun.engine.GameStateType;
import it.unisa.theneverendingrun.models.hero.HeroStateType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class SoundEffects {

    public static final Map<GameStateType, Music> musics = loadMusics();
    public static final Map<HeroStateType, Sound> sounds = loadSounds();

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
