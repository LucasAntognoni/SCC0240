import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML // fx:id="sobre"
    private Menu sobre;

    @FXML // fx:id="relatorio"
    private Menu relatorio;

    @FXML // fx:id="gerenciar"
    private Menu gerenciar;

    @FXML
    public void initialize() {

        Helper.onAction(sobre);
        Helper.onAction(relatorio);
        Helper.onAction(gerenciar);
    }

    @FXML
    void menuSobre(ActionEvent event) {

        try {

            URL paneUrl = getClass().getResource("Sobre.fxml");
            AnchorPane aPane = FXMLLoader.load( paneUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(aPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuRelatorio(ActionEvent event) {

        try {

            URL paneUrl = getClass().getResource("Relatorios.fxml");
            AnchorPane aPane = FXMLLoader.load( paneUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(aPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuGerenciar(ActionEvent event) {

        try {

            URL paneUrl = getClass().getResource("Gerenciar.fxml");
            AnchorPane aPane = FXMLLoader.load( paneUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(aPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}