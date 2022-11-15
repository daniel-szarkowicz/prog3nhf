package pacman;

public class Pacman {
    private Direction direction;
    private Direction nextDirection;
    
    public Pacman() {
        this.direction = Direction.UP;
        this.nextDirection = Direction.UP;
    }
    
    public void setNextDirection(Direction d) {
        this.nextDirection = d;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public Direction getNextDirection() {
        return this.nextDirection;
    }
}