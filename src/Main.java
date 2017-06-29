import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    // Compose Scene With Static Menu Bar
    private static BorderPane root = new BorderPane();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        URL mainPaneUrl = getClass().getResource("StaticView_Welcome.fxml");
        AnchorPane mainPane = FXMLLoader.load( mainPaneUrl );

        URL mainMenuUrl = getClass().getResource("MainMenuController.fxml");
        MenuBar menu = FXMLLoader.load(mainMenuUrl);

        // Plug Menu
        root.setTop(menu);
        root.setCenter(mainPane);

        // Compose Scene
        Scene scene = new Scene(root, 640, 530);
        primaryStage.setTitle("Database View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static BorderPane getRoot()
    {
        return root;
    }
}
