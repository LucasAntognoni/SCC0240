import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    public ObservableList<Atleta> getAtletaData() {
        return atletaData;
    }

    void parseData(ResultSet resultSet) throws SQLException {

        ResultSetMetaData rsmd = resultSet.getMetaData();

        int col = rsmd.getColumnCount();

        int i = 0;

        while (resultSet.next()) {

            for (i = 1; i <= col; i++) {

                if (i > 1) System.out.print(", ");
                String colValue = resultSet.getString(i);
                System.out.print(colValue + " " + rsmd.getColumnName(i));
            }
            System.out.print("\n");
        }

    }


    @FXML
    void handleConectar(ActionEvent event) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8632455/aaa@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Do query
        this.resultSet = statement.executeQuery("SELECT * FROM atleta");

        parseData(this.resultSet);
    }


    @FXML
    void handleInserir(ActionEvent event){

    }

    @FXML
    void handleEditar(ActionEvent event){

    }

    @FXML
    void handleDeletar(ActionEvent event){

    }


}
