import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    private static BorderPane root = new BorderPane();

    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //GenReports.genReport1(nome da modalidade, nome do médico, nome do preparador);
        //GenReports.genReport2(nome do país);
        //GenReports.genReport3();

        URL menuUrl = getClass().getResource("Menu.fxml");
        MenuBar menu = FXMLLoader.load( menuUrl );

        URL mainPaneUrl = getClass().getResource("MainPane.fxml");
        AnchorPane mainPane = FXMLLoader.load( mainPaneUrl );

        root.setTop(menu);
        root.setCenter(mainPane);

        Scene scene = new Scene(root, 640, 480);

        /*scene
                .getStylesheets()
                .add(getClass()
                        .getResource("application.css")
                        .toExternalForm());
        */

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
