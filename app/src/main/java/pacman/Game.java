package pacman;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final List<Entity> pacmanList;
    public final List<Entity> monsterList;
    public final TileMap map;
    
    public Game() {
        this.map = new TileMap(20);
        this.pacmanList = new ArrayList<>();
        this.pacmanList.add(new Entity(this.map.getTile(2, 2)));
        this.pacmanList.add(new Entity(this.map.getTile(2, 3)));
        this.pacmanList.add(new Entity(this.map.getTile(2, 4)));
        this.monsterList = new ArrayList<>();
        this.monsterList.add(new Entity(this.map.getTile(6, 2)));
        this.monsterList.add(new Entity(this.map.getTile(10, 2)));
        this.monsterList.add(new Entity(this.map.getTile(14, 2)));
    }
}