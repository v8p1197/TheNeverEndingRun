package it.unisa.theneverendingrun.gameStates;

import it.unisa.theneverendingrun.GameEngine;
import org.mini2Dx.core.graphics.Graphics;

/**
 * In this state the rules of the game are displayed on screen. The user can go back to the menu, start a new run or quit
 */
public class HelpState extends GameState {

    public HelpState(GameEngine game) {
        super(game);
    }

    @Override
    public void initialise() {

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void keyAction() {

    }

    @Override
    public void onMenu() {
        game.changeState(new MenuState(game));
    }

    @Override
    public void onPlay() {
        game.changeState(new PlayState(game));
    }

    @Override
    public void onEnded() {
    }

    @Override
    public void onHelp() {
    }
}
