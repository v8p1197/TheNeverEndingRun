package it.unisa.theneverendingrun.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public final class SoundUtils {

    public static Music getMusic(String musicPath, boolean looping) {
        if (musicPath == null) throw new NullPointerException("musicPath is null");
        if (musicPath.isEmpty()) throw new IllegalArgumentException("musicPath is not valid");

        var music = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
        music.setLooping(looping);
        return music;
    }

    public static Sound getSound(String soundPath) {
        if (soundPath == null) throw new NullPointerException("soundPath is null");
        if (soundPath.isEmpty()) throw new IllegalArgumentException("soundPath is not valid");

        return Gdx.audio.newSound(Gdx.files.internal(soundPath));
    }
}
