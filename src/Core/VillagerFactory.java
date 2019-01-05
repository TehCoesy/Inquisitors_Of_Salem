package Core;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class VillagerFactory {
    private final int SQUARE_SIZE = 100;
    private final int COUNT_PER_ROW = 4;

    public ArrayList<Button> _players = new ArrayList<>();

    public Button createPlayer(int _id) {
        int _calc = _id % COUNT_PER_ROW;
        Button button = new Button();
        button.setPrefWidth(SQUARE_SIZE);
        button.setPrefHeight(SQUARE_SIZE);
        button.setLayoutX(10 + (_calc) * (SQUARE_SIZE + 20));
        button.setLayoutY(10 + ((_id - _calc) / COUNT_PER_ROW) * (SQUARE_SIZE + 20));
        button.setText("Villager " + (_id + 1));
        button.setOnAction(e -> {
            System.out.println("Villager " + (_id + 1));
        });
        _players.add(button);
        return button;
    }
}
