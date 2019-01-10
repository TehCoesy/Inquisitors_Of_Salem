package Core;

import Entities.Entity;

import java.util.ArrayList;

public class EntityContainer {
    public ArrayList<Entity> _allEntities;
    public ArrayList<Entity> _villagers;
    public ArrayList<Entity> _enemies;
    public ArrayList<Entity> _investigator;
    public ArrayList<Entity> _healer;

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
}
