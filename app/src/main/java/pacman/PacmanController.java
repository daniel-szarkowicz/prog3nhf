package pacman;

import java.awt.event.KeyEvent;
import java.util.Map;

public class PacmanController {
    private final Entity model;
    private final Map<Integer, PacmanCommand> keymap;

    public PacmanController(Entity model, Map<Integer, PacmanCommand> keymap) {
        this.model = model;
        this.keymap = keymap;
    }

    public void keyPressed(KeyEvent e) {
        this.keymap.getOrDefault(e.getExtendedKeyCode(), PacmanCommand.NOTHING).control(this.model);
    }

    public void tick(double delta) {
        this.model.move(delta * 3.0);
    }
}