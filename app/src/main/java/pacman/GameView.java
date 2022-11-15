package pacman;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameView extends JPanel {
    private final Game model;

    private final PacmanView pacman;

    public GameView(Game model, Container window) {
        this.model = model;
        window.add(this);
        this.pacman = new PacmanView(this.model.pacman);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.pacman.draw((Graphics2D) g);
    }
}