package pacman;

import javax.swing.JFrame;

import pacman.view.MapView;

/**
 * The window of the application
 * <p>
 * This class is essentially useless, because it is only used once and the
 * constructor doesn't do anything diffucult either
 */
public class GameWindow extends JFrame {
    public GameWindow() {
        super("Pac-Man");
        // this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(MapView.TILE_SIZE * 20, MapView.TILE_SIZE * 20);
        // this.setResizable(false);
    }
}