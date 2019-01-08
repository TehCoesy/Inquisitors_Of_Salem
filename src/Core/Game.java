package Core;

import Controllers.Controller;
import IO.MyButton;
import Entities.*;
import Utility.Ballot;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;

public class Game {
    //GAMEPLAY
    private int day = 0;
    private String _currentAction;

    //GAMEPLAY PARAMETERS
    private static final int PLAYER_COUNT = 12;
    private static final int VILLAGER_COUNT = 9;
    private static final int ENEMIES_COUNT = 3;

    //UTILITY
    VillagerFactory _factory = new VillagerFactory();
    Ballot ballot;

    //RENDERING
    private ArrayList<MyButton> _buttons = new ArrayList<>();
    private Controller _mainController;

    //ENTITIES
    private ArrayList<Entity> _allEntities;
    private ArrayList<Entity> _villagers;
    private ArrayList<Entity> _enemies;
    private Investigator _invest;

    public void newGame() throws Exception {
        if (_mainController == null) {
            throw new Exception("newGame(): UI Controller not set.");
        }

        ballot = new Ballot(PLAYER_COUNT);

        _allEntities = new ArrayList<>();
        _villagers = new ArrayList<>();
        _enemies = new ArrayList<>();

        if (VILLAGER_COUNT + ENEMIES_COUNT != PLAYER_COUNT) {
            throw new Exception("Internal Setting: PLAYER_COUNT not valid (different than VILLAGER_COUNT + ENEMIES_COUNT)");
        }

        for (int i = 0; i < ENEMIES_COUNT; i++) {
            Enemy enemy = new Enemy();
            enemy.setRole("Enemy");
            enemy.setID(ballot.getBallot());
            _enemies.add(enemy);
        }

        for (int i = 0; i < VILLAGER_COUNT; i++) {
            Entity entity = new Entity();
            entity.setID(ballot.getBallot());
            _villagers.add(entity);
        }

        _allEntities.addAll(_enemies);
        _allEntities.addAll(_villagers);

        ballot = null;
    }

    public void deconstructGame() {
        _allEntities = null;
        _villagers = null;
        _enemies = null;
        _invest = null;
    }

    public void nightTime() {

    }

    public void listVillagers() {
        for (Entity entity : _allEntities) {
            System.out.println(entity.toString());
        }
    }

    public void setController(Controller controller) {
        this._mainController = controller;
    }

    public void setUI() {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            MyButton button = _factory.createPlayer(i);
            _buttons.add(button);
            button.setOnAction(e -> {
                System.out.println("Villager " + button.getID());
                _mainController.addEvent("Clicked on Villager " + button.getID());
            });
            button.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {

                }
            });
            _mainController.addButton(button);
        }
    }

    public void cancelAction() {

    }

    public void registerAction(String action) {
        this._currentAction = action;
    }

    public void onAction(int _id) {

    }
}
