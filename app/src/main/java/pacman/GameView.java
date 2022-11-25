package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GameView extends JPanel {
    private final Game model;

    private final List<EntityView> entityList;
    private final MapView map;

    public GameView(Game model, List<Color> pacmanColors) {
        this.model = model;
        this.entityList = new ArrayList<>();
        if (pacmanColors.size() < this.model.pacmanList.size()) {
            throw new RuntimeException("Túl kevés a cucc!");
        }
        for (int i = 0; i < this.model.pacmanList.size(); ++i) {
            this.entityList.add(new PacmanView(this.model.pacmanList.get(i), pacmanColors.get(i)));
        }
        for (var dot : this.model.dotList) {
            this.entityList.add(new DotView(dot));
        }
        for (var largeDot : this.model.largeDotList) {
            this.entityList.add(new LargeDotView(largeDot));
        }
        for (var blinky: this.model.blinkyList) {
            this.entityList.add(new MonsterView(blinky, Color.RED));
        }
        for (var inky: this.model.inkyList) {
            this.entityList.add(new MonsterView(inky, Color.BLUE));
        }
        for (var pinky: this.model.pinkyList) {
            this.entityList.add(new MonsterView(pinky, Color.PINK));
        }
        for (var clyde: this.model.clydeList) {
            this.entityList.add(new MonsterView(clyde, Color.ORANGE));
        }
        this.map = new MapView(this.model.map);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.setBackground(Color.BLACK);
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        // RenderingHints.VALUE_ANTIALIAS_ON);
        this.map.draw(g2d);
        for (var entity : this.entityList) {
            entity.draw(g2d);
        }
    }
}