package pacman;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final List<Pacman> pacmanList;
    public final TileMap map;
    
    public Game() {
        this.map = new TileMap(20);
        this.pacmanList = new ArrayList<>();
        this.pacmanList.add(new Pacman(this.map.getTile(2, 2)));
        this.pacmanList.add(new Pacman(this.map.getTile(2, 3)));
        this.pacmanList.add(new Pacman(this.map.getTile(2, 4)));
    }
}