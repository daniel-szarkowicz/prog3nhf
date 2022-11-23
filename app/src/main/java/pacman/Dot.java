package pacman;

public class Dot extends Entity {
    public Dot(Tile startingTile) {
        super(startingTile);
    }

    @Override
    public void collideWith(Entity entity) {
    }

    @Override
    public void meetMonster(Monster monster) {
    }

    @Override
    public void meetPacman(Pacman pacman) {
        this.die();
    }
}