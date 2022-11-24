package pacman;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class App {
    public static final long FRAME_TIME_TARGET = 1000 / 100;

    public static void main(String[] args) throws Throwable {
        GameWindow window = new GameWindow();
        window.setVisible(true);
        while (true) {
            var action = mainMenu(window);
            switch (action) {
                case PLAY:
                    gameMain(window);
                    break;
                case EDITOR:
                    mapEditorMain(window);
                    break;
                case EXIT:
                    window.dispose();
                    System.exit(0);
                    break;
            }
        }
    }

    private static MenuAction mainMenu(JFrame window) throws InterruptedException {
        var panel = new MainMenu();
        window.add(panel);
        window.setVisible(true);
        while (panel.active) {
            Thread.sleep(1);
        }
        window.remove(panel);
        return panel.action;
    }

    private static void gameMain(JFrame window) throws Throwable {
        var map = TileMap.openFileChoose(window);
        if (map == null) {
            map = new TileMap(20);
        }
        Game model = new Game(map);
        GameView view = new GameView(model, setupColors());
        GameController controller = new GameController(model, setupKeymaps());

        window.add(view);
        view.addKeyListener(controller);
        var start = System.currentTimeMillis();
        var end = System.currentTimeMillis();
        window.setVisible(true);
        view.requestFocusInWindow();
        while (model.active) {
            start = System.currentTimeMillis();
            controller.tick((start - end) / 1000.0);
            window.repaint();
            end = System.currentTimeMillis();
            if (end - start < FRAME_TIME_TARGET) {
                Thread.sleep(FRAME_TIME_TARGET - (end - start));
            }
            // Thread.sleep(FRAME_TIME_TARGET);
        }
        window.remove(view);
    }

    private static void mapEditorMain(JFrame window) throws Throwable {
        MapEditor model = new MapEditor();
        MapEditorView view = new MapEditorView(model);
        MapEditorController controller = new MapEditorController(model);

        window.add(view);
        var menubar = new JMenuBar();
        var fileMenu = new JMenu("File");
        var openMenuItem = new JMenuItem("Open");
        var saveMenuItem = new JMenuItem("Save");
        var newMenuItem = new JMenuItem("New");
        openMenuItem.addActionListener(e -> {
            var map = TileMap.openFileChoose(window);
            if (map != null) {
                model.map.from(map);
            }
        });
        saveMenuItem.addActionListener(e -> {
            TileMap.saveFileChoose(model.map, window);
        });
        newMenuItem.addActionListener(e -> {
            Integer width = null;
            while (width == null) {
                width = Integer.parseInt(JOptionPane.showInputDialog("Map width:"));
            }
            Integer height = null;
            while (height == null) {
                height = Integer.parseInt(JOptionPane.showInputDialog("Map height:"));
            }
            model.map.from(new TileMap(width, height));
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(newMenuItem);
        menubar.add(fileMenu);
        var editMenu = new JMenu("Edit");
        var wallMenuItem = new JMenuItem("Wall");
        var pacmanSpawnMenuItem = new JMenuItem("Pacman Spawn");
        var monsterSpawnMenuItem = new JMenuItem("Monster Spawn");
        wallMenuItem.addActionListener(e -> controller.mode = MapEditorMode.WALL);
        pacmanSpawnMenuItem.addActionListener(e -> controller.mode = MapEditorMode.PACMAN_SPAWN);
        monsterSpawnMenuItem.addActionListener(e -> controller.mode = MapEditorMode.MONSTER_SPAWN);
        editMenu.add(wallMenuItem);
        editMenu.add(pacmanSpawnMenuItem);
        editMenu.add(monsterSpawnMenuItem);
        menubar.add(editMenu);
        window.setJMenuBar(menubar);
        view.addKeyListener(controller);
        view.addMouseListener(controller);
        view.requestFocusInWindow();
        var start = System.currentTimeMillis();
        var end = System.currentTimeMillis();
        window.setVisible(true);
        model.active = true;
        while (model.active) {
            start = System.currentTimeMillis();
            // controller.tick((start - end)/1000.0);
            window.repaint();
            end = System.currentTimeMillis();
            if (end - start < FRAME_TIME_TARGET) {
                Thread.sleep(FRAME_TIME_TARGET - (end - start));
            }
            // Thread.sleep(FRAME_TIME_TARGET);
        }
        window.remove(view);
        window.setJMenuBar(null);
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
