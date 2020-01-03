package it.unisa.theneverendingrun.services.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

public final class SoundManager {

    private boolean soundOn;
    private boolean musicOn;
    private Array<Sound> sounds;
    private Array<Music> musics;
    private int pausedMusicIndex;

    private static SoundManager soundManager;

    private SoundManager() {
        soundOn = true;
        musicOn = true;
        sounds = new Array<>();
        musics = new Array<>();
        musics.add(Gdx.audio.newMusic(Gdx.files.internal("sounds/music/playMusic.mp3"))); //musicIndex = 0
        musics.get(0).setLooping(true);
        musics.add(Gdx.audio.newMusic(Gdx.files.internal("sounds/music/playerDiedMusic.mp3"))); //musicIndex = 1
        musics.add(Gdx.audio.newMusic(Gdx.files.internal("sounds/music/menuMusic.mp3"))); //musicIndex = 2
        musics.get(2).setLooping(true);
        musics.add(Gdx.audio.newMusic(Gdx.files.internal("sounds/music/helpMusic.mp3"))); //musicIndex = 3
        musics.get(3).setLooping(true);

        sounds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/effects/heroJump.mp3"))); //soundIndex = 0
        sounds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/effects/heroSlide.mp3")));//soundIndex = 1

    }

    public static SoundManager getSoundManager() {
        if (soundManager == null)
            soundManager = new SoundManager();
        return soundManager;
    }

    //--------------------------------------GETTERS-------------------------------
    public boolean isSoundOn() {
        return soundOn;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    //----------------------------------SETTERS----------------------------------
    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public void setSound(int soundNumber) {
        if (isSoundOn())
            sounds.get(soundNumber).play();
    }

    public void setMusic(int musicNumber) {
        if (isMusicOn()) {
            for (Music track : musics) {
                track.stop();
            }
            musics.get(musicNumber).play();
        }
    }

    public void muteMusic() {
        for (Music track : musics) {
            if (track.isPlaying())
                track.setVolume(0f);
        }
        setMusicOn(false);
    }

    public void resumeMusic() {
        for (Music track : musics) {
            if (track.isPlaying())
                track.setVolume(1);
        }
        setMusicOn(true);
    }

    public void onPause() {
        for (Music track : musics) {
            if (track.isPlaying())
                track.setVolume(0.2f);
        }
    }
}
