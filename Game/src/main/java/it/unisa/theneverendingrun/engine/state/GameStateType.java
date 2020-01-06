package it.unisa.theneverendingrun.engine.state;

import com.badlogic.gdx.audio.Music;
import it.unisa.theneverendingrun.utilities.SoundUtils;

public enum GameStateType {
    PLAY("sounds/music/playMusic.mp3", true),
    ENDED("sounds/music/menuMusic.mp3", true),
    MENU("sounds/music/menuMusic.mp3", true),
    HELP("sounds/music/helpMusic.mp3", true);

    private Music music;

    GameStateType(String musicPath, boolean looping) {

        try {
            music = SoundUtils.getMusic(musicPath, looping);
        } catch (IllegalArgumentException exc) {
            music = null;
        }
    }

    public Music getMusic() {
        return music;
    }
}
