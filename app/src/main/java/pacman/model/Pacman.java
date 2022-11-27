package pacman.model;

public class Pacman extends Entity {
    private double strong_timer;

    public Pacman(Tile startingTile) {
        super(startingTile);
        startingTile.timeStampPacman();
        this.strong_timer = 0.0;
    }

    @Override
    public void collideWith(Entity entity) {
        entity.meetPacman(this);
    }

    @Override
    public void meetMonster(Monster monster) {
        if (this.isStrong()) {
            monster.die();
        } else if (!monster.isDead()) {
            this.die();
        }
    }

    @Override
    public void meetPacman(Pacman pacman) {
    }

    @Override
    public void setTile(Tile tile) {
        super.setTile(tile);
        tile.timeStampPacman();
    }

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

    public boolean isStrong() {
        return this.strong_timer > 0.0;
    }

    public void setStrong() {
        this.strong_timer = 5.0;
    }

    @Override
    public void move(double speed, double delta) {
        if (this.isStrong()) {
            speed *= 1.5;
        }
        super.move(speed, delta);
        this.strong_timer -= delta;
    }
}
