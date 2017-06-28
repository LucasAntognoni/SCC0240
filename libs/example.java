///* JDBC connection and wrangler objects */
//import java.sql.*; /* JDBC connection handler */
//import java.io.FileOutputStream; /* For writing results to pdf file */
//import java.io.ByteArrayOutputStream; /* holds the XML output of the SQL results */
//
///* Document and PdfWriter objects to create PDF */
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.Paragraph; /* To push XML data into PDF */
//import com.sun.rowset.WebRowSetImpl; /* Rowset of SQL output data */
//
//class Main {
//
//    public static void main (String[] args) throws Exception
//    {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//
//        // Instance connection
//        Connection connection = DriverManager.getConnection
//                ("jdbc:oracle:thin:8632455/aaa@grad.icmc.usp.br:15215:orcl");
//
//        // Instance statement
//        Statement statement = connection.createStatement();
//
//        // Query result
//        ResultSet resultSet = statement.executeQuery("select a.nome as nome_atleta, a.atleta_id, n.nome as nome_nacao, a.nascimento " +
//                " from atleta a " +
//                "   join consulta c on (a.atleta_id = c.atleta_id) " +
//                "   join medico m on (m.medico_id = c.medico_id) " +
//                "   join atleta_participa ap on (ap.atleta_id = a.atleta_id) " +
//                "   join equipe eq on (ap.equipe_id = eq.equipe_id) " +
//                "   join preparador p on (p.preparador_id = eq.equipe_id) " +
//                "   join esporte e on (e.esporte_id = eq.esporte_id) " +
//                "   join nacao n on (a.nacao_id = n.nacao_id) " +
//                " where upper(e.nome) = 'FUTEBOL' " +
//                "   and upper(m.nome) = 'JORGE PEREIRA' " +
//                "   and upper(p.nome) = 'GUSTAVO BATISTA'");
//        //ResultSet resultSet = statement.executeQuery("SELECT * FROM atleta");
//        ResultSetMetaData rsmd = resultSet.getMetaData();
//        int rsmdColumnCount = rsmd.getColumnCount();
//
//        // PDF form
//        Document pdfData = new Document();
//        PdfWriter.getInstance(pdfData, new FileOutputStream("query_as_pdf.pdf"));
//        pdfData.open();
//
//        // PDF table
//        //PdfPTable pdfTable = new PdfPTable(1);
//
//        // Populate
//        //PdfPCell pdfTableCell;
//        while (resultSet.next()) {
//
//            for (int iColumn = 1; iColumn <= rsmdColumnCount; iColumn++) {
//                String value = resultSet.getString(iColumn);
//                System.out.print(rsmd.getColumnName(iColumn) + ": " + value + "; ");
//
//                //String value = resultSet.getString(iColumn);
//                //pdfTableCell = new PdfPCell(new Phrase(value));
//                //pdfTable.addCell(pdfTableCell);
//
//                if (iColumn == rsmdColumnCount)
//                    System.out.print("\n");
//            }
//            //String aName = resultSet.getString("nome_atleta");
//            //pdfTableCell = new PdfPCell(new Phrase(aName));
//            //pdfTable.addCell(pdfTableCell);
//
//            //String aID = resultSet.getString("a.atleta_id");
//            //pdfTableCell = new PdfPCell(new Phrase(aID));
//            //pdfTable.addCell(pdfTableCell);
//
//            //String nName = resultSet.getString("nome_nacao");
//            //pdfTableCell = new PdfPCell(new Phrase(nName));
//            //pdfTable.addCell(pdfTableCell);
//
//            //String aBDate = resultSet.getString("a.nascimento");
//            //pdfTableCell = new PdfPCell(new Phrase(aBDate));
//            //pdfTable.addCell(pdfTableCell);
//        }
//
//        // Attach
//        //pdfData.add(pdfTable);
//        //pdfData.close();
//
//        // Close all
//        resultSet.close();
//        statement.close();
//        connection.close();
//        System.out.println("All done.");
//    }
//}
