package kkm;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;


public class DBworker extends BaseClass {


    public static List<String> sqlSentToGateway(String environment, String scriptResult, String srcID, String status) {
        List<String> result = null;
        String scriptFileName = "";
        switch (status) {
            case "ERROR":
                scriptFileName = "scriptUpdate.sql";
                break;
            case "DONE":
                scriptFileName = "updateScript2.sql";
                break;
            case "FAIL":
                scriptFileName = "updateScript3.sql";
        }
        String urlDB = getUrlDB(environment);
        try {
            Class.forName(dbDriverClass);
            Connection con = DriverManager.getConnection(urlDB, login, password);
            Statement stmt = con.createStatement();
            String script = "";
            switch (scriptResult) {
                case "checkStatusCode":
                    String rightString1 = new String(Files.readAllBytes(Paths.get(paths + scriptFileName)), StandardCharsets.UTF_8);
                    script = rightString1.replace("--srcID--", srcID);
                    break;
                case "tableString":
                    script = "select * from ProcessStatus where srcID = '" + srcID + "'";
            }
            ResultSet rs = stmt.executeQuery(script);
            log.info(script);
            List<String> tableString = new ArrayList<>();
            String list = "";
            while (rs.next()) {

                tableString.add(0, rs.getString("SystemCode"));
                tableString.add(1, rs.getString("EntityCode"));
                tableString.add(2, rs.getString("fiscGateCheckStatusCode"));
                switch (status) {
                    case "ERROR":
                    case "FAIL":
                        list = rs.getString("fiscGateErrorCode");
                        break;
                    case "DONE":
                        list = "nothing";
                        break;
                }
                tableString.add(3, list);
                tableString.add(4, rs.getString("srcID"));
                log.info("Table String info is: " + tableString);
                con.close();
                result = tableString;
            }
        } catch (ClassNotFoundException | IOException | SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> sqlGetSRCID(String environment, String status) {
        String script;
        List<String> result = null;
        List<String> tableColons = new ArrayList<>();
        String urlDB = getUrlDB(environment);
        try {
            Class.forName(dbDriverClass);
            Connection con = DriverManager.getConnection(urlDB, login, password);
            Statement stmt = con.createStatement();
            script = selectByStatusCode + status + "'";
            ResultSet rs = stmt.executeQuery(script);
            log.info("Script is: " + script);
            while (rs.next()) {
                List<String> result1;
                tableColons.add(0, rs.getString("srcID"));
                tableColons.add(1, rs.getString("SystemCode"));
                tableColons.add(2, rs.getString("EntityCode"));
                log.info("srcID is: " + tableColons.get(0));
                con.close();
                result1 = tableColons;
                result = result1;
            }
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void sqlScriptVoid(String environment, String srcID, String scriptFileName, String filialCode, String inn, String isSentToGateway) throws SQLException, ClassNotFoundException, IOException {

        Class.forName(dbDriverClass);
        String urlDB = getUrlDB(environment);
        String finishTime = "";
        switch (isSentToGateway) {
            case "YES":
                finishTime = "23:59:59";
                break;
            case "NO":
                finishTime = "00:00:00";
        }
        Connection con = DriverManager.getConnection(urlDB, login, password);
        Statement stmt = con.createStatement();
        String CID = randomNumeric(10);
        String rightString1 = new String(Files.readAllBytes(Paths.get(paths + scriptFileName)), StandardCharsets.UTF_8);
        String script = rightString1.replace("--srcID--", srcID).replace("--CID--", CID).replace("--finishTime--", finishTime).replace("--startTime--", "00:00:00").replace("--filialCode--", filialCode).replace("--inn--", inn);
        log.info("Script is: " + script);
        try {
            stmt.executeQuery(script);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }


    public static List<String> sqlNoSentToGateway(String environment, String srcID) {
        List<String> result = null;
        String scriptFileName = "updateTimeSelect.sql";
        String urlDB = getUrlDB(environment);
        try {
            Class.forName(dbDriverClass);
            Connection con = DriverManager.getConnection(urlDB, login, password);
            Statement stmt = con.createStatement();
            String rightString1 = new String(Files.readAllBytes(Paths.get(paths + scriptFileName)), StandardCharsets.UTF_8);
            String script = rightString1.replace("--srcID--", srcID);
            ResultSet rs = stmt.executeQuery(script);
            log.info(script);
            List<String> tableString = new ArrayList<>();
            List<String> result1;
            while (rs.next()) {
                tableString.add(0, rs.getString("SystemCode"));
                tableString.add(1, rs.getString("EntityCode"));
                String status = rs.getString("fiscGateCheckStatusCode");
                tableString.add(2, status);
                String code = rs.getString("fiscGateErrorCode");
                tableString.add(3, code);
                String mess = rs.getString("fiscGateErrorMessage");
                tableString.add(4, mess);
                tableString.add(5, rs.getString("srcID"));
                tableString.add(6, rs.getString("fiscGateCheckID"));
                result1 = tableString;
                log.info("Table String info is: " + result1);
                con.close();
                result = result1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}