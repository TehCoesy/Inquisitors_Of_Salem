package Core;

import Controllers.Controller;
import IO.MyButton;
import Entities.*;
import Utility.Ballot;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;

public class Game {
    //GAMEPLAY
    private boolean _lynchActed = false;
    private int day = 0;
    private String _currentAction = "";

    //GAMEPLAY PARAMETERS
    private static final int PLAYER_COUNT = 12;
    private static final int VILLAGER_COUNT = 7;
    private static final int ENEMIES_COUNT = 3;
    private static final int INVESTIGATOR_COUNT = 1;
    private static final int HEALER_COUNT = 1;

    //UTILITY
    VillagerFactory _factory = new VillagerFactory();
    Ballot ballot;

    //RENDERING
    private ArrayList<MyButton> _buttons = new ArrayList<>();
    private Controller _mainController;

    //ENTITIES
    private EntityContainer _container = new EntityContainer();
    private WolvesTeam _wolvesTeam = new WolvesTeam(_container);

    //ACTION

    public void newGame() throws Exception {
        if (_mainController == null) {
            throw new Exception("newGame(): UI Controller not set.");
        }

        ballot = new Ballot(PLAYER_COUNT);

        _container.newLists();

        if (VILLAGER_COUNT + ENEMIES_COUNT + INVESTIGATOR_COUNT + HEALER_COUNT != PLAYER_COUNT) {
            throw new Exception("Internal Setting: PLAYER_COUNT not valid (different than VILLAGER_COUNT + ENEMIES_COUNT)");
        }

        for (int i = 0; i < ENEMIES_COUNT; i++) {
            Entity enemy = new Villager();
            enemy._trueRole = "Enemy";
            enemy.setID(ballot.getBallot());
            _container._enemies.add(enemy);
        }

        for (int i = 0; i < VILLAGER_COUNT; i++) {
            Entity entity = new Villager();
            entity.setID(ballot.getBallot());
            _container._villagers.add(entity);
        }

        for (int i = 0; i < INVESTIGATOR_COUNT; i++) {
            Entity investigator = new Investigator();
            investigator.setID(ballot.getBallot());
            investigator._trueRole = "Investigator";
            _container._investigator.add(investigator);
        }

        for (int i = 0; i < HEALER_COUNT; i++) {
            Entity healer = new Healer();
            healer.setID(ballot.getBallot());
            healer._trueRole = "Healer";
            _container._healer.add(healer);
        }

        _container.addAll();

        ballot = null;
    }

    public void nextTurn() {
        System.out.println("NextTurn");

        _wolvesTeam.onAction();

        String _investEvent = _container._investigator.get(0).onAction();

        if (_investEvent != null) {
            _mainController.addEvent(_investEvent);
        }

        String _healerEvent = _container._healer.get(0).onAction();

        if (_healerEvent != null) {
            _mainController.addEvent(_healerEvent);
        }

        for (Entity _entity : _container._allEntities) {
            _entity.applyEffect();
        }

        for (Entity _entity : _container._allEntities) {
            if (_entity._dead) {
                System.out.println(_entity.getID() + " is dead.");
                _mainController.addEvent(_entity.getID() + " is dead.");
            }
        }

        day++;
        _lynchActed = false;
    }

    public void setController(Controller controller) {
        this._mainController = controller;
    }

    public void setUI() {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            MyButton button = _factory.createPlayer(i);
            _buttons.add(button);
            button.setOnAction(e -> {
                if (!this._currentAction.isEmpty()) {
                    _mainController.addEvent(this._currentAction + " ON Villager " + button.getID());
                    onAction(button.getID());
                    cancelAction();
                } else {
                    getWill(button.getID());
                }
            });
            button.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    cancelAction();
                }
            });
            _mainController.addButton(button);
        }
    }

    public void cancelAction() {
        this._currentAction = "";
    }

    public void registerAction(String action) {
        this._currentAction = action;
        _mainController.pingToolTip(this._currentAction + " Action(s) available.");
    }

    public void onAction(int _id) {
        if (this._currentAction == "INVESTIGATOR") {
            _container._investigator.get(0).regigsterAction(_container._allEntities.get(_id));
        } else if (this._currentAction == "HEALER") {
            _container._healer.get(0).regigsterAction(_container._allEntities.get(_id));
        } else if (this._currentAction == "LYNCH") {
            if (!_lynchActed) {
                _container._allEntities.get(_id)._dead = true;
                _lynchActed = true;
            } else {
                _mainController.pingToolTip("You already voted 1 this turn.");
            }
        }
    }

    private void getWill(int ID) {
        System.out.println("Villager " + ID + " 's Will");
    }
}
