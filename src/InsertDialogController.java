import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class InsertDialogController extends AnchorPane {

    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private TextField textField6;
    @FXML
    private TextField textField7;
    @FXML
    private TextField textField8;
    @FXML
    private TextField textField9;
    @FXML
    private TextField textField10;

    @FXML
    private void handleOkPress(ActionEvent event) throws ClassNotFoundException, SQLException
    {
        // Lock button
        buttonOk.setDisable(true);

        // Get parameters
        String param1 = textField1.getText();
        String param2 = textField2.getText();
        String param3 = textField3.getText();
        String param4 = textField4.getText();
        String param5 = textField5.getText();
        //String param6 = textField6.getText();
        String param7 = textField7.getText();
        String param8 = textField8.getText();
        //String param9 = textField9.getText();
        //String param10 = textField10.getText();

        // Instance connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8602430/a@grad.icmc.usp.br:15215:orcl");
                //("jdbc:oracle:thin:8936951/a@grad.icmc.usp.br:15215:orcl");


        // Instance statement
        Statement statement = connection.createStatement();

        // Query for country
        ResultSet resultSet = statement.executeQuery("select n.nacao_id from atleta a join nacao n " +
                "on (a.nacao_id = n.nacao_id) where upper(n.nome) like '" + param5.toUpperCase() + "'");

        // GEt nation ID
        resultSet.next();
        String nacao_id = resultSet.getString(1);

        // Insert new data
        statement.executeUpdate("insert into atleta (atleta_id, nome, sexo, nascimento, nacao_id, altura, peso) " +
                "values (" + param1 + ", '" + param2 + "', '" + param3 + "', TO_DATE('" + param4 + "', 'dd/mm/yyyy'), " +
                nacao_id + ", " + param7 + ", " + param8 + ")");
        System.out.println("Done insert.");

        // Close connection
        resultSet.close();
        statement.close();
        connection.close();

        // Unlock button
        buttonOk.setDisable(false);

        // Close window
        Stage stage = (Stage)buttonOk.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelPress(ActionEvent event) {
        Stage stage = (Stage)buttonCancel.getScene().getWindow();
        stage.close();
    }
}
