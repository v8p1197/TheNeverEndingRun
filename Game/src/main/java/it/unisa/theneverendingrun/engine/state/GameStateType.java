package it.unisa.theneverendingrun.engine.state;

import com.badlogic.gdx.audio.Music;
import it.unisa.theneverendingrun.utilities.SoundUtils;

public enum GameStateType {
    PLAY("sounds/music/play_state_music.mp3", true),
    ENDED("sounds/music/lost_state_music.mp3", false),
    MENU("sounds/music/menu_state_music.mp3", true),
    HELP("sounds/music/help_state_music.mp3", true);

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
