package Controllers;

import Core.Game;
import Core.MyButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Observable;

//Controller.java class, handles displaying and modifying UI elements

public class Controller {
    @FXML
    private Button exitButton;
    @FXML
    private Button optionButton;
    @FXML
    private Pane gameViewPane;
    @FXML
    private ListView messageBoard;

    //GAMEPLAY
    Game _mainGame = new Game();
    ArrayList<String> _events = new ArrayList<>();

    public void init() {
        _mainGame.setController(this);

        try {
            _mainGame.newGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //_mainGame.listVillagers();
        _mainGame.setUI();
    }

    public void addButton(MyButton button) {
        gameViewPane.getChildren().add(button);
    }

    public void clearButton() {
        gameViewPane.getChildren().removeAll();
    }

    public void clearEvent() {

    }

    public void addEvent(String text) {
        _events.add(text);
        ObservableList<String> _items = FXCollections.observableArrayList(_events);
        messageBoard.setItems(_items);
        messageBoard.refresh();
    }
}
