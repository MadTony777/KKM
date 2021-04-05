package kkm;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Kibana extends BaseClass{

    public static String searchForServices(String environment, String id) {
        Logger log = LoggerFactory.getLogger(UnitTests.class);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
            String url = "http://elog.vsk.ru:9200/" + urlKibanaSearch + "-" + newstring + "/_search?q=%20message:%20%22CID=" + id + "%22%20";
            log.info("URL is : " + url);
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            result = responseBody;
            log.info("Response message : " + responseBody);
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
            String url = "http://elog.vsk.ru:9200/" + urlKibanaSearch + "-" + newstring + "/_search?q=%20message:%20%22CID=" + id + "%22%20";
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


    public static String searchForDB(String environment, String srcID) {
        Logger log = LoggerFactory.getLogger(UnitTests.class);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
            String url = "http://elog.vsk.ru:9200/" + urlKibana + "-" + newstring + "/_search?q=%20message:%20%22" + searchField + "%22%20AND%20%22" + srcID + "%22%20&size=500";
            log.info("URL is : " + url);
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            result = responseBody;
            log.info("Response message : " + responseBody);
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
            String url = "http://elog.vsk.ru:9200/" + urlKibana + "-" + newstring + "/_search?q=%20message:%20%22" + searchField + "%22%20AND%20%22" + srcID + "%22%20&size=500";
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


    public static String searchForDB(String environment) {
        Logger log = LoggerFactory.getLogger(UnitTests.class);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
            String url = "http://elog.vsk.ru:9200/" + urlKibana + "-" + newstring + "/_search?q=%20message:%20%22" + searchField + "%22%20&size=5";
            log.info("URL is : " + url);
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            result = responseBody;
            log.info("Response message : " + responseBody);
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
            String url = "http://elog.vsk.ru:9200/" + urlKibana + "-" + newstring + "/_search?q=%20message:%20%22" + searchField + "%22%20&size=5";
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


    public String searchForPhone(String environment , String id) {
        Logger log = LoggerFactory.getLogger(UnitTests.class);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
            String searchField = "KkmService:%20REST%20request%20Operation%20!%27PostReceipt!%27%20was%20call%20with%20body";
            String url = "http://elog.vsk.ru:9200/" + urlKibanaSearch + "-" + newstring + "/_search?q=%20message:%20%22" + searchField + "%22%20AND%20%22" + id + "%22%20";
            log.info("URL is : " + url);
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            result = responseBody;
            log.info("Response message : " + responseBody);
        } catch (Exception ex) {
            log.error("!!!!!!!!!!!!!!!!!!!!Something goes wrong!!!!!!!!!!!!!!!!!!!!");
        }
        return result;
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
            String searchField = "KkmService:%20REST%20request%20Operation%20!%27PostReceipt!%27%20was%20call%20with%20body";
            String url = "http://elog.vsk.ru:9200/" + urlKibanaSearch + "-" + newstring + "/_search?q=%20message:%20%22" + searchField + "%22%20AND%20%22" + id + "%22%20";
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
