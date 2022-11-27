package pacman.controller;

import pacman.Direction;
import pacman.model.Entity;

public class PinkyController extends EntityController {
    public PinkyController(Entity model) {
        super(model);
    }

    @Override
    public void control(double delta, Entity model) {
        var pathToPacman = model.getTile().getPathToClosestPacman();
        if (pathToPacman != null) {
            model.setNextDirection(pathToPacman.direction);
        }
        else {
            if ((!model.isMoving() && MonsterController.RANDOM.nextFloat() < delta*10) || MonsterController.RANDOM.nextFloat() < delta) {
                var nextDirection = Direction.values()[MonsterController.RANDOM.nextInt(4)];
                model.setNextDirection(nextDirection);
            }
        }
        model.move(2.0, delta);
    }
}