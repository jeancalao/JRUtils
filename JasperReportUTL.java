/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ciahering.jasperreportutl;

/**
 *
 * @author Jean Calao
 * Data: 01/07/2024
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//String reportPath = "C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\bin\\teste.jrxml";
  //          String dataSourceUrl = "jdbc:oracle:thin:@10.20.6.11:1521:corp1_qa";
    //        String dataSourceUser = "prcf";
      //      String dataSourcePassword = "confac";

public class JasperReportUTL {
    public static String JasperReport(String[] arquivo) {
        if (arquivo.length == 0) {
           arquivo[1] = "C:\\relats\\teste.jrxml";
        }
        main(arquivo);
        return "Relatorio Executado!";
    }
    
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Carrega o driver JDBC do Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Estabelece a conexão com o banco de dados
            String dbURL = "jdbc:oracle:thin:@10.20.6.11:1521:corp1_qa";
            String username = "prcf";
            String password = "confac";
            connection = DriverManager.getConnection(dbURL, username, password);

            // Caminho para o arquivo .jasper compilado
            String reportPath = "C:\\relats\\teste.jrxml";

            // Parâmetros a serem passados para o relatório
            //Map<String, Object> parameters = new HashMap<>();
            //parameters.put("FILTER", "CFORDPRO.NRORDPRO = 883132");
            
            // compilar
            JasperReport jsreport = JasperCompileManager.compileReport(reportPath);
            
            // Preencher o relatório com os dados usando a conexão
            JasperPrint jasperPrint = JasperFillManager.fillReport(jsreport, new HashMap(), connection);

            // Exportar o relatório para PDF
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\bin\\relatorio.pdf");
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
            
            System.out.println("Relatório gerado com sucesso!");

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do Oracle não encontrado.");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados.");
        } catch (JRException e) {
            System.err.println("Erro ao preencher o relatório.");
        } finally {
            // Fecha a conexão
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}

