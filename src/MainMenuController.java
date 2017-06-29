import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

public class MainMenuController extends AnchorPane {

    @FXML
    private MenuItem aboutView;

    @FXML
    private MenuItem reportView;

    @FXML
    private MenuItem managerView;

    public MainMenuController() {}

    @FXML
    private void initialize()
    {
        // Any can chosen at first
        aboutView.setDisable(false);
        reportView.setDisable(false);
        managerView.setDisable(false);
    }

    @FXML
    private void handleChangeToAboutView(ActionEvent event)
    {
        // Load only once
        aboutView.setDisable(true);
        reportView.setDisable(false);
        managerView.setDisable(false);

        try {

            URL paneUrl = getClass().getResource("StaticView_About.fxml");
            AnchorPane aPane = FXMLLoader.load( paneUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(aPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleChangeToReportView(ActionEvent event)
    {
        // Load only once
        aboutView.setDisable(false);
        reportView.setDisable(true);
        managerView.setDisable(false);

        try {

            URL paneUrl = getClass().getResource("ReportViewController.fxml");
            AnchorPane aPane = FXMLLoader.load( paneUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(aPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleChangeToManagerView(ActionEvent event)
    {
        // Load only once
        aboutView.setDisable(false);
        reportView.setDisable(false);
        managerView.setDisable(true);

        try {

            URL paneUrl = getClass().getResource("ManagerViewController.fxml");
            AnchorPane aPane = FXMLLoader.load( paneUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(aPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}