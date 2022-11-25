package pacman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Nullable;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TileMap implements Iterable<Tile>, Serializable {
    private List<Tile> tiles;
    private int width;
    private int height;
    public Tile pacmanSpawn;
    public Tile monsterSpawn;

    public TileMap(int size) {
        this(size, size);
    }

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new ArrayList<Tile>(this.width * this.height);
        var rng = new Random();
        Tile firstNotWall = null;
        Tile lastNotWall = null;
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                var wall = rng.nextFloat() > 0.65 && x != 2 && x != this.width - 3 && y != 2 && y != this.width - 3;
                var tile = new Tile(x, y, this, wall);
                if (!wall) {
                    if (firstNotWall == null) {
                        firstNotWall = tile;
                    } else {
                        lastNotWall = tile;
                    }
                }
                tiles.add(tile);
            }
        }
        this.pacmanSpawn = firstNotWall;
        this.monsterSpawn = lastNotWall;
    }

    public @Nullable Tile getTile(int x, int y) {
        if (0 <= x && x < this.width && 0 <= y && y < this.height) {
            return tiles.get(y * this.width + x);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<Tile> iterator() {
        return tiles.iterator();
    }

    public void from(TileMap map) {
        this.tiles = map.tiles;
        this.width = map.width;
        this.height = map.height;
        this.pacmanSpawn = map.pacmanSpawn;
        this.monsterSpawn = map.monsterSpawn;
    }

    public Tile getPacmanSpawn() {
        return this.pacmanSpawn;
    }

    public void setPacmanSpawn(Tile pacmanSpawn) {
        this.pacmanSpawn = pacmanSpawn;
    }

    public Tile getMonsterSpawn() {
        return this.monsterSpawn;
    }

    public void setMonsterSpawn(Tile monsterSpawn) {
        this.monsterSpawn = monsterSpawn;
    }

    public static boolean saveFileChoose(TileMap map, JFrame window) {
        var fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileFilter(new FileNameExtensionFilter("Pac-Man map (*.map)", "map"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.showSaveDialog(window);
        var file = fc.getSelectedFile();
        if (file == null) {
            return false;
        } else {
            try {
                var os = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
                os.writeObject(map);
                os.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }

    public static TileMap openFileChoose(JFrame window) {
        var fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileFilter(new FileNameExtensionFilter("Pac-Man map (*.map)", "map"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.showOpenDialog(window);
        var file = fc.getSelectedFile();
        if (file == null) {
            return null;
        } else {
            try {
                var is = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
                var map = (TileMap) is.readObject();
                is.close();
                return map;
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }
        }
    }
}