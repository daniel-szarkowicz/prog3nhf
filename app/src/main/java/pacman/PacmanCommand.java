package pacman;

import pacman.model.Entity;

/**
 * Commands for controlling the Pac-Man with different keybinds
*/
public interface PacmanCommand {
    public static final PacmanCommand TURN_UP = model -> model.setNextDirection(Direction.UP);
    public static final PacmanCommand TURN_DOWN = model -> model.setNextDirection(Direction.DOWN);
    public static final PacmanCommand TURN_LEFT = model -> model.setNextDirection(Direction.LEFT);
    public static final PacmanCommand TURN_RIGHT = model -> model.setNextDirection(Direction.RIGHT);
    public static final PacmanCommand NOTHING = model -> {};

    public void control(Entity model);
}