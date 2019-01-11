package Entities;

public class Healer extends Entity {
    @Override
    public String onAction() {
        if (_target == null) {
            return null;
        }

        if (_target._harmed) {
            _target._harmed = false;
        }

        return "HEAL " + _target.getID();
    }
}
