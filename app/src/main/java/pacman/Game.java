package pacman;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final List<Entity> pacmanList;
    public final List<Entity> monsterList;
    public final List<Entity> dotList;
    public final TileMap map;
    
    public Game() {
        this(new TileMap(20));
    }

    public Game(TileMap map) {
        this.map = map;
        this.dotList = new ArrayList<>();
        this.populateMap();
        this.pacmanList = new ArrayList<>();
        this.pacmanList.add(new Entity(this.map.getTile(2, 2)));
        // this.pacmanList.add(new Entity(this.map.getTile(2, 3)));
        // this.pacmanList.add(new Entity(this.map.getTile(2, 4)));
        this.monsterList = new ArrayList<>();
        this.monsterList.add(new Entity(this.map.getTile(6, 2)));
        this.monsterList.add(new Entity(this.map.getTile(10, 2)));
        this.monsterList.add(new Entity(this.map.getTile(14, 2)));
    }
    
    public void populateMap() {
        for (var tile : this.map) {
            if (!tile.isWall())
            this.dotList.add(new Entity(tile));
        }
    }
}