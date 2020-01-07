package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.audio.Sound;
import it.unisa.theneverendingrun.utilities.SoundUtils;

public enum HeroStateType {
    STAND(""),
    DEAD(""),
    FALL(""),
    SLIDE("sounds/effects/hero_slide_state_music.mp3"),
    JUMP("sounds/effects/hero_jump_state_music.mp3"),
    RUN("");

    private String soundPath;

    private Sound sound;

    HeroStateType(String soundPath) {
        this.soundPath = soundPath;

        try {
            sound = SoundUtils.getSound(soundPath);
        } catch (IllegalArgumentException exc) {
            sound = null;
        }
    }

    public String getSoundPath() {
        return soundPath;
    }

    public Sound getSound() {
        return sound;
    }
}
