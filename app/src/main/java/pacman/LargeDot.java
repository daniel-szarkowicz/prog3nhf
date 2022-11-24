package pacman;

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

    @Override
    public void meetPacman(Pacman pacman) {
        if (!this.isDead()) {
            this.die();
            pacman.setStrong();
        }
    }
}