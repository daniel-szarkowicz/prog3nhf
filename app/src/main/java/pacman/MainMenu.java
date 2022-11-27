package pacman;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
    public boolean active;
    public MenuAction action;

    public MainMenu() {
        var playButton = new JButton("Play");
        var loadButton = new JButton("Load");
        var editorButton = new JButton("Edit");
        var exitButton = new JButton("Exit");
        playButton.addActionListener(e -> {
            action = MenuAction.PLAY;
            active = false;
        });
        loadButton.addActionListener(e -> {
            action = MenuAction.LOAD;
            active = false;
        });
        editorButton.addActionListener(e -> {
            action = MenuAction.EDITOR;
            active = false;
        });
        exitButton.addActionListener(e -> {
            action = MenuAction.EXIT;
            active = false;
        });
        this.add(playButton);
        this.add(loadButton);
        this.add(editorButton);
        this.add(exitButton);
        this.active = true;
        this.action = MenuAction.EXIT;
    }
}