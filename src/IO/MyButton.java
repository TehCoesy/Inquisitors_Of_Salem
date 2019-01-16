package IO;

import Entities.Entity;
import javafx.scene.control.Button;

public class MyButton extends Button {
    private int _ID;
    public Entity _villager;

    public void dead() {
        String _currentText = getText();
        setText(_currentText + "\n[DEAD]");
    }


    public int getID() {
        return _ID;
    }

    public void setID(int _ID) {
        this._ID = _ID;
    }

    private void updateText() {
        String text = "Villager " + _ID + "\n[" + _villager._role + "] ";
        if (_villager._dead) {
            text += "[DEAD]";
        }
        this.setText(text);
    }
}
