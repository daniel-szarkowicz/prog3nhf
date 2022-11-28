package pacman.controller;

import java.awt.event.KeyEvent;
import java.util.Map;

import pacman.PacmanCommand;
import pacman.model.Entity;

/**
 * The controller for the Pac-Man
 */
public class PacmanController extends EntityController {
    private final Map<Integer, PacmanCommand> keymap;

    public PacmanController(Entity model, Map<Integer, PacmanCommand> keymap) {
        super(model);
        this.keymap = keymap;
    }

    @Override
    public void keyPressed(KeyEvent e, Entity model) {
        this.keymap.getOrDefault(e.getExtendedKeyCode(), PacmanCommand.NOTHING).control(model);
    }

    @Override
    public void control(double delta, Entity model) {
        model.move(3.0, delta);
    }
}