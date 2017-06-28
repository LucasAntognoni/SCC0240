/* JDBC connection and wrangler objects */
import java.sql.*; /* JDBC connection handler */
import java.io.FileOutputStream; /* For writing results to pdf file */
import java.io.ByteArrayOutputStream; /* holds the XML output of the SQL results */

/* Document and PdfWriter objects to create PDF */
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph; /* To push XML data into PDF */
import com.sun.rowset.WebRowSetImpl; /* Rowset of SQL output data */

class GenReports {

    public static void genReport1() throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8602430/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Query result
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT a.nome AS nome_atleta, a.atleta_id AS documento_atleta, n.nome AS nome_nacao, a.nascimento " +
                "FROM atleta a " +
                "  JOIN consulta c ON (a.atleta_id = c.atleta_id) " +
                "  JOIN medico m ON (m.medico_id = c.medico_id) " +
                "  JOIN atleta_participa ap ON (ap.atleta_id = a.atleta_id) " +
                "  JOIN equipe eq ON (ap.equipe_id = eq.equipe_id) " +
                "  JOIN preparador p ON (p.preparador_id = eq.equipe_id) " +
                "  JOIN esporte e ON (e.esporte_id = eq.esporte_id) " +
                "  JOIN nacao n ON (a.nacao_id = n.nacao_id) " +
                "WHERE upper(e.nome) = 'FUTEBOL' " +
                "  AND upper(m.nome) = 'JORGE PEREIRA' " +
                "  AND upper(p.nome) = 'GUSTAVO BATISTA' ");
        //ResultSet resultSet = statement.executeQuery("SELECT atleta.nome, atleta.atleta_id FROM atleta");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rsmdColumnCount = rsmd.getColumnCount();

        // PDF form
        Document pdfData = new Document();
        PdfWriter.getInstance(pdfData, new FileOutputStream("query_as_pdf.pdf"));
        pdfData.open();

        // PDF table
        PdfPTable pdfTable = new PdfPTable(rsmdColumnCount);

        // Populate
        PdfPCell pdfTableCell;
        while (resultSet.next()) {

            for (int iColumn = 1; iColumn <= rsmdColumnCount; iColumn++) {
                String value = resultSet.getString(iColumn);
                System.out.print(rsmd.getColumnName(iColumn) + ": " + value + "; ");
                pdfTableCell = new PdfPCell(new Phrase(value));
                pdfTable.addCell(pdfTableCell);

                if (iColumn == rsmdColumnCount)
                    System.out.print("\n");
            }
            //String aName = resultSet.getString("nome_atleta");
            //pdfTableCell = new PdfPCell(new Phrase(aName));
            //pdfTable.addCell(pdfTableCell);

            //String aID = resultSet.getString("a.atleta_id");
            //pdfTableCell = new PdfPCell(new Phrase(aID));
            //pdfTable.addCell(pdfTableCell);

            //String nName = resultSet.getString("nome_nacao");
            //pdfTableCell = new PdfPCell(new Phrase(nName));
            //pdfTable.addCell(pdfTableCell);

            //String aBDate = resultSet.getString("a.nascimento");
            //pdfTableCell = new PdfPCell(new Phrase(aBDate));
            //pdfTable.addCell(pdfTableCell);
        }

        // Attach
        pdfData.add(pdfTable);
        pdfData.close();

        // Close all
        resultSet.close();
        statement.close();
        connection.close();
        System.out.println("Generated report.");
    }

    public static void genReport2() throws Exception
    {
        System.out.println();
    }

    public static void genReport3() throws Exception
    {
        System.out.println();
    }
}
