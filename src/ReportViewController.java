import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ReportViewController extends AnchorPane {

    // Which Report to Create?
    private int whichReport;

    @FXML
    private Button reportSelectButtonPreparadores;
    @FXML
    private Button reportSelectButtonMedicos;
    @FXML
    private Button reportSelectButtonAtletas;
    @FXML
    private Button generateReportButton;
    @FXML
    private TextField reportTextField1;
    @FXML
    private TextField reportTextField2;
    @FXML
    private TextField reportTextField3;
    @FXML
    private TextArea reportQueryStatusText;
    @FXML
    private TextArea reportDescriptionText;

    @FXML
    private void initialize()
    {
        reportTextField1.setDisable(true);
        reportTextField2.setDisable(true);
        reportTextField3.setDisable(true);

        whichReport = 0;
    }

    @FXML
    private void handleReportSelectAtletas(ActionEvent event)
    {
        reportTextField1.setDisable(false);
        reportTextField2.setDisable(false);
        reportTextField3.setDisable(false);

        reportTextField1.setPromptText("Modalidade em questão");
        reportTextField2.setPromptText("Médico que atendeu");
        reportTextField3.setPromptText("Preparador");

        whichReport = 1;
        reportDescriptionText.setText("O relatório gerado mostrará todos os atletas de uma dada modalidade, que foram" +
                "atendidos por um certo médico e que são treinados por um certo preparador. Os três parâmetros são" +
                "fornecidos pelo usuário para uso na consulta.");
    }

    @FXML
    private void handleReportSelectMedicos(ActionEvent event)
    {
        reportTextField1.setDisable(false);
        reportTextField2.setDisable(true);
        reportTextField3.setDisable(true);

        reportTextField1.setPromptText("Nacionalidade do paciente");

        whichReport = 2;
        reportDescriptionText.setText("O relatório gerado mostrará todos os médicos que atenderam no mínimo um atleta" +
                "de uma dada nacionalidade, juntamente com o número exato de atletas que foram atendidos. Os dois " +
                "parâmetros são fornecidos pelo usuário para uso na consulta.");
    }

    @FXML
    private void handleReportSelectPreparadores(ActionEvent event)
    {
        reportTextField1.setDisable(true);
        reportTextField2.setDisable(true);
        reportTextField3.setDisable(true);

        whichReport = 3;
        reportDescriptionText.setText("O relatório gerado mostrará a quantidade de todos os treinos que ocorreram" +
                "em cada período (matutino, vespertino e noturno) juntamente com o número de atletas que treinaram" +
                "em cada um desses períodos. Nenhum parâmetro é necessário para consulta.");
    }

    @FXML
    private void handleGenerateReport(ActionEvent event)
    {
        switch (whichReport) {

            case 1:
                String param1 = reportTextField1.getText();
                String param2 = reportTextField2.getText();
                String param3 = reportTextField3.getText();
                try {
                    StaticReportGenerator.generateReport1(param1, param2, param3);
                    reportQueryStatusText.setText("Busca de informações de atleta sucedida.");
                } catch (Exception e) {
                    reportQueryStatusText.setText("Erro na recuperação de informações de atleta.");
                }
                break;

            case 2:
                String param = reportTextField1.getText();

                try {
                    StaticReportGenerator.generateReport2(param);
                    reportQueryStatusText.setText("Busca de informações de médico sucedida.");
                } catch (Exception e) {
                    reportQueryStatusText.setText("Erro na recuperação de informações de médico.");
                }
                break;

            case 3:
                try {
                    StaticReportGenerator.generateReport3();
                    reportQueryStatusText.setText("Busca de informações de preparador sucedida.");
                } catch (Exception e) {
                    reportQueryStatusText.setText("Erro na recuperação de informações de preparador.");
                }
                break;

            default: break;
        }
    }
}
