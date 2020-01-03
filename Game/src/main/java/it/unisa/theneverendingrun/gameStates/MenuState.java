package it.unisa.theneverendingrun.gameStates;

import it.unisa.theneverendingrun.GameEngine;
import org.mini2Dx.core.graphics.Graphics;

// TODO must be implemented in next US's

/**
 * In this state the game displays the main menu, from which the user can start a run, open the help menu or quit
 */
public class MenuState extends GameState {

    public MenuState(GameEngine game) {
        super(game);
    }

    @Override
    public void initialise() {

    }

    @Override
    public void update(float delta) {

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
        game.changeState(new HelpState(game));
    }
}
