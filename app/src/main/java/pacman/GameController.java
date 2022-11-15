package pacman;

import java.awt.Component;

public class GameController {
    private final Game model;

    private final PacmanController pacman;

    public GameController(Game model, Component window) {
        this.model = model;
        this.pacman = new PacmanController(this.model.pacman);
        window.addKeyListener(this.pacman);
    }
}