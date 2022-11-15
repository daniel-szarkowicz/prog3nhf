package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacmanController implements KeyListener {
    private final Pacman model;

    public PacmanController(Pacman model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                this.model.setNextDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_W:
                this.model.setNextDirection(Direction.UP);
                break;
            case KeyEvent.VK_S:
                this.model.setNextDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_D:
                this.model.setNextDirection(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}