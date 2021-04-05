package kkm;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.NotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Services extends BaseClass {


    public static String PostCheckId(String completeUrl, String iD, String fileName, String key) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(completeUrl);
        httpPost.setHeader("Content-type", "application/json");
        String oldbody = new String(Files.readAllBytes(Paths.get(paths + fileName)), StandardCharsets.UTF_8);
        String body = oldbody.replace("--messageId--", iD);
        String result;
        try {
            StringEntity stringEntity = new StringEntity(body);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            String responce = EntityUtils.toString(response.getEntity());
            log.info(responce);
            httpClient.execute(httpPost);
            String checkId = String.valueOf(getValuesForGivenKey("[" + responce + "]", key));
            log.info(checkId);
            result = checkId.replace("[", "").replace("]", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String PostCheckId(String completeUrl, String iD, String fileName, String key, String phoneValue) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(completeUrl);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("X-VSK-CorrelationId", iD);
        String oldbody = new String(Files.readAllBytes(Paths.get(paths + fileName)), StandardCharsets.UTF_8);
        String body = oldbody.replace("--messageId--", iD).replace("--phone--", phoneValue);
        String result;
        try {
            StringEntity stringEntity = new StringEntity(body);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            String responce = EntityUtils.toString(response.getEntity());
            log.info(responce);
            httpClient.execute(httpPost);
            String checkId = String.valueOf(getValuesForGivenKey("[" + responce + "]", key));
            log.info(checkId);
            result = checkId.replace("[", "").replace("]", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getReceiptStatusFromReceiptInfo(String url1, String iD, String checkId, String tag) throws IOException {
        String url = url1 + checkId;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-VSK-CorrelationId", iD);
        con.setRequestProperty("Accept-Charset", "UTF-8");
        int responseCode = con.getResponseCode();

        String result = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                log.info("\nSending 'GET' request to URL : " + url);
                log.info("Response Code : " + responseCode);
            }
            String part = response.toString();
            String full = "[" + part + "]";
            List<String> receiptStatus;
            switch (tag){
                case "receiptInfo":
                    List<String> receiptInfo;
                    receiptInfo = getValuesForGivenKey(full, "receiptInfo");
                    String ri = String.valueOf(receiptInfo);
                    receiptStatus = getValuesForGivenKey(ri, "receiptStatus");
                    break;
                case "receiptStatus":
                    receiptStatus = getValuesForGivenKey(full, "receiptStatus");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tag);
            }
            String rsFull = String.valueOf(receiptStatus);
            result = rsFull;
            log.info(rsFull);
        }
        return result;
    }

    public static String putInputStream(String url1, String iD, String checkId) throws IOException, NotFoundException {
        String url = url1 + checkId;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("X-VSK-CorrelationId", iD);
        con.setRequestProperty("Accept-Charset", "UTF-8");
        String full = null;
        int responseCode = con.getResponseCode();
        log.info("\nSending 'GET' request to URL : " + url);
        log.info("Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            full = response.toString();
            log.info(full);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return full;
    }


    public static String getInputStream(String url1, String iD, String checkId) throws IOException {
        String url = url1 + checkId;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-VSK-CorrelationId", iD);
        con.setRequestProperty("Accept-Charset", "UTF-8");
        int responseCode = con.getResponseCode();
        log.info("\nSending 'GET' request to URL : " + url);
        log.info("Response Code : " + responseCode);
        String result = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            String Full = response.toString();
            result = Full;
            log.info(Full);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String ErrorStreamRequests(String url1, String iD, String checkId, String operation) throws IOException {
        String url = url1 + checkId;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(operation);
        con.setRequestProperty("X-VSK-CorrelationId", iD);
        con.setRequestProperty("Accept-Charset", "UTF-8");
        int responseCode = con.getResponseCode();
        log.info("\nSending '" + operation + "' request to URL : " + url);
        log.info("Response Code : " + responseCode);
        String result = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            String full = response.toString();
            result = full;
            log.info(full);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    static String requestPutCheckToShFI(String messageId, String environment, String fileName) throws IOException {
        String requestBody = new String(Files.readAllBytes(Paths.get(paths + fileName)), StandardCharsets.UTF_8);
        requestBody = requestBody.replace("${MESSAGEID}", messageId);
        String url;
        if (fileName.equals("putCheckCorrection.json")||fileName.equals("allPutCheckCorrectionToShFI.json")) {
            url = getUrl(environment) + "correction";
        }
            else{
            url = getUrl(environment);
        }
        log.info("Request url is: " + url);
        Response response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .header("X-VSK-CorrelationId", messageId)
                .body(requestBody)
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response();
        String serviceResponse = response.body().asString();
        log.info("Response is: " + serviceResponse);
        return serviceResponse;
    }

    static String requestGetCheck(String checkId, String environment, int statusCode) {
        String cid = String.valueOf(UUID.randomUUID());
        String url = getUrl(environment) + checkId;
        log.info("Request url is: " + url);
        Response response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .header("X-VSK-CorrelationId", cid)
                .get(url)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
        String serviceResponse = response.body().asString();
        System.out.println("Responce is: " + serviceResponse);
        return serviceResponse;
    }


    static String requestPutCheckCorrection(String urlEnd, String environment, int statusCode, String filename) throws IOException {
        String cid = String.valueOf(UUID.randomUUID());
        String requestBody = new String(Files.readAllBytes(Paths.get(paths + filename)), StandardCharsets.UTF_8);
        String url = getUrl(environment) + urlEnd;
        log.info("Request url is: " + url);
        Response response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .header("X-VSK-CorrelationId", cid)
                .body(requestBody)
                .post(url)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
        String serviceResponse = response.body().asString();
        System.out.println("Responce is: " + serviceResponse);
        return serviceResponse;
    }



    static String requestSearchForPhone(String environment, String id) {
        String urlKibanaSearch ="";
        switch (environment){
            case "stage":
                urlKibanaSearch = "esb-stage";
                break;
            case "test":
                urlKibanaSearch = "esb-test";
        }
        String result = "";
        try {
            Date today = Calendar.getInstance().getTime();
            String newstring = new SimpleDateFormat("yyyy.MM.dd").format(today);
            String searchField = "KkmService: REST request Operation 'PostReceipt' was call with body";
            String url = "http://elog.vsk.ru:9200/" + urlKibanaSearch + "-" + newstring + "/_search?q= message: \"" + searchField + "\"AND\"" + id + "\" ";
            log.info("Request url is: " + url);
            Response response = RestAssured.given().log().all()
                    .header("content-Type", "application/json")
                    .header("Accept", "application/json")
                    .get(url)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
            result = response.body().asString();
            System.out.println("Responce is: " + result);
        } catch (Exception ex) {
            log.error("!!!!!!!!!!!!!!!!!!!!Something goes wrong!!!!!!!!!!!!!!!!!!!!");
        }
        return result;
    }


    static String requestSearchForDB(String environment) {
        String urlKibana ="";
        switch (environment){
            case "stage":
                urlKibana = "kkm-commutator-stage";
                break;
            case "test":
                urlKibana = "kkm-commutator-test";
        }
        String result = "";
        try {
            Date today = Calendar.getInstance().getTime();
            String newstring = new SimpleDateFormat("yyyy.MM.dd").format(today);
            String searchField = "ReadyForSendingReceiptKeys";
            String url = "http://elog.vsk.ru:9200/" + urlKibana + "-" + newstring + "/_search?q= message: \"" + searchField + "\"&size=5 ";
            log.info("Request url is: " + url);
            Response response = RestAssured.given().log().all()
                    .header("content-Type", "application/json")
                    .header("Accept", "application/json")
                    .get(url)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
            result = response.body().asString();
            System.out.println("Responce is: " + result);
        } catch (Exception ex) {
            log.error("!!!!!!!!!!!!!!!!!!!!Something goes wrong!!!!!!!!!!!!!!!!!!!!");
        }
        return result;
    }

    static String requestSearchForDB(String environment, String srcID) {
        String urlKibana ="";
        switch (environment){
            case "stage":
                urlKibana = "kkm-commutator-stage";
                break;
            case "test":
                urlKibana = "kkm-commutator-test";
        }
        String result = "";
        try {
            Date today = Calendar.getInstance().getTime();
            String newstring = new SimpleDateFormat("yyyy.MM.dd").format(today);
            String searchField = "ReadyForSendingReceiptKeys";
            String url = "http://elog.vsk.ru:9200/" + urlKibana + "-" + newstring + "/_search?q= message: \"" + searchField + "\" AND \"" + srcID + "\" &size=500";
            Response response = RestAssured.given().log().all()
                    .header("content-Type", "application/json")
                    .header("Accept", "application/json")
                    .get(url)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
            result = response.body().asString();
            System.out.println("Responce is: " + result);
        } catch (Exception ex) {
            log.error("!!!!!!!!!!!!!!!!!!!!Something goes wrong!!!!!!!!!!!!!!!!!!!!");
        }
        return result;
    }


    static String requestSearchForServices(String environment, String id) {
        String result = "";
        String urlKibanaSearch ="";
        switch (environment){
            case "stage":
                urlKibanaSearch = "esb-stage";
                break;
            case "test":
                urlKibanaSearch = "esb-test";
        }
        try {
            Date today = Calendar.getInstance().getTime();
            String newstring = new SimpleDateFormat("yyyy.MM.dd").format(today);
            String url = "http://elog.vsk.ru:9200/" + urlKibanaSearch + "-" + newstring + "/_search?q= message:\"CID=" + id + "\"";
            Response response = RestAssured.given().log().all()
                    .header("content-Type", "application/json")
                    .header("Accept", "application/json")
                    .get(url)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
            result = response.body().asString();
            System.out.println("Responce is: " + result);
        } catch (Exception ex) {
            log.error("!!!!!!!!!!!!!!!!!!!!Something goes wrong!!!!!!!!!!!!!!!!!!!!");
        }
        return result;
    }

}
