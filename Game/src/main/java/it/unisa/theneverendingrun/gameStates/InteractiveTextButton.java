package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class InteractiveTextButton extends TextButtonDecorator {

    private Actionable action;

    public InteractiveTextButton(TextButton button, Actionable action) {
        super(button);
        this.action = action;
    }

    public void click() {
        action.act();
    }
}
