package pacman;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static final long FRAME_TIME_TARGET = 1000/100;

    public static void main(String[] args) throws Throwable {
        var map = mapEditorMain();
        gameMain(map);
    }
    
    private static void gameMain(TileMap map) throws Throwable {
        Game model = new Game(map);
        GameView view = new GameView(model, setupColors());
        GameController controller = new GameController(model, setupKeymaps());

        GameWindow window = new GameWindow();
        window.add(view);
        window.addKeyListener(controller);
        window.setVisible(true);
        var start = System.currentTimeMillis();
        var end = System.currentTimeMillis();
        while (window.isVisible()) {
            start = System.currentTimeMillis();
            controller.tick((start - end)/1000.0);
            window.repaint();
            end = System.currentTimeMillis();
            // if (end - start < FRAME_TIME_TARGET) {
            //     Thread.sleep(FRAME_TIME_TARGET - (end - start));
            // }
            Thread.sleep(FRAME_TIME_TARGET);
        }
        window.dispose();
    }
    
    private static TileMap mapEditorMain() throws Throwable {
        MapEditor model = new MapEditor();
        MapEditorView view = new MapEditorView(model);
        MapEditorController controller = new MapEditorController(model);
        
        GameWindow window = new GameWindow();
        window.add(view);
        window.addKeyListener(controller);
        window.addMouseListener(controller);
        window.setVisible(true);
        var start = System.currentTimeMillis();
        var end = System.currentTimeMillis();
        while (window.isVisible()) {
            start = System.currentTimeMillis();
            // controller.tick((start - end)/1000.0);
            window.repaint();
            end = System.currentTimeMillis();
            if (end - start < FRAME_TIME_TARGET) {
                Thread.sleep(FRAME_TIME_TARGET - (end - start));
            }
            // Thread.sleep(FRAME_TIME_TARGET);
        }
        window.dispose();
        return model.map;
    }
    
    private static List<Color> setupColors() {
        var colors = new ArrayList<Color>();
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.PINK);
        
        return colors;
    }
    
    private static List<Map<Integer, PacmanCommand>> setupKeymaps() {
        var keymaps = new ArrayList<Map<Integer, PacmanCommand>>();
        var keymap1 = new HashMap<Integer, PacmanCommand>();
        keymap1.put(KeyEvent.VK_W, PacmanCommand.TURN_UP);
        keymap1.put(KeyEvent.VK_S, PacmanCommand.TURN_DOWN);
        keymap1.put(KeyEvent.VK_A, PacmanCommand.TURN_LEFT);
        keymap1.put(KeyEvent.VK_D, PacmanCommand.TURN_RIGHT);
        var keymap2 = new HashMap<Integer, PacmanCommand>();
        keymap2.put(KeyEvent.VK_UP, PacmanCommand.TURN_UP);
        keymap2.put(KeyEvent.VK_DOWN, PacmanCommand.TURN_DOWN);
        keymap2.put(KeyEvent.VK_LEFT, PacmanCommand.TURN_LEFT);
        keymap2.put(KeyEvent.VK_RIGHT, PacmanCommand.TURN_RIGHT);
        var keymap3 = new HashMap<Integer, PacmanCommand>();
        keymap3.put(KeyEvent.VK_K, PacmanCommand.TURN_UP);
        keymap3.put(KeyEvent.VK_J, PacmanCommand.TURN_DOWN);
        keymap3.put(KeyEvent.VK_H, PacmanCommand.TURN_LEFT);
        keymap3.put(KeyEvent.VK_L, PacmanCommand.TURN_RIGHT);
        
        keymaps.add(keymap1);
        keymaps.add(keymap2);
        keymaps.add(keymap3);

        return keymaps;
    }
}
