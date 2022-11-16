package pacman;

import java.awt.event.KeyEvent;
import java.util.Map;

public class PacmanController {
    private final Pacman model;
    private final Map<Integer, KeyboardControl> keymap;

    public PacmanController(Pacman model, Map<Integer, KeyboardControl> keymap) {
        this.model = model;
        this.keymap = keymap;
    }

    public void keyPressed(KeyEvent e) {
        this.keymap.getOrDefault(e.getExtendedKeyCode(), KeyboardControl.NOTHING).command.control(this.model);
    }

    public void tick(double delta) {
        this.model.move(delta * 3.0);
    }
}