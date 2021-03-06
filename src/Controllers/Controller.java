package Controllers;

import Core.*;
import IO.MyButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

//Controller.java class, handles displaying and modifying UI elements

public class Controller {
    @FXML
    private TextField currentToolTip;
    @FXML
    private TextField villagerCount;
    @FXML
    private TextField enemyCount;
    @FXML
    private Button optionButton;
    @FXML
    private Button clearMessage;
    @FXML
    private Pane gameViewPane;
    @FXML
    private Button nextTurn;
    @FXML
    private Button lynchAction;
    @FXML
    private Button investigatorAction;
    @FXML
    private Button healerAction;
    @FXML
    private ListView messageBoard;

    //GAMEPLAY
    Game _mainGame;
    ArrayList<String> _events = new ArrayList<>();

    public void init() {
        _mainGame = new Game(this);

        setControls();
        setMiscControls();

        try {
            _mainGame.newGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setControls() {
        clearMessage.setOnAction(e -> {
           clearEvent();
        });

        nextTurn.setOnAction(e -> {
            _mainGame.nextTurn();
        });

        lynchAction.setOnAction(e -> {
            _mainGame.registerAction("LYNCH");
        });

        investigatorAction.setOnAction(e -> {
            _mainGame.registerAction("INVESTIGATOR");
        });

        healerAction.setOnAction(e -> {
            _mainGame.registerAction("HEALER");
        });
    }

    private void setMiscControls() {
        clearMessage.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("Cancel");
            }
        });

        nextTurn.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("Cancel");
            }
        });

        investigatorAction.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("Cancel");
            }
        });

        healerAction.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("Cancel");
            }
        });

        gameViewPane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.print("Cancel");
            }
        });

        lynchAction.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("Cancel");
            }
        });
    }

    public void addButton(MyButton button) {
        gameViewPane.getChildren().add(button);
    }

    public void clearButton() {
        gameViewPane.getChildren().removeAll();
    }

    public void clearEvent() {
        _events.clear();
        messageBoard.setItems(null);
        messageBoard.refresh();
    }

    public void addEvent(String text) {
        _events.add(text);
        ObservableList<String> _items = FXCollections.observableArrayList(_events);
        messageBoard.setItems(_items);
        messageBoard.refresh();
    }

    public void setPlayerCount(int villager, int enemy) {
        villagerCount.setText("Villagers: " + villager);
        enemyCount.setText("Enemies: " + enemy);
    }

    public void pingToolTip(String text) {
        currentToolTip.setText(text);
    }
}
