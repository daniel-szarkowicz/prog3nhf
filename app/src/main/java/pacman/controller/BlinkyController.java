package pacman.controller;

import pacman.Direction;
import pacman.model.Entity;

public class BlinkyController extends EntityController {
    public BlinkyController(Entity model) {
        super(model);
    }

    @Override
    public void control(double delta, Entity model) {
        var nextDirection = model.getTile().getPacmanPathDirection();
        if (nextDirection != null) {
            model.setNextDirection(nextDirection);
            model.move(2.0, delta);
        } else {
            if ((!model.isMoving() && MonsterController.RANDOM.nextFloat() < delta * 10)
                    || MonsterController.RANDOM.nextFloat() < delta) {
                nextDirection = Direction.values()[MonsterController.RANDOM.nextInt(4)];
                model.setNextDirection(nextDirection);
            }
            model.move(3.0, delta);
        }
    }
}