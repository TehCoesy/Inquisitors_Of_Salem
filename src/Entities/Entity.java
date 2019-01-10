package Entities;

import java.util.ArrayList;

public abstract class Entity {
    protected Entity _target = null;
    private int _ID;
    public String _role = "Villager";
    public String _trueRole = "Villager";

    //EFFECTS
    public boolean _dead;
    public boolean _harmed;

    public ArrayList<String> _will =  new ArrayList<>();

    public int getID() {
        return _ID;
    }

    public void setID(int _ID) {
        this._ID = _ID;
    }

    public String toString() {
        String _output = this._role + " ID: " + this._ID;
        return _output;
    }

    public void addWill(String text) {
        _will.add(text);
    }

    public void revealRole() {
        _role = _trueRole;
    }

    public abstract String onAction();

    public void regigsterAction(Entity target) {
        this._target = target;
    }

    public void cancelAction() {
        this._target = null;
    }

    public void applyEffect() {
        if (_harmed) {
            _dead = true;
        }
    }
}
