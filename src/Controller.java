import Core.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class Controller {
    @FXML
    private Button exitButton;
    @FXML
    private Button optionButton;
    @FXML
    private Pane gameViewPane;

    private VillagerFactory _factory = new VillagerFactory();
    Game _mainGame = new Game();

    public void init() {
        try {
            _mainGame.newGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //_mainGame.listVillagers();

        for (int i = 0; i < 12; i++) {
            gameViewPane.getChildren().add(_factory.createPlayer(i));
        }
    }
}
