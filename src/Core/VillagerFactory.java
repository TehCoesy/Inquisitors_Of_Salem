package Core;

import IO.MyButton;
import javafx.scene.control.Tooltip;

public class VillagerFactory {
    private int SQUARE_SIZE = 100;
    private int COUNT_PER_ROW = 4;

    public void settings(int size, int per_row) {
        this.SQUARE_SIZE = size;
        this.COUNT_PER_ROW = per_row;
    }

    public MyButton createPlayer(int _id) {
        int _calc = (_id - 1) % COUNT_PER_ROW;
        MyButton button = new MyButton();
        button.getStyleClass().add("button_custom");
        button.getStyleClass().add("button_undiscovered");
        button.setPrefWidth(SQUARE_SIZE);
        button.setPrefHeight(SQUARE_SIZE);
        button.setLayoutX(20 + (_calc) * (SQUARE_SIZE + 20));
        button.setLayoutY(20 + ((_id - _calc - 1) / COUNT_PER_ROW) * (SQUARE_SIZE + 20));
        button.setText("Villager " + (_id));
        button.setID(_id);
        return button;
    }
}
