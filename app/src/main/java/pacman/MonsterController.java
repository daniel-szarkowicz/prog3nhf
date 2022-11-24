package pacman;

import java.util.Random;

public class MonsterController extends EntityController {
    public static final Random RANDOM;
    
    public MonsterController(Entity model) {
        super(model);
    }
    
    public void control(double delta, Entity model) {
        if ((!model.isMoving() && RANDOM.nextFloat() < delta*10) || RANDOM.nextFloat() < delta) {
            var nextDirection = Direction.values()[RANDOM.nextInt(4)];
            model.setNextDirection(nextDirection);
        }
        model.move(3.0, delta);
    }
    
    static {
        RANDOM = new Random();
    }
}