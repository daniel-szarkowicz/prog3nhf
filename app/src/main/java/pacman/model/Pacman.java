package pacman.model;

/**
 * The model of a Pac-Man
 */
public class Pacman extends Entity {
    private double strong_timer;

    public Pacman(Tile startingTile) {
        super(startingTile);
        startingTile.timeStampPacman();
        this.strong_timer = 0.0;
    }

    /**
     * Meet the entities it collided with as Pac-Man
     */
    @Override
    public void collideWith(Entity entity) {
        entity.meetPacman(this);
    }

    /**
     * The Pac-Man meets a monster
     * <p>
     * If the Pac-Man is strong the monster dies, otherwise the Pac-Man dies
     * 
     * @param monster the monster the Pac-Man met
     */
    @Override
    public void meetMonster(Monster monster) {
        if (this.isStrong()) {
            monster.die();
        } else if (!monster.isDead()) {
            this.die();
        }
    }

    /**
     * Does nothing when meeting another Pac-Man
     */
    @Override
    public void meetPacman(Pacman pacman) {
    }

    /**
     * When stepping on a tile sets its pacman timer. This is needed for
     * the {@link pacman.controller.BlinkyController}
     */
    @Override
    public void setTile(Tile tile) {
        super.setTile(tile);
        tile.timeStampPacman();
    }

    /**
     * When a Pac-Man dies we have to reset the pacman timers, so the
     * {@link pacman.controller.BlinkyController} doesn't get stuck
     */
    @Override
    public void die() {
        if (!this.isDead()) {
            super.die();
            this.getTile().resetPacmanTimes();
        }
    }

    @Override
    public void respawn() {
        super.respawn();
        this.getTile().timeStampPacman();
    }

    /**
     * @return whether the Pac-Man is strong
     */
    public boolean isStrong() {
        return this.strong_timer > 0.0;
    }

    /**
     * Sets the Pac-Man to strong
     */
    public void setStrong() {
        this.strong_timer = 5.0;
    }

    /**
     * If the Pac-Man is strong make it faster and decrease its strength time.
     */
    @Override
    public void move(double speed, double delta) {
        if (this.isStrong()) {
            speed *= 1.5;
        }
        super.move(speed, delta);
        this.strong_timer -= delta;
    }
}
