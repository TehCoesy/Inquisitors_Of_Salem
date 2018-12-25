import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameApplication extends Application {
    //Launch
    public static void main(String[] args) {
        launch(args);
    }

    //InitApplication
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/MainGame.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,690,490);

        Controller mainController = loader.getController();

        mainController.init();

        //Basic main Window parameters
        primaryStage.setTitle("Inquisitors Of Salem");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
