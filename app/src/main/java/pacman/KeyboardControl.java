package pacman;

public enum KeyboardControl {
    TURN_UP(model -> model.setNextDirection(Direction.UP)),
    TURN_DOWN(model -> model.setNextDirection(Direction.DOWN)),
    TURN_LEFT(model -> model.setNextDirection(Direction.LEFT)),
    TURN_RIGHT(model -> model.setNextDirection(Direction.RIGHT)),
    NOTHING(model -> {});
    
    public PacmanCommand command;
    
    private KeyboardControl(PacmanCommand command) {
        this.command = command;
    }
}