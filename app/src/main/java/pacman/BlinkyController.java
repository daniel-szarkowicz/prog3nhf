package pacman;

public class BlinkyController extends EntityController {
    public BlinkyController(Entity model) {
        super(model);
    }

    public void control(double delta, Entity model) {
        var nextDirection = model.getTile().getPacmanPathDirection();
        if (nextDirection != null) {
            model.setNextDirection(nextDirection);
            model.move(delta * 2.0);
        }
        else {
            if ((!model.isMoving() && MonsterController.RANDOM.nextFloat() < delta*10) || MonsterController.RANDOM.nextFloat() < delta) {
                nextDirection = Direction.values()[MonsterController.RANDOM.nextInt(4)];
                model.setNextDirection(nextDirection);
            }
            model.move(delta * 3.0);
        }
    }
}