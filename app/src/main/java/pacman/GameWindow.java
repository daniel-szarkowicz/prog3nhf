package pacman;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public GameWindow() {
        super("Pac-Man");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setResizable(false);
    }
}