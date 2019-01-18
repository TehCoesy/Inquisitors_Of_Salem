package Core;

import AI.WolvesTeam;
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
    private boolean _gameEnd = false;

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

    public Game(Controller controller) {
        this._mainController = controller;

        _container.newLists();

        setUI();
    }

    private void setUI() {
        for (int i = 1; i <= PLAYER_COUNT; i++) {
            MyButton button = _factory.createPlayer(i);
            _buttons.add(button);

            button._villager = new Villager();
            button.setOnAction(e -> {
                if (!this._currentAction.isEmpty()) {
                    if (button._villager._dead) {
                        _mainController.pingToolTip(button._villager.getID() + " is Dead.");
                    } else {
                        _mainController.addEvent(this._currentAction + " ON Villager " + button._villager.getID());
                        onAction(button._villager);
                        cancelAction();
                    }
                } else {
                    getWill(button._villager.getID());
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

    public void newGame() throws Exception {
        if (_mainController == null) {
            throw new Exception("newGame(): UI Controller not set.");
        }

        ballot = new Ballot(PLAYER_COUNT);

        if (VILLAGER_COUNT + ENEMIES_COUNT + INVESTIGATOR_COUNT + HEALER_COUNT != PLAYER_COUNT) {
            throw new Exception("Internal Setting: PLAYER_COUNT not valid (different than VILLAGER_COUNT + ENEMIES_COUNT)");
        }

        for (int i = 0; i < ENEMIES_COUNT; i++) {
            Entity enemy = new Villager();
            enemy._trueRole = "Enemy";
            enemy.setID(ballot.getBallot());
            _container._enemies.add(enemy);
            _buttons.get(enemy.getID() - 1)._villager = enemy;
        }

        for (int i = 0; i < VILLAGER_COUNT; i++) {
            Entity entity = new Villager();
            entity.setID(ballot.getBallot());
            _container._villagers.add(entity);
            _buttons.get(entity.getID() - 1)._villager = entity;
        }

        for (int i = 0; i < INVESTIGATOR_COUNT; i++) {
            Entity investigator = new Investigator();
            investigator.setID(ballot.getBallot());
            investigator._trueRole = "Investigator";
            _container._investigator.add(investigator);
            _buttons.get(investigator.getID() - 1)._villager = investigator;
        }

        for (int i = 0; i < HEALER_COUNT; i++) {
            Entity healer = new Healer();
            healer.setID(ballot.getBallot());
            healer._trueRole = "Healer";
            _container._healer.add(healer);
            _buttons.get(healer.getID() - 1)._villager = healer;
        }

        _container.addAll();

        ballot = null;

        updateButtonsGraphics();
    }


    public void nextTurn() {
        if (_gameEnd) {
            return;
        }

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
            if (_entity._harmed && !_entity._dead) {
                _entity.applyEffect();
                _entity.revealRole();
                _mainController.addEvent(_entity.getID() + " was killed.");
                _mainController.addEvent(_entity.getID() + " was " + _entity._role);
            }
        }

        day++;
        _lynchActed = false;

        updateButtonsGraphics();
        checkWinCondition();
    }



    public void cancelAction() {
        this._currentAction = "";
    }

    public void registerAction(String action) {
        if (_gameEnd) {
            return;
        }

        this._currentAction = action;
        _mainController.pingToolTip(this._currentAction + " Action(s) available.");
    }

    public void onAction(Entity target) {
        if (_gameEnd) {
            return;
        }

        if (this._currentAction == "INVESTIGATOR") {
            _container._investigator.get(0).regigsterAction(target);
        } else if (this._currentAction == "HEALER") {
            _container._healer.get(0).regigsterAction(target);
        } else if (this._currentAction == "LYNCH") {
            if (!_lynchActed) {
                target._dead = true;
                _lynchActed = true;
            } else {
                _mainController.pingToolTip("You already voted 1 this turn.");
            }
        }
    }

    private void updateButtonsGraphics() {
        if (this._container == null) {
            return;
        }

        for (MyButton button : _buttons) {
            if (button._villager._dead) {
                button.getStyleClass().clear();
                button.getStyleClass().add("button_custom");
                button.getStyleClass().add("button_dead");
            } else if (button._villager._trueRole == "Enemy") {
                button.getStyleClass().clear();
                button.getStyleClass().add("button_custom");
                button.getStyleClass().add("button_enemy");
            }
        }
    }

    private void getWill(int ID) {
        System.out.println("Villager " + ID + " 's Will");
    }

    private void checkWinCondition() {
        _container.updateStatus();
        if (_container._enemyLeft > _container._villagerLeft) {
            _gameEnd = true;
            _mainController.addEvent("You lose!");
        }
    }
}
