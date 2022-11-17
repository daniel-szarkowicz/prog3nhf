package pacman;

import java.util.Random;

public class MonsterController {
    private static final Random RANDOM;
    private final Entity model;
    
    public MonsterController(Entity model) {
        this.model = model;
    }
    
    public void tick(double delta) {
        if (RANDOM.nextFloat() < 0.01) {
            var nextDirection = Direction.values()[RANDOM.nextInt(4)];
            this.model.setNextDirection(nextDirection);
        }
        this.model.move(delta * 5.0);
    }
    
    static {
        RANDOM = new Random();
    }
}