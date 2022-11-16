package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController implements KeyListener {
    private final Game model;

    private final List<PacmanController> pacmanList;

    public GameController(Game model, List<Map<Integer, KeyboardControl>> pacmanKeymaps) {
        this.model = model;
        this.pacmanList = new ArrayList<>();
        if (pacmanKeymaps.size() < this.model.pacmanList.size()) {
            throw new RuntimeException("Túl kevés cucc!");
        }
        for (int i = 0; i < this.model.pacmanList.size(); ++i) {
            var pacman = new PacmanController(this.model.pacmanList.get(i), pacmanKeymaps.get(i));
            this.pacmanList.add(pacman);
        }
    }

    public void tick(double delta) {
        for (var pacman : pacmanList) {
            pacman.tick(delta);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (var pacman : pacmanList) {
            pacman.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}