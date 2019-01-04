package Core;

import Entities.*;
import Utility.Ballot;
import java.util.ArrayList;

public class Game {
    //GAMEPLAY
    private int day = 0;

    //GAMEPLAY PARAMETERS
    private static final int PLAYER_COUNT = 11;
    private static final int VILLAGER_COUNT = 9;
    private static final int ENEMIES_COUNT = 3;

    //UTILITY
    Ballot ballot;

    //RENDERING
    GameCanvas _gameView;

    public Game(GameCanvas gameView) {
        this._gameView = gameView;
        this._gameView.setCount(PLAYER_COUNT);

    }

    private ArrayList<Entity> _allEntities;
    private ArrayList<Entity> _villagers;
    private ArrayList<Entity> _enemies;
    private Investigator _invest;

    public void newGame() throws Exception {
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
}
