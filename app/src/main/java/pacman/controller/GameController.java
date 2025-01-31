package pacman.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pacman.PacmanCommand;
import pacman.model.Game;

/**
 * The main controller of the game
 */
public class GameController implements KeyListener {
    private final Game model;

    private final List<EntityController> entityList;

    /**
     * Creates a game controller for the game model
     * 
     * @param model         the model to control
     * @param pacmanKeymaps the keyMaps for the Pac-Man
     */
    public GameController(Game model, List<Map<Integer, PacmanCommand>> pacmanKeymaps) {
        this.model = model;
        this.entityList = new ArrayList<>();
        if (pacmanKeymaps.size() < this.model.pacmanList.size()) {
            throw new RuntimeException("Túl kevés a cucc!");
        }
        for (int i = 0; i < this.model.pacmanList.size(); ++i) {
            this.entityList.add(new PacmanController(this.model.pacmanList.get(i), pacmanKeymaps.get(i)));
        }
        for (var blinky : this.model.blinkyList) {
            this.entityList.add(new BlinkyController(blinky));
        }
        for (var inky : this.model.inkyList) {
            this.entityList.add(new MonsterController(inky));
        }
        for (var pinky : this.model.pinkyList) {
            this.entityList.add(new PinkyController(pinky));
        }
        for (var clyde : this.model.clydeList) {
            this.entityList.add(new ClydeController(clyde));
        }
    }

    /**
     * Updates the controllers
     * 
     * @param delta the change in time
     */
    public void tick(double delta) {
        for (var entity : this.entityList) {
            entity.tick(delta);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.model.active = false;
        }
        for (var entity : entityList) {
            entity.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}