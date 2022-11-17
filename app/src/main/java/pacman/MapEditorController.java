package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapEditorController implements KeyListener, MouseListener {
    private final MapEditor model;

    public MapEditorController(MapEditor model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        var tile = this.model.map.getTile(e.getX() / MapView.TILE_SIZE,
                e.getY() / MapView.TILE_SIZE);
        if (tile != null) {
            tile.setWall(!tile.isWall());
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}