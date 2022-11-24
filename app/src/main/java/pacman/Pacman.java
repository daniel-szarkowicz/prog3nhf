package pacman;

public class Pacman extends Entity {
    public Pacman(Tile startingTile) {
        super(startingTile);
        startingTile.timeStampPacman();
    }

    @Override
    public void collideWith(Entity entity) {
        entity.meetPacman(this);
    }

    @Override
    public void meetMonster(Monster monster) {
        this.die();
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
        super.die();
        this.getTile().resetPacmanTimes();
    }
}
