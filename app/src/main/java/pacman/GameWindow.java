package pacman;

import javax.swing.JFrame;

import pacman.view.MapView;

public class GameWindow extends JFrame {
    public GameWindow() {
        super("Pac-Man");
        // this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(MapView.TILE_SIZE * 20, MapView.TILE_SIZE * 20);
        // this.setResizable(false);
    }
}