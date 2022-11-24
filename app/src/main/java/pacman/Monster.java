package pacman;

public class Monster extends Entity {
    public Monster(Tile startingTile) {
        super(startingTile);
    }

    @Override
    public void collideWith(Entity entity) {
        entity.meetMonster(this);
    }

    @Override
    public void meetMonster(Monster monster) {
    }

    @Override
    public void meetPacman(Pacman pacman) {
        if (pacman.isStrong()) {
            this.die();
        } else if(!this.isDead()) {
            pacman.die();
        }
    }
}
