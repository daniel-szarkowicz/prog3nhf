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

import pacman.controller.GameController;
import pacman.controller.MapEditorController;
import pacman.model.Game;
import pacman.model.MapEditor;
import pacman.model.TileMap;
import pacman.view.GameView;
import pacman.view.MapEditorView;

public class App {
    public static final long FRAME_TIME_TARGET = 1000 / 100;

    public static void main(String[] args) throws Throwable {
        GameWindow window = new GameWindow();
        window.setVisible(true);
        while (true) {
            var action = mainMenu(window);
            switch (action) {
                case PLAY:
                    playGame(window);
                    break;
                case LOAD:
                    loadGame(window);
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

    private static void playGame(JFrame window) throws Throwable {
        var map = TileMap.openFileChoose(window);
        if (map == null) {
            JOptionPane.showMessageDialog(window, "Failed to load map");
            return;
        }
        Integer pacmanCount = null;
        while (pacmanCount == null || pacmanCount < 1 || pacmanCount > 4) {
            var p = JOptionPane.showInputDialog("Pacman count:");
            if (p != null) {
                try {
                    pacmanCount = Integer.parseInt(p);
                } catch (NumberFormatException nemÉrdekelKivétel) {
                    pacmanCount = null;
                }
            }
        }
        Game model = new Game(map, pacmanCount);
        gameMain(window, model);
    }

    private static void loadGame(JFrame window) throws Throwable {
        var game = Game.openFileChoose(window);
        if (game == null) {
            JOptionPane.showMessageDialog(window, "Failed to load game");
            return;
        }
        gameMain(window, game);
    }

    private static void gameMain(JFrame window, Game model) throws Throwable {
        GameView view = new GameView(model, setupColors());
        GameController controller = new GameController(model, setupKeymaps());

        window.add(view);
        var menubar = new JMenuBar();
        var fileMenu = new JMenu("File");
        var saveMenuItem = new JMenuItem("Save");
        var exitMenuItem = new JMenuItem("Exit");
        saveMenuItem.addActionListener(e -> {
            Game.saveFileChoose(model, window);
        });
        exitMenuItem.addActionListener(e -> {
            model.active = false;
        });
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        menubar.add(fileMenu);
        window.setJMenuBar(menubar);
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
        window.setJMenuBar(null);
    }

    private static void mapEditorMain(JFrame window) throws InterruptedException {
        MapEditor model = new MapEditor();
        MapEditorView view = new MapEditorView(model);
        MapEditorController controller = new MapEditorController(model);

        window.add(view);
        var menubar = new JMenuBar();
        var fileMenu = new JMenu("File");
        var openMenuItem = new JMenuItem("Open");
        var saveMenuItem = new JMenuItem("Save");
        var newMenuItem = new JMenuItem("New");
        var exitMenuItem = new JMenuItem("Exit");
        openMenuItem.addActionListener(e -> {
            var map = TileMap.openFileChoose(window);
            if (map != null) {
                model.map = map;
                view.renewMap();
            }
        });
        saveMenuItem.addActionListener(e -> {
            TileMap.saveFileChoose(model.map, window);
        });
        newMenuItem.addActionListener(e -> {
            Integer width = null;
            while (width == null || width < 5 || width > 50) {
                var w = JOptionPane.showInputDialog("Map width:");
                if (w != null) {
                    try {
                        width = Integer.parseInt(w);
                    } catch (NumberFormatException nemÉrdekelKivétel) {
                        width = null;
                    }
                }
            }
            Integer height = null;
            while (height == null || height < 5 || height > 50) {
                var h = JOptionPane.showInputDialog("Map height:");
                if (h != null) {
                    try {
                        height = Integer.parseInt(h);
                    } catch (NumberFormatException nemÉrdekelKivétel) {
                        height = null;
                    }
                }
            }
            model.map = new TileMap(width, height);
            view.renewMap();
        });
        exitMenuItem.addActionListener(e -> model.active = false);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(newMenuItem);
        fileMenu.add(exitMenuItem);
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
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);

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
        keymap3.put(KeyEvent.VK_I, PacmanCommand.TURN_UP);
        keymap3.put(KeyEvent.VK_K, PacmanCommand.TURN_DOWN);
        keymap3.put(KeyEvent.VK_J, PacmanCommand.TURN_LEFT);
        keymap3.put(KeyEvent.VK_L, PacmanCommand.TURN_RIGHT);
        var keymap4 = new HashMap<Integer, PacmanCommand>();
        keymap4.put(KeyEvent.VK_T, PacmanCommand.TURN_UP);
        keymap4.put(KeyEvent.VK_G, PacmanCommand.TURN_DOWN);
        keymap4.put(KeyEvent.VK_F, PacmanCommand.TURN_LEFT);
        keymap4.put(KeyEvent.VK_H, PacmanCommand.TURN_RIGHT);

        keymaps.add(keymap1);
        keymaps.add(keymap2);
        keymaps.add(keymap3);
        keymaps.add(keymap4);

        return keymaps;
    }
}
