package pacman.model;

/**
 * The model of a large dot
 */
public class LargeDot extends Entity {
    public LargeDot(Tile startingTile) {
        super(startingTile);
    }

    @Override
    public void collideWith(Entity entity) {
    }

    @Override
    public void meetMonster(Monster monster) {
    }

    /**
     * When eaten by a Pac-Man make the Pac-Man strong
     */
    @Override
    public void meetPacman(Pacman pacman) {
        if (!this.isDead()) {
            this.die();
            pacman.setStrong();
        }
    }
}