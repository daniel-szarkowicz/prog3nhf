package pacman;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.Before;
import org.junit.Test;

import pacman.model.LargeDot;
import pacman.model.Monster;
import pacman.model.Pacman;
import pacman.model.TileMap;

public class PacmanTest {
    TileMap map;
    Pacman pacman;

    @Before
    public void init() {
        map = new TileMap(20);
        pacman = new Pacman(map.getPacmanSpawn());
        assumeFalse(map.getPacmanSpawn().equals(map.getMonsterSpawn()));
    }
    
    @Test
    public void Pacman_collides_with_Monster_and_Pacman_dies() {
        var monster = new Monster(map.getMonsterSpawn());
        pacman.collideWith(monster);
        assertTrue(pacman.isDead());
        assertFalse(monster.isDead());
    }
    
    @Test
    public void Pacman_meets_LargeDot_and_becomes_strong() {
        var largeDot = new LargeDot(map.getMonsterSpawn());
        largeDot.meetPacman(pacman);
        assertTrue(pacman.isStrong());
        assertTrue(largeDot.isDead());
    }
    
    @Test
    public void strong_Pacman_collides_with_Monster_and_Monster_dies() {
        var monster = new Monster(map.getMonsterSpawn());
        pacman.setStrong();
        assertTrue(pacman.isStrong());
        pacman.collideWith(monster);
        assertFalse(pacman.isDead());
        assertTrue(monster.isDead());
    }
    
    @Test
    public void strong_Pacman_becomes_weak_after_5_seconds() {
        pacman.setStrong();
        assertTrue(pacman.isStrong());
        pacman.move(0.0, 5.0);
        assertFalse(pacman.isStrong());
    }
    
    @Test
    public void dead_Pacman_respawns_after_10_seconds() {
        pacman.die();
        assertTrue(pacman.isDead());
        pacman.move(0.0, 10.0);
        assertFalse(pacman.isDead());
    }
    
    @Test
    public void Pacman_respawns_on_pacman_spawn_point() {
        pacman.setTile(map.getMonsterSpawn());
        assertEquals(map.getMonsterSpawn(), pacman.getTile());
        pacman.die();
        pacman.respawn();
        assertEquals(map.getPacmanSpawn(), pacman.getTile());
    }
}