package pacman.controller;

import pacman.Direction;
import pacman.model.Entity;

/**
 * The controller for the Pinky monster
 */
public class PinkyController extends EntityController {
    public PinkyController(Entity model) {
        super(model);
    }

    /**
     * Pinky always chases Pac-Man on the sortest path
     */
    @Override
    public void control(double delta, Entity model) {
        var pathToPacman = model.getTile().getPathToClosestPacman();
        if (pathToPacman != null) {
            model.setNextDirection(pathToPacman.direction);
        } else {
            if ((!model.isMoving() && MonsterController.RANDOM.nextFloat() < delta * 10)
                    || MonsterController.RANDOM.nextFloat() < delta) {
                var nextDirection = Direction.values()[MonsterController.RANDOM.nextInt(4)];
                model.setNextDirection(nextDirection);
            }
        }
        model.move(2.0, delta);
    }
}