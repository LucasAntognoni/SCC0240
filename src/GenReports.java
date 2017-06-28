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

    public static void genReport1(String mod_name, String med_name, String prep_name) throws Exception
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
                "WHERE upper(e.nome) = " + mod_name.toUpperCase() +
                "  AND upper(m.nome) = " + med_name.toUpperCase() +
                "  AND upper(p.nome) = " + prep_name.toUpperCase());

        // Metadata
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rsmdColumnCount = rsmd.getColumnCount();

        // PDF form
        Document pdfData = new Document();
        PdfWriter.getInstance(pdfData, new FileOutputStream("query_reportatletas.pdf"));
        pdfData.open();

        // PDF table
        PdfPCell pdfTableCell;
        PdfPTable pdfTable = new PdfPTable(rsmdColumnCount);

        // This is totally not cool
        pdfTableCell = new PdfPCell(new Phrase("Nome do atleta"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("ID do atleta"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("Nacionalidade"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("Data de nascimento"));
        pdfTable.addCell(pdfTableCell);

        // Populate
        while (resultSet.next()) {

            for (int iColumn = 1; iColumn <= rsmdColumnCount; iColumn++) {
                String value = resultSet.getString(iColumn);
                pdfTableCell = new PdfPCell(new Phrase(value));
                pdfTable.addCell(pdfTableCell);
            }
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

    public static void genReport2(String country_name) throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8602430/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Query result
        ResultSet resultSet = statement.executeQuery(" select medico_id, nome, crm, endereco, quantidade from " +
            " ( " +
                    " select m.medico_id, m.nome, m.crm, m.endereco, count (distinct a.atleta_id) as quantidade " +
                    " from medico m " +
                    " join consulta c on (c.MEDICO_ID = m.medico_id) " +
                    " join atleta a on (c.ATLETA_ID = a.atleta_id) " +
                    " join nacao n on (a.nacao_id = n.nacao_id) " +
                    " where upper(n.nome) = " + country_name.toUpperCase() +
                    " group by m.medico_id, m.nome, m.crm, m.endereco " +
            " ) " +
        " where quantidade >= 1 ");

        // Metadata
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rsmdColumnCount = rsmd.getColumnCount();

        // PDF form
        Document pdfData = new Document();
        PdfWriter.getInstance(pdfData, new FileOutputStream("query_reportmedicos.pdf"));
        pdfData.open();

        // PDF table
        PdfPCell pdfTableCell;
        PdfPTable pdfTable = new PdfPTable(rsmdColumnCount);

        // This is totally not cool
        pdfTableCell = new PdfPCell(new Phrase("ID do médico"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("Nome"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("CRM"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("Endereço"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("Número de pacientes"));
        pdfTable.addCell(pdfTableCell);

        // Populate
        while (resultSet.next()) {

            for (int iColumn = 1; iColumn <= rsmdColumnCount; iColumn++) {
                String value = resultSet.getString(iColumn);
                pdfTableCell = new PdfPCell(new Phrase(value));
                pdfTable.addCell(pdfTableCell);
            }
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

    public static void genReport3() throws Exception
    {
         Class.forName("oracle.jdbc.driver.OracleDriver");

        // Instance connection
        Connection connection = DriverManager.getConnection
                ("jdbc:oracle:thin:8602430/a@grad.icmc.usp.br:15215:orcl");

        // Instance statement
        Statement statement = connection.createStatement();

        // Query result
        ResultSet resultSet = statement.executeQuery(" select p.preparador_id, p.nome, p.iscpf as brasileiro, " +
                "    count(distinct td.atleta_id) as pegos_doping, " +
                "    count(distinct a.atleta_id) as total_atletas, " +
                "    (case count(distinct a.atleta_id) " +
                "    when 0 then 0 " +
                "    else (count(distinct td.atleta_id) / count(distinct a.atleta_id)) " +
                "    end) as razao " +
                " from preparador p " +
                "    join atleta_participa ap on (p.preparador_id = ap.equipe_id) " +
                "    join atleta a on (a.atleta_id = ap.atleta_id) " +
                "    left join testedoping td on (td.ATLETA_ID = a.atleta_id and td.ispositivo = 'S') " +
                " group by p.preparador_id, p.nome, p.iscpf " +
                " order by razao desc");

        // Metadata
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rsmdColumnCount = rsmd.getColumnCount();

        // PDF form
        Document pdfData = new Document();
        PdfWriter.getInstance(pdfData, new FileOutputStream("query_reporttreinadores.pdf"));
        pdfData.open();

        // PDF table
        PdfPCell pdfTableCell;
        PdfPTable pdfTable = new PdfPTable(rsmdColumnCount);

        // This is totally not cool
        pdfTableCell = new PdfPCell(new Phrase("ID do preparador"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("Nome"));
        pdfTable.addCell(pdfTableCell);

        pdfTableCell = new PdfPCell(new Phrase("Brasileiro?"));
        pdfTable.addCell(pdfTableCell);

        // Populate
        while (resultSet.next()) {

            for (int iColumn = 1; iColumn <= rsmdColumnCount; iColumn++) {
                String value = resultSet.getString(iColumn);
                pdfTableCell = new PdfPCell(new Phrase(value));
                pdfTable.addCell(pdfTableCell);
            }
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
}
