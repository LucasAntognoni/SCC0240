import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

public class ManagerViewController extends AnchorPane {

    // Observable Table Data
    private ObservableList<DataSerialObject> atletaData = FXCollections.observableArrayList();

    @FXML
    private Button botaoInserir;
    @FXML
    private Button botaoDeletar;
    @FXML
    private Button botaoEditar;

    @FXML
    private TableView<DataSerialObject> tabelaAtleta;
    @FXML
    private TableColumn<DataSerialObject, String> colID;
    @FXML
    private TableColumn<DataSerialObject, String> colNome;
    @FXML
    private TableColumn<DataSerialObject, String> colSexo;
    @FXML
    private TableColumn<DataSerialObject, String> colNascimento;
    @FXML
    private TableColumn<DataSerialObject, String> colNacao;
    @FXML
    private TableColumn<DataSerialObject, String> colCPF;
    @FXML
    private TableColumn<DataSerialObject, String> colAltura;
    @FXML
    private TableColumn<DataSerialObject, String> colPeso;
    @FXML
    private TableColumn<DataSerialObject, String> colPunicoes;
    @FXML
    private TableColumn<DataSerialObject, String> colImpedido;

    @FXML
    private Label labelID;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelSexo;
    @FXML
    private Label labelNascimento;
    @FXML
    private Label labelNacao;
    @FXML
    private Label labelCPF;
    @FXML
    private Label labelAltura;
    @FXML
    private Label labelPeso;
    @FXML
    private Label labelPunicoes;
    @FXML
    private Label labelImpedido;

    public ObservableList<DataSerialObject> getAtletaData()
    {
        return atletaData;
    }

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
    }

    @FXML
    private void handleConectar(ActionEvent event) throws ClassNotFoundException, SQLException
    {
        atletaData.clear();

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8936951/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Do data query
        ResultSet resultSet = statement.executeQuery("SELECT atleta_id, nome, sexo, nascimento, nacao_id, iscpf, altura, peso, qpunicoes, sf_isatletaimpedido(atleta_id) as impedido FROM atleta");

        // Add to table
        while (resultSet.next()) {

            this.atletaData.add(new DataSerialObject(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9), resultSet.getString(10)));
        }

        tabelaAtleta.setItems(this.atletaData);

        resultSet.close();
        statement.close();
        connection.close();
    }

    @FXML
    private void handleInserir(ActionEvent event)
    {

    }

    @FXML
    private void handleDeletar(ActionEvent event) throws ClassNotFoundException, SQLException
    {
        String atletaID  = tabelaAtleta.getSelectionModel().getSelectedItem().getId_atleta();

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8936951/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        try {
            statement.executeUpdate("DELETE FROM ATLETA WHERE ATLETA_ID = " + atletaID);
        }
        catch (Exception e) {
            System.out.print("Erro ao deletar atleta: dependência encontrada!");
        }

        statement.close();
        connection.close();
    }

    @FXML
    private void handleEditar(ActionEvent event)
    {

    }
}
