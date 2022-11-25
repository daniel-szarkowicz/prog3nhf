package pacman;

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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Game implements Serializable {
    public final List<Entity> pacmanList;
    public final List<Entity> blinkyList;
    public final List<Entity> pinkyList;
    public final List<Entity> inkyList;
    public final List<Entity> dotList;
    public final List<Entity> largeDotList;
    public final TileMap map;
    public boolean active;

    public Game() {
        this(new TileMap(20));
    }

    public Game(TileMap map) {
        this.map = map;
        this.dotList = new ArrayList<>();
        this.largeDotList = new ArrayList<>();
        this.populateMap();
        this.pacmanList = new ArrayList<>();
        this.pacmanList.add(new Pacman(this.map.getPacmanSpawn()));
        this.pacmanList.add(new Pacman(this.map.getPacmanSpawn()));
        this.pacmanList.add(new Pacman(this.map.getPacmanSpawn()));
        this.blinkyList = new ArrayList<>();
        this.pinkyList = new ArrayList<>();
        this.inkyList = new ArrayList<>();
        this.blinkyList.add(new Monster(this.map.getMonsterSpawn()));
        this.pinkyList.add(new Monster(this.map.getMonsterSpawn()));
        this.inkyList.add(new Monster(this.map.getMonsterSpawn()));
        this.active = true;
    }

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

    public static Game openFileChoose(JFrame window) {
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