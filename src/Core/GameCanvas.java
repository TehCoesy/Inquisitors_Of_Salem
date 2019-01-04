package Core;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameCanvas extends Canvas {
    private int PLAYER_COUNT = 12;
    private int SQUARE_SIZE = 100;
    private int COUNT_PER_ROW = 4;

    public void setCount(int count) {
        this.PLAYER_COUNT = count;
    }

    public void render() {
        GraphicsContext g = this.getGraphicsContext2D();
        g.setFill(Color.BLUE);

        for (int i = 0; i < PLAYER_COUNT; i++) {
            int _calc = i % COUNT_PER_ROW;
            g.fillRect(10 + (_calc) * (SQUARE_SIZE + 10), 10 + ((i - _calc) / COUNT_PER_ROW) * (SQUARE_SIZE + 10), SQUARE_SIZE, SQUARE_SIZE);
        }
    }
}
