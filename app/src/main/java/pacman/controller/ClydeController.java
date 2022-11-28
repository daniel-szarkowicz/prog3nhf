package pacman.controller;

import pacman.Direction;
import pacman.model.Entity;

/**
The controller for the Clyde monster
*/
public class ClydeController extends EntityController {
    public ClydeController(Entity model) {
        super(model);
    }

    /**
    Clyde move randomly when Pac-Man is far, but starts chasing when it gets
    close
    */
    @Override
    public void control(double delta, Entity model) {
        var pathToPacman = model.getTile().getPathToClosestPacman();
        if (pathToPacman != null && pathToPacman.length <= 7) {
            model.setNextDirection(pathToPacman.direction);
            model.move(2.0, delta);
        }
        else {
            if ((!model.isMoving() && MonsterController.RANDOM.nextFloat() < delta*10) || MonsterController.RANDOM.nextFloat() < delta) {
                var nextDirection = Direction.values()[MonsterController.RANDOM.nextInt(4)];
                model.setNextDirection(nextDirection);
            }
            model.move(3.0, delta);
        }
    }
}