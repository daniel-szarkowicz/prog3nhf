package pacman.controller;

import java.util.Random;

import pacman.Direction;
import pacman.model.Entity;

/**
 * The controller for the Inky monster
 */
public class MonsterController extends EntityController {
    public static final Random RANDOM;

    public MonsterController(Entity model) {
        super(model);
    }

    /**
     * Inky moves randomly
     */
    public void control(double delta, Entity model) {
        if ((!model.isMoving() && RANDOM.nextFloat() < delta * 10) || RANDOM.nextFloat() < delta) {
            var nextDirection = Direction.values()[RANDOM.nextInt(4)];
            model.setNextDirection(nextDirection);
        }
        model.move(3.0, delta);
    }

    static {
        RANDOM = new Random();
    }
}