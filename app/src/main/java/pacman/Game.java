package pacman;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final List<Entity> pacmanList;
    public final List<Entity> monsterList;
    public final List<Entity> dotList;
    public final TileMap map;
    public boolean active;
    
    public Game() {
        this(new TileMap(20));
    }

    public Game(TileMap map) {
        this.map = map;
        this.dotList = new ArrayList<>();
        this.populateMap();
        this.pacmanList = new ArrayList<>();
        this.pacmanList.add(new Pacman(this.map.getPacmanSpawn()));
        this.pacmanList.add(new Pacman(this.map.getPacmanSpawn()));
        this.pacmanList.add(new Pacman(this.map.getPacmanSpawn()));
        this.monsterList = new ArrayList<>();
        this.monsterList.add(new Monster(this.map.getMonsterSpawn()));
        this.monsterList.add(new Monster(this.map.getMonsterSpawn()));
        this.monsterList.add(new Monster(this.map.getMonsterSpawn()));
        this.active = true;
    }
    
    public void populateMap() {
        for (var tile : this.map) {
            if (!tile.isWall())
            this.dotList.add(new Dot(tile));
        }
    }
}