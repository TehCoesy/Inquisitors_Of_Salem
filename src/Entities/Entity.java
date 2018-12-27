package Entities;

public class Entity {
    private int _ID;
    private String _role = "Villager";
    private boolean _dead;

    public void kill() {
        this._dead = true;
    }

    public boolean isDead() {
        return this._dead;
    }

    public int getID() {
        return _ID;
    }

    public void setID(int _ID) {
        this._ID = _ID;
    }

    public String getRole() {
        return _role;
    }

    public void setRole(String _role) {
        this._role = _role;
    }

    public String toString() {
        String _output = this._role + " ID: " + this._ID;
        return _output;
    }
}
