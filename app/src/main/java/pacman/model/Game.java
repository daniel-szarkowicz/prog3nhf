package pacman.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Nullable;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The main model of the game
 */
public class Game implements Serializable {
    public final List<Entity> pacmanList;
    public final List<Entity> blinkyList;
    public final List<Entity> pinkyList;
    public final List<Entity> inkyList;
    public final List<Entity> clydeList;
    public final List<Entity> dotList;
    public final List<Entity> largeDotList;
    public final TileMap map;
    public boolean active;

    /**
     * Create a game with a random map and one Pac-Man
     */
    public Game() {
        this(new TileMap(20), 1);
    }

    /**
     * Create a game with a map and some Pac-Man
     * 
     * @param map         the map of the game
     * @param pacmanCount the amount of Pac-Man in the game
     */
    public Game(TileMap map, int pacmanCount) {
        this.map = map;
        this.dotList = new ArrayList<>();
        this.largeDotList = new ArrayList<>();
        this.populateMap();
        this.pacmanList = new ArrayList<>();
        for (int i = 0; i < pacmanCount; ++i) {
            this.pacmanList.add(new Pacman(this.map.getPacmanSpawn()));
        }
        this.map.pacmanList = this.pacmanList;
        this.blinkyList = new ArrayList<>();
        this.pinkyList = new ArrayList<>();
        this.inkyList = new ArrayList<>();
        this.clydeList = new ArrayList<>();
        this.blinkyList.add(new Monster(this.map.getMonsterSpawn()));
        this.pinkyList.add(new Monster(this.map.getMonsterSpawn()));
        this.inkyList.add(new Monster(this.map.getMonsterSpawn()));
        this.clydeList.add(new Monster(this.map.getMonsterSpawn()));
        this.active = true;
    }

    /**
     * Places the dots on the map
     */
    public void populateMap() {
        var random = new Random();
        for (var tile : this.map) {
            if (!tile.isWall()) {
                if (random.nextFloat() < 0.05) {
                    this.largeDotList.add(new LargeDot(tile));
                } else {
                    this.dotList.add(new Dot(tile));
                }
            }
        }
    }

    /**
     * A helper function to save a game with a file chooser
     * 
     * @param game   the game to save
     * @param window the parent of the file chooser
     * @return whether the save was successful
     */
    public static boolean saveFileChoose(Game game, JFrame window) {
        var fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileFilter(new FileNameExtensionFilter("Pac-Man game save (*.game)", "game"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.showSaveDialog(window);
        var file = fc.getSelectedFile();
        if (file == null) {
            return false;
        } else {
            try {
                var os = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
                os.writeObject(game);
                os.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }

    /**
     * A helper function to open a game with a file chooser
     * 
     * @param window the parent of the file chooser
     * @return the loaded game or null if it was unsuccessful
     */
    public static @Nullable Game openFileChoose(JFrame window) {
        var fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileFilter(new FileNameExtensionFilter("Pac-Man game save (*.game)", "game"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.showOpenDialog(window);
        var file = fc.getSelectedFile();
        if (file == null) {
            return null;
        } else {
            try {
                var is = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
                var game = (Game) is.readObject();
                is.close();
                return game;
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }
        }
    }
}