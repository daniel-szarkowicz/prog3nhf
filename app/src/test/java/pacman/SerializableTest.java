package pacman;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Test;

public class SerializableTest {
    @Test
    public void Map_is_serializable() throws Throwable {
        var out = new ByteArrayOutputStream();
        var oos = new ObjectOutputStream(out);
        var map = new TileMap(20);
        oos.writeObject(map);
        var in = new ByteArrayInputStream(out.toByteArray());
        var ois = new ObjectInputStream(in);
        var remap = (TileMap) ois.readObject();
    }

    @Test
    public void Game_is_serializable() throws Throwable {
        var out = new ByteArrayOutputStream();
        var oos = new ObjectOutputStream(out);
        var game = new Game();
        oos.writeObject(game);
        var in = new ByteArrayInputStream(out.toByteArray());
        var ois = new ObjectInputStream(in);
        var regame = (Game) ois.readObject();
    }
}
