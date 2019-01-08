package Core;

import IO.MyButton;

public class VillagerFactory {
    private int SQUARE_SIZE = 100;
    private int COUNT_PER_ROW = 4;

    public void settings(int size, int per_row) {
        this.SQUARE_SIZE = size;
        this.COUNT_PER_ROW = per_row;
    }

    public MyButton createPlayer(int _id) {
        int _calc = _id % COUNT_PER_ROW;
        MyButton button = new MyButton();
        button.setPrefWidth(SQUARE_SIZE);
        button.setPrefHeight(SQUARE_SIZE);
        button.setLayoutX(20 + (_calc) * (SQUARE_SIZE + 20));
        button.setLayoutY(20 + ((_id - _calc) / COUNT_PER_ROW) * (SQUARE_SIZE + 20));
        button.setText("Villager " + (_id + 1));
        button.setID(_id + 1);
        return button;
    }
}
