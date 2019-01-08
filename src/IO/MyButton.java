package IO;

import javafx.scene.control.Button;

public class MyButton extends Button {
    private int _ID;

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
}
