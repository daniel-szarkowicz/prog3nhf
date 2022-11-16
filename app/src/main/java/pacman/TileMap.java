package pacman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

public class TileMap implements Iterable<Tile> {
    private final List<Tile> tiles;
    private final int width;
    private final int height;

    public TileMap(int size) {
        this(size, size);
    }

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new ArrayList<Tile>(this.width * this.height);
        var rng = new Random();
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                var wall = rng.nextFloat() > 0.65 && x != 2;
                tiles.add(new Tile(x, y, this, wall));
            }
        }
    }

    public @Nullable Tile getTile(int x, int y) {
        if (0 <= x && x < this.width && 0 <= y && y < this.height) {
            return tiles.get(y * this.height + x);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<Tile> iterator() {
        return tiles.iterator();
    }
}