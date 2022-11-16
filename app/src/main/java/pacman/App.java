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
        Game model = new Game();
        GameView view = new GameView(model, setupColors());
        GameController controller = new GameController(model, setupKeymaps());

        GameWindow window = new GameWindow();
        window.add(view);
        window.addKeyListener(controller);
        window.setVisible(true);
        var start = System.currentTimeMillis();
        var end = System.currentTimeMillis();
        while (true) {
            start = System.currentTimeMillis();
            controller.tick((start - end)/1000.0);
            window.repaint();
            end = System.currentTimeMillis();
            if (end - start < FRAME_TIME_TARGET) {
                Thread.sleep(FRAME_TIME_TARGET - (end - start));
            }
            // Thread.sleep(FRAME_TIME_TARGET);
        }
    }
    
    private static List<Color> setupColors() {
        var colors = new ArrayList<Color>();
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.PINK);
        
        return colors;
    }
    
    private static List<Map<Integer, KeyboardControl>> setupKeymaps() {
        var keymaps = new ArrayList<Map<Integer, KeyboardControl>>();
        var keymap1 = new HashMap<Integer, KeyboardControl>();
        keymap1.put(KeyEvent.VK_W, KeyboardControl.TURN_UP);
        keymap1.put(KeyEvent.VK_S, KeyboardControl.TURN_DOWN);
        keymap1.put(KeyEvent.VK_A, KeyboardControl.TURN_LEFT);
        keymap1.put(KeyEvent.VK_D, KeyboardControl.TURN_RIGHT);
        var keymap2 = new HashMap<Integer, KeyboardControl>();
        keymap2.put(KeyEvent.VK_UP, KeyboardControl.TURN_UP);
        keymap2.put(KeyEvent.VK_DOWN, KeyboardControl.TURN_DOWN);
        keymap2.put(KeyEvent.VK_LEFT, KeyboardControl.TURN_LEFT);
        keymap2.put(KeyEvent.VK_RIGHT, KeyboardControl.TURN_RIGHT);
        var keymap3 = new HashMap<Integer, KeyboardControl>();
        keymap3.put(KeyEvent.VK_K, KeyboardControl.TURN_UP);
        keymap3.put(KeyEvent.VK_J, KeyboardControl.TURN_DOWN);
        keymap3.put(KeyEvent.VK_H, KeyboardControl.TURN_LEFT);
        keymap3.put(KeyEvent.VK_L, KeyboardControl.TURN_RIGHT);
        
        keymaps.add(keymap1);
        keymaps.add(keymap2);
        keymaps.add(keymap3);

        return keymaps;
    }
}
