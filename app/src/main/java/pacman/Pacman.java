package pacman;

public class Pacman extends Entity {
    public Pacman(Tile startingTile) {
        super(startingTile);
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
}
