package Core;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameCanvas extends Canvas {
    public GameCanvas() {
        setWidth(480);
        setHeight(400);
    }

    public void render() {
        GraphicsContext g = this.getGraphicsContext2D();
        g.setFill(Color.BLUE);
        g.fillRect(0,0,480,400);
    }
}
