package pacman;

import java.awt.Graphics2D;

public class PacmanView {
    private final Pacman model;
    public PacmanView(Pacman model) {
        this.model = model;
    }
    
    public void draw(Graphics2D g) {
        g.drawString(this.model.getDirection() + " " + this.model.getNextDirection(), 20, 20);
    }
}
