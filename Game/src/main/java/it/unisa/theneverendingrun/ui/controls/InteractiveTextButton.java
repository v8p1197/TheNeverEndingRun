package it.unisa.theneverendingrun.ui.controls;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import it.unisa.theneverendingrun.ui.Actionable;

public class InteractiveTextButton extends TextButton {

    private Actionable action;

    public InteractiveTextButton(String text, Skin skin, String styleName, Actionable action) {
        super(text, skin, styleName);
        this.action = action;
    }

    public void click() {
        action.apply();
    }
}
