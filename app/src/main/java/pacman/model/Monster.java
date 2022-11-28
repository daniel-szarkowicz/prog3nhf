package pacman.model;

/**
 * The model of the monsters
 */
public class Monster extends Entity {
    public Monster(Tile startingTile) {
        super(startingTile);
    }

    /**
     * Meets the entities it collided with as monsters
     */
    @Override
    public void collideWith(Entity entity) {
        entity.meetMonster(this);
    }

    /**
     * Does nothing when meeting another monster
     */
    @Override
    public void meetMonster(Monster monster) {
    }

    /**
     * The Monster meets a Pac-Man
     * <p>
     * If the Pac-Man is strong the Monster dies, otherwise the Pac-Man dies
     * 
     * @param pacman the pacman the Monster met
     */
    @Override
    public void meetPacman(Pacman pacman) {
        if (pacman.isStrong()) {
            this.die();
        } else if (!this.isDead()) {
            pacman.die();
        }
    }
}
