import Core.Game;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    Game _mainGame = new Game();

    @FXML
    private Button exitButton;
    @FXML
    private Button optionButton;

    public void init() {
        try {
            _mainGame.newGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        _mainGame.listVillagers();
    }
}
