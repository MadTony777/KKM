package kkm;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseClass {

    public static final String selectByStatusCode = "SELECT TOP 1 * FROM ProcessStatus Where fiscGateCheckStatusCode = '";
    public static final String paths = "src/test/java/kkm/FILES/";
    public static final String stageURL = "http://esb-stage:8501/cxf/rest/api/v1/kkm/receipts/";
    public static final String testURL = "http://esb-test01:8181/cxf/rest/api/v1/kkm/receipts/";
    public static final String stageDB = "jdbc:jtds:sqlserver://KKMCash-db-stg:1433;databaseName=KKMCash_Stage;IntegratedSecurity = true;useNTLMv2=true;domain=VSK.RU";
    public static final String testDB = "jdbc:jtds:sqlserver://KKMCash-db-test:1433;databaseName=KKMCash_Test;IntegratedSecurity = true;useNTLMv2=true;domain=VSK.RU";
    public static final String login = "esbAutotests";
    public static final String password = "qazWSX123";
    public static final String dbDriverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String arg = System.getProperty("arg", "test");
    public String environment = arg;
    public static Logger log = LoggerFactory.getLogger(UnitTests.class);

    public static List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }

    public static void pauseMethod(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getUrlDB(String environment) {
        String  urlDB;
        switch (environment) {
            case "stage":
                urlDB = stageDB;
                break;
            case "test":
                urlDB = testDB;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + environment);
        }
        return  urlDB;
    }


    public static String getUrl(String environment) {
        String  url;
        switch (environment) {
            case "stage":
                url = stageURL;
                break;
            case "test":
                url = testURL;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + environment);
        }
        return  url;
    }


//
//    public static String getUrlKibana(String environment) {
//        String  urlKibana;
//        switch (environment) {
//            case "stage":
//                urlKibana = stageURL;
//                break;
//            case "test":
//                urlKibana = testURL;
//            default:
//                throw new IllegalStateException("Unexpected value: " + environment);
//        }
//        return  urlKibana;
//    }
}
