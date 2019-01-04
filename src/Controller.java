import Core.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class Controller {
    GameCanvas _gameView = new GameCanvas();
    Game _mainGame = new Game(_gameView);

    @FXML
    private Button exitButton;
    @FXML
    private Button optionButton;
    @FXML
    private Pane gameViewPane;

    public void init() {
        try {
            _mainGame.newGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        _mainGame.listVillagers();

        _gameView.setWidth(gameViewPane.getWidth());
        _gameView.setHeight(gameViewPane.getHeight());

        gameViewPane.getChildren().add(_gameView);
        _gameView.render();
    }
}
