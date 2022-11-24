package pacman;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.Before;
import org.junit.Test;

public class MonsterTest {

    TileMap map;
    Monster monster;

    @Before
    public void init() {
        map = new TileMap(20);
        monster = new Monster(map.getMonsterSpawn());
        assumeFalse(map.getPacmanSpawn().equals(map.getMonsterSpawn()));
    }
    
    @Test
    public void Monster_collides_with_Pacman_and_Pacman_dies() {
        var pacman = new Pacman(map.getPacmanSpawn());
        monster.collideWith(pacman);
        assertFalse(monster.isDead());
        assertTrue(pacman.isDead());
    }
    
    @Test
    public void Monster_collides_with_strong_Pacman_and_Monster_dies() {
        var pacman = new Pacman(map.getPacmanSpawn());
        pacman.setStrong();
        monster.collideWith(pacman);
        assertTrue(monster.isDead());
        assertFalse(pacman.isDead());
    }
    
    @Test
    public void dead_Monster_respawns_after_10_seconds() {
        monster.die();
        assertTrue(monster.isDead());
        monster.move(0.0, 10.0);
        assertFalse(monster.isDead());
    }
    
    @Test
    public void Monster_respawns_on_monster_spawn_point() {
        monster.setTile(map.getPacmanSpawn());
        assertEquals(map.getPacmanSpawn(), monster.getTile());
        monster.die();
        monster.respawn();
        assertEquals(map.getMonsterSpawn(), monster.getTile());
    }
}