import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ManagerViewController extends AnchorPane {

    // Observable Table Data
    private ObservableList<Atleta> atletaData = FXCollections.observableArrayList();

    @FXML
    private Button buttonRetrieve;
    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonUpdate;

    @FXML
    private TableView<Atleta> tabelaAtleta;
    @FXML
    private TableColumn<Atleta, String> colID;
    @FXML
    private TableColumn<Atleta, String> colNome;
    @FXML
    private TableColumn<Atleta, String> colSexo;
    @FXML
    private TableColumn<Atleta, String> colNascimento;
    @FXML
    private TableColumn<Atleta, String> colNacao;
    @FXML
    private TableColumn<Atleta, String> colCPF;
    @FXML
    private TableColumn<Atleta, String> colAltura;
    @FXML
    private TableColumn<Atleta, String> colPeso;
    @FXML
    private TableColumn<Atleta, String> colPunicoes;
    @FXML
    private TableColumn<Atleta, String> colImpedido;

    public ManagerViewController() {}

    @FXML
    private void initialize()
    {
        colID.setCellValueFactory(cellData ->          cellData.getValue().id_atletaProperty());
        colNome.setCellValueFactory(cellData ->             cellData.getValue().nomeProperty());
        colSexo.setCellValueFactory(cellData ->             cellData.getValue().sexoProperty());
        colNascimento.setCellValueFactory(cellData -> cellData.getValue().nascimentoProperty());
        colNacao.setCellValueFactory(cellData ->        cellData.getValue().id_nacaoProperty());
        colCPF.setCellValueFactory(cellData ->               cellData.getValue().cpfProperty());
        colAltura.setCellValueFactory(cellData ->         cellData.getValue().alturaProperty());
        colPeso.setCellValueFactory(cellData ->             cellData.getValue().pesoProperty());
        colPunicoes.setCellValueFactory(cellData ->     cellData.getValue().punicoesProperty());
        colImpedido.setCellValueFactory(cellData ->     cellData.getValue().impedidoProperty());

        // Lock delete and update
        buttonUpdate.setDisable(true);
        buttonDelete.setDisable(true);

        // Unlock delete and update if a valid item is clicked
        tabelaAtleta.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (tabelaAtleta.getSelectionModel().getSelectedItem() != null) {
                    buttonUpdate.setDisable(false);
                    buttonDelete.setDisable(false);
                }
            }
        });

    }

    @FXML
    private void handleRetrieve(ActionEvent event) throws ClassNotFoundException, SQLException
    {
        // Lock button
        buttonRetrieve.setDisable(true);

        // Instance connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8602430/a@grad.icmc.usp.br:15215:orcl");
                //("jdbc:oracle:thin:8936951/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Do data query
        ResultSet resultSet = statement.executeQuery("SELECT atleta_id, nome, sexo, nascimento, nacao_id, iscpf, altura, peso, qpunicoes, sf_isatletaimpedido(atleta_id) as impedido FROM atleta");

        // Add to table
        atletaData.clear();
        while (resultSet.next()) {
            this.atletaData.add(new Atleta(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9), resultSet.getString(10)));
        }

        tabelaAtleta.setItems(this.atletaData);

        resultSet.close();
        statement.close();
        connection.close();

        // Unlock button
        buttonRetrieve.setDisable(false);
    }

    @FXML
    private void handleInsert(ActionEvent event) throws IOException
    {
        // Instance objects
        Stage newStage = new Stage();
        Parent newRoot = FXMLLoader.load(getClass().getResource("InsertDialogController.fxml"));

        // Compose dialog stage
        newStage.setScene(new Scene(newRoot));
        newStage.setTitle("Inserir Registro");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(buttonInsert.getScene().getWindow());
        newStage.showAndWait();
    }

    @FXML
    private void handleDelete(ActionEvent event) throws ClassNotFoundException, SQLException
    {
        // Lock button
        buttonDelete.setDisable(true);

        // Info to be queried
        String atletaID  = tabelaAtleta.getSelectionModel().getSelectedItem().getId_atleta();

        // Instance connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8602430/a@grad.icmc.usp.br:15215:orcl");
                //("jdbc:oracle:thin:8936951/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        try {
            // Delete dependencies
            statement.executeUpdate("DELETE FROM ATLETA_PARTICIPA WHERE ATLETA_ID = " + atletaID);
            statement.executeUpdate("DELETE FROM ATLETA_ROTINA WHERE ATLETA_ID = " + atletaID);
            statement.executeUpdate("DELETE FROM TESTEDOPING WHERE ATLETA_ID = " + atletaID); // DOWN THERE!
            statement.executeUpdate("DELETE FROM CONSULTA WHERE ATLETA_ID = " + atletaID);
            statement.executeUpdate("DELETE FROM LESAO WHERE ATLETA_ID = " + atletaID);

            System.out.println("Deleted foreign key dependencies.");

            // Delete from table
            statement.executeUpdate("DELETE FROM ATLETA WHERE ATLETA_ID = " + atletaID);

            System.out.println("Deleted main entry.");
        }
        catch (Exception e) {
            System.out.print("Erro ao deletar atleta: dependência encontrada!");
        }

        // Close stuff
        statement.close();
        connection.close();

        // Unlock button
        buttonDelete.setDisable(false);
    }

    @FXML
    private void handleUpdate(ActionEvent event)
    {

    }
}
