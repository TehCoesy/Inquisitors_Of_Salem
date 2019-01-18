package Core;

import Entities.Entity;

import java.util.ArrayList;

public class EntityContainer {
    //ENTITIES
    public ArrayList<Entity> _allEntities;
    public ArrayList<Entity> _villagers;
    public ArrayList<Entity> _enemies;
    public ArrayList<Entity> _investigator;
    public ArrayList<Entity> _healer;

    //STATUS
    public int _enemyLeft;
    public int _villagerLeft;

    public void newLists() {
        _allEntities = new ArrayList<>();
        _villagers = new ArrayList<>();
        _enemies = new ArrayList<>();
        _investigator = new ArrayList<>();
        _healer = new ArrayList<>();
    }

    public void addAll() {
        _allEntities.addAll(_villagers);
        _allEntities.addAll(_enemies);
        _allEntities.addAll(_investigator);
        _allEntities.addAll(_healer);
    }

    public void deconstruct() {
        _allEntities = null;
        _villagers = null;
        _enemies = null;
        _investigator = null;
        _healer = null;
    }

    public int getPlayerCount() {
        return this._allEntities.size();
    }

    public void updateStatus() {
        _enemyLeft = 0;
        _villagerLeft = 0;
        for (Entity entity : _allEntities) {
            if (entity._trueRole == "Enemy" && entity._dead == false) {
                _enemyLeft++;
            } else if (entity._trueRole != "Enemy" && entity._dead == false) {
                _villagerLeft++;
            }
        }
        System.out.println(_enemyLeft + " " + _villagerLeft);
    }
}
