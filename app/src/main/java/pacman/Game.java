package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
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
}