import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.*;


public class DBController {

    @FXML // fx:id = botaoInserir
    private Button botaoInserir;

    @FXML // fx:id = botaoEditar
    private Button botaoEditar;

    @FXML // fx:id = botaoDeletar
    private Button botaoDeletar;
    
    @FXML
    void handleConectar(ActionEvent event) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8632455/aaa@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Do query
        ResultSet resultSet = statement.executeQuery("SELECT * FROM atleta");
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
