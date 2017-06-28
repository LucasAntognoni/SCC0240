import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;


public class DBController {

    private ObservableList<Atleta> atletaData = FXCollections.observableArrayList();
    private ResultSet resultSet;

    @FXML // fx:id = botaoInserir
    private Button botaoInserir;

    @FXML // fx:id = botaoEditar
    private Button botaoEditar;

    @FXML // fx:id = botaoDeletar
    private Button botaoDeletar;

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

    public ObservableList<Atleta> getAtletaData() {
        return atletaData;
    }

    void parseData(ResultSet resultSet) throws SQLException {

        ResultSetMetaData rsmd = resultSet.getMetaData();

        while (resultSet.next()) {

            this.atletaData.add(new Atleta(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                    resultSet.getString(10)));
        }

        tabelaAtleta.setItems(this.atletaData);
    }


    @FXML
    private void initialize() {

        colID.setCellValueFactory(cellData -> cellData.getValue().id_atletaProperty());
        colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        colSexo.setCellValueFactory(cellData -> cellData.getValue().sexoProperty());
        colNascimento.setCellValueFactory(cellData -> cellData.getValue().nascimentoProperty());
        colNacao.setCellValueFactory(cellData -> cellData.getValue().id_nacaoProperty());
        colCPF.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        colAltura.setCellValueFactory(cellData -> cellData.getValue().alturaProperty());
        colPeso.setCellValueFactory(cellData -> cellData.getValue().pesoProperty());
        colPunicoes.setCellValueFactory(cellData -> cellData.getValue().punicoesProperty());
        colImpedido.setCellValueFactory(cellData -> cellData.getValue().impedidoProperty());
    }

    @FXML
    void handleConectar(ActionEvent event) throws ClassNotFoundException, SQLException {

        atletaData.clear();

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8936951/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Do query
        this.resultSet = statement.executeQuery("SELECT atleta_id, nome, sexo, nascimento, nacao_id, iscpf, altura, peso, qpunicoes, sf_isatletaimpedido(atleta_id) as impedido FROM atleta");

        parseData(this.resultSet);
    }

    @FXML
    void handleInserir(ActionEvent event){

    }

    @FXML
    void handleEditar(ActionEvent event){

    }

    @FXML
    void handleDeletar(ActionEvent event) throws ClassNotFoundException, SQLException {

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
}
