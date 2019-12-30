package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import java.util.ArrayList;

public class ButtonActionHandler {

    public static void action(ArrayList<InteractiveTextButton> buttons, KeyButtonsStrategy strategy) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            strategy = new KeyDownButtonsStrategy();
            checkNextButton(strategy, buttons);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            strategy = new KeyUpButtonsStrategy();
            checkNextButton(strategy, buttons);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            buttons.stream().filter(Button::isChecked).forEach(InteractiveTextButton::click);
        }
    }


    private static void checkNextButton(KeyButtonsStrategy strategy, ArrayList<InteractiveTextButton> buttons) {

        for (int i = 0; i < buttons.size(); i++) {
            var button = buttons.get(i);
            if (button.isChecked()) {
                button.setChecked(false);
                var nextIndex = strategy.nextIndex(i, buttons.size());
                buttons.get(nextIndex).setChecked(true);
                break;
            }
        }
    }
}
