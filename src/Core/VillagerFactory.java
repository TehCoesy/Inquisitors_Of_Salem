package Core;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class VillagerFactory {
    private final int SQUARE_SIZE = 100;
    private final int COUNT_PER_ROW = 4;

    private ArrayList<MyButton> _buttons = new ArrayList<>();

    public MyButton createPlayer(int _id) {
        int _calc = _id % COUNT_PER_ROW;
        MyButton button = new MyButton();
        button.setPrefWidth(SQUARE_SIZE);
        button.setPrefHeight(SQUARE_SIZE);
        button.setLayoutX(20 + (_calc) * (SQUARE_SIZE + 20));
        button.setLayoutY(20 + ((_id - _calc) / COUNT_PER_ROW) * (SQUARE_SIZE + 20));
        button.setText("Villager " + (_id + 1));
        button.setID(_id + 1);
        _buttons.add(button);
        /*
        button.setOnAction(e -> {
            System.out.println("Villager " + (_id + 1));
        });
        */
        return button;
    }

}
