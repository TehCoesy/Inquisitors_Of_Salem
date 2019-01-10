package Entities;

public class Investigator extends Entity {
    @Override
    public String onAction() {
        if (_target == null) {
            return "No INVESTIGATOR action.";
        }

        if (_target._trueRole == "Enemy") {
            return _target.getID() + " is an Enemy!";
        } else {
            return _target.getID() + " is innocent.";
        }
    }
}
