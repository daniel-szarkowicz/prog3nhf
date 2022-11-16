package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GameView extends JPanel {
    private final Game model;

    private final List<PacmanView> pacmanList;
    private final MapView map;

    public GameView(Game model, List<Color> pacmanColors) {
        this.model = model;
        this.pacmanList = new ArrayList<>();
        if (pacmanColors.size() < this.model.pacmanList.size()) {
            throw new RuntimeException("Túl kevés a cucc!");
        }
        for (int i = 0; i < this.model.pacmanList.size(); ++i) {
            this.pacmanList.add(new PacmanView(this.model.pacmanList.get(i), pacmanColors.get(i)));
        }
        this.map = new MapView(this.model.map);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        var g2d = (Graphics2D) g;
        this.map.draw(g2d);
        for (var pacman : this.pacmanList) {
            pacman.draw(g2d);
        }
    }
}