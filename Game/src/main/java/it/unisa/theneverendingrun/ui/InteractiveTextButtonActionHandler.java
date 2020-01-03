package it.unisa.theneverendingrun.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import it.unisa.theneverendingrun.services.strategies.KeyStrategy;
import it.unisa.theneverendingrun.services.strategies.impls.NextKeyStrategy;
import it.unisa.theneverendingrun.services.strategies.impls.PreviousKeyStrategy;

import java.util.List;

public interface InteractiveTextButtonActionHandler extends ActionHandler<InteractiveTextButton> {

    default void action(List<InteractiveTextButton> buttons) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            var strategy = new NextKeyStrategy();
            checkNextButton(buttons, strategy);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            var strategy = new PreviousKeyStrategy();
            checkNextButton(buttons, strategy);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            buttons.stream().filter(Button::isChecked).findFirst().ifPresent(InteractiveTextButton::click);
        }
    }

    private static void checkNextButton(List<InteractiveTextButton> buttons, KeyStrategy strategy) {
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
