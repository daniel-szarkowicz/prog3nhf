package pacman;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.Before;
import org.junit.Test;

public class TileTest {
    TileMap map;
    Tile t1;
    Tile t2;
    Tile t3;
    Tile t4;

    @Before
    public void init() {
        map = new TileMap(2, 2);
        /*
         * t1 t2
         * t3 t4
         */
        t1 = map.getTile(0, 0);
        t2 = map.getTile(1, 0);
        t3 = map.getTile(0, 1);
        t4 = map.getTile(1, 1);
        t1.setWall(false);
        t2.setWall(false);
        t3.setWall(false);
        t4.setWall(true);
        map.setPacmanSpawn(t2);
        map.setMonsterSpawn(t3);
    }

    @Test
    public void Move_entity_to_free_tile() {
        Entity entity = new Pacman(map.getPacmanSpawn());
        entity.setTile(t1);
        assertEquals(t1, entity.getTile());
        t1.moveEntity(entity, Direction.DOWN);
        assertEquals(t3, entity.getTile());
    }

    @Test
    public void Move_entity_to_wall() {
        Entity entity = new Pacman(map.getPacmanSpawn());
        entity.setTile(t2);
        assertEquals(t2, entity.getTile());
        t2.moveEntity(entity, Direction.DOWN);
        assertEquals(t2, entity.getTile());
    }

    @Test
    public void Move_entity_out_of_bounds() {
        Entity entity = new Pacman(map.getPacmanSpawn());
        entity.setTile(t3);
        assertEquals(t3, entity.getTile());
        t3.moveEntity(entity, Direction.DOWN);
        assertEquals(t3, entity.getTile());
    }
}
