package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapEditorController implements KeyListener, MouseListener {
    private final MapEditor model;
    public MapEditorMode mode;

    public MapEditorController(MapEditor model) {
        this.model = model;
        this.mode = MapEditorMode.WALL;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.model.active = false;
        }
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
        if (tile != null && this.model.map.getPacmanSpawn() != tile && this.model.map.getMonsterSpawn() != tile) {
            switch (this.mode) {
                case WALL:
                    tile.setWall(!tile.isWall());
                    break;
                case PACMAN_SPAWN:
                    if (!tile.isWall())
                        this.model.map.setPacmanSpawn(tile);
                    break;
                case MONSTER_SPAWN:
                    if (!tile.isWall())
                        this.model.map.setMonsterSpawn(tile);
                    break;
            }
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