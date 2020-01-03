package it.unisa.theneverendingrun.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public final class SoundEffectsUtils {

    public static Music getMusic(String musicPath, boolean looping) {
        if (musicPath == null || musicPath.isEmpty())
            throw new IllegalArgumentException("music path is not valid");

        var music = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
        music.setLooping(looping);
        return music;
    }

    public static Sound getSound(String soundPath) {
        if (soundPath == null || soundPath.isEmpty())
            throw new IllegalArgumentException("sound path is not valid");

        return Gdx.audio.newSound(Gdx.files.internal(soundPath));
    }
}
