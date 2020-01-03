package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.audio.Music;
import it.unisa.theneverendingrun.utilities.SoundEffectsUtils;

public enum GameStateType {
    PLAY("sounds/music/playMusic.mp3", true),
    ENDED("sounds/music/menuMusic.mp3", true),
    MENU("sounds/music/menuMusic.mp3", true),
    HELP("sounds/music/helpMusic.mp3", true);

    private String musicPath;

    private boolean looping;

    private Music music;

    GameStateType(String musicPath, boolean looping) {
        this.musicPath = musicPath;
        this.looping = looping;

        try {
            music = SoundEffectsUtils.getMusic(musicPath, looping);
        } catch (IllegalArgumentException exc) {
            music = null;
        }
    }

    public String getSoundPath() {
        return musicPath;
    }

    public boolean getLooping() {
        return looping;
    }

    public Music getMusic() {
        return music;
    }
}
