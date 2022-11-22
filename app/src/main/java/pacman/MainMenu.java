package pacman;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
    public boolean active;
    public MenuAction action;

    public MainMenu() {
        var playButton = new JButton("play");
        var editorButton = new JButton("edit");
        var exitButton = new JButton("exit");
        playButton.addActionListener(e -> {
            action = MenuAction.PLAY;
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
        this.add(editorButton);
        this.add(exitButton);
        this.active = true;
        this.action = MenuAction.EXIT;
    }
}