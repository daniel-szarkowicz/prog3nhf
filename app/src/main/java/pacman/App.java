/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pacman;

public class App {
    public static void main(String[] args) throws Throwable {
        GameWindow window = new GameWindow();
        Game model = new Game();
        GameView view = new GameView(model, window);
        GameController controller = new GameController(model, window);
        window.setVisible(true);
        while(true) {
            window.repaint();
            Thread.sleep(1000/60);
        }
    }
}
