package kkm;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static kkm.Services.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class Template extends DBworker {


    public static String kkmWithCancelExpess(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        long start = System.currentTimeMillis();
        log.info("Swagger done");
        String checkId = Services.PostCheckId(url, iD, filename, "checkId");
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 110000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo")).equals("[DONE]")) {
                log.info(Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo"));
                isDone = true;
            }
        }
        Services.putInputStream(url, iD + "2", checkId + "/cancel");
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        log.info("Check creation time(status DONE) in millis: " + timeConsumedMillis);
        pauseMethod(240000);
        return requestSearchForServices(environment, iD + "2");
    }

    public static String kkmWithCancel(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        log.info("Swagger done");
        String checkId = Services.PostCheckId(url, iD, filename, "checkId");
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 110000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo")).equals("[DONE]")) {
                log.info(Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo"));
                isDone = true;
            }
        }
        return Services.putInputStream(url, iD + "2", checkId + "/cancel");
    }


    public static String kkm(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        log.info("Swagger done");
        String checkId = "";
        try {
            checkId = Services.PostCheckId(url, iD, filename, "checkId");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responce = "";
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 60000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo")).equals("[DONE]")) {
                responce = Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo");
                log.info(responce);
                isDone = true;
            }
        }
        return responce;
    }

    public static String kkmGetStatus(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        log.info("Swagger done");
        String checkId = "";
        try {
            checkId = Services.PostCheckId(url, iD, filename, "checkId");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 60000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId + "/status", "receiptStatus")).equals("[DONE]")) {
                isDone = true;
                result = Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId + "/status", "receiptStatus");
                log.info("Result is: " + result);
            }
        }
        return result;
    }

    public static String kkmDOC(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        log.info("Swagger done");
        String checkId = Services.PostCheckId(url, iD, filename, "checkId");
        String result = Services.getInputStream(url, iD + "1", checkId);
        log.info("Result is: " + result);
        return result;
    }

    public static String kkmReceiptCorrection(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        String correction = "correction/";
        log.info("Swagger done");
        String checkId = Services.PostCheckId(url + correction, iD, filename, "checkId");
        String result = "";
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 60000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo")).equals("[DONE]")) {
                log.info(Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo"));
                isDone = true;
                result = Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId + "/status", "receiptStatus");
            }
        }
        return result;
    }


    public static String kkmReceiptCorrectionNegative(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        String correction = "correction/";
        log.info("Swagger done");
        return Services.PostCheckId(url + correction, iD, filename, "error");
    }


    public static String kkmEmail(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        log.info("Swagger done");
        String checkId = Services.PostCheckId(url, iD, filename, "checkId");
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        String status;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 60000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            status = Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo");
            if (status.equals("[DONE]")) {
//                log.info(Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo"));
                isDone = true;
            }
            log.info(status);
        }
        String dop = "/messages/email/Zagorodskih@VSK.RU";
        pauseMethod(30000);
        return Services.getInputStream(url, iD + "11", checkId + dop);
    }


    public static String kkmEmailWrongStatus(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        log.info("Swagger done");
        String checkId = Services.PostCheckId(url, iD, filename, "checkId");
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        String status;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 60000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            status = Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo");
            if (status.equals("[DONE]")) {
                isDone = true;
            }
            log.info(status);
        }
        Services.putInputStream(url, iD + "1", checkId + "/cancel");
        String dop = "/messages/email/Zagorodskih@VSK.RU";
        pauseMethod(3000);
        return Services.ErrorStreamRequests(url, iD + "11", checkId + dop, "GET");
    }


    public static String kkmSendMessageNotFound(String environment, String iD) {
        String url = getUrl(environment);
        String dop = "messages/email/Zagorodskih@VSK.RU";
        String checkid = "00000000-0000-0000-0000-000000000000/";
        String responce = "";
        try {
            responce = Services.ErrorStreamRequests(url, iD, checkid + dop, "GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responce;
    }


    public static String kkmSendMessageWasCanceled(String environment, String iD, String filename) throws IOException {
        String url = getUrl(environment);
        String dop = "/messages/email/Zagorodskih@VSK.RU";
//        log.info("Request done");
        String checkId = Services.PostCheckId(url, iD, filename, "checkId");
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 110000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo")).equals("[DONE]")) {
                log.info(Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo"));
                isDone = true;
            }
        }
        String result = Services.putInputStream(url, iD + "2", checkId + "/cancel");
//        String result = "";
//        try {
//            result = Services.ErrorStreamRequests(url, iD, checkId + dop, "GET");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return result;
    }


    public static String kkmRepeatCancel(String environment, String iD, String filename) {
        String url = getUrl(environment);
        String response = "";
        try {
            log.info("Request done");
            String checkId = Services.PostCheckId(url, iD, filename, "checkId");
            long startMs = System.currentTimeMillis();
            boolean isDone = false;
            while (!isDone) {
                if (System.currentTimeMillis() - startMs >= 60000) {
                    throw new RuntimeException("********************EXPIRED********************");
                }
                if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo")).equals("[DONE]")) {
                    log.info(Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo"));
                    isDone = true;
                }
            }
            Services.ErrorStreamRequests(url, iD + "2", checkId + "/cancel", "PUT");
            response = Services.ErrorStreamRequests(url, iD + "3", checkId + "/cancel", "PUT");
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }


    public static String kkmCancelNotFound(String environment, String iD) throws IOException {
        String url = getUrl(environment);
        String checkid = "00000000-0000-0000-0000-000000000000";
        return Services.ErrorStreamRequests(url, iD, checkid + "/cancel", "PUT");
    }


    public static List<String> checkSentToGateway(String environment, String status) throws Exception {
        List<String> tableColons = sqlGetSRCID(environment, status);
        String srcID = tableColons.get(0);
        log.info("srcID is: " + srcID);
        List<String> checkStatusCode = sqlSentToGateway(environment, "checkStatusCode", srcID, status);
        if (checkStatusCode.get(2) != null) {
            throw new Exception("************ERROR: status code not null************");
        }
        log.info("Status is: " + checkStatusCode);
        pauseMethod(60000);
        List<String> responce = sqlSentToGateway(environment, "tableString", srcID, status);
        if (status.equals("ERROR")) {
            if (!String.valueOf(responce.get(3)).equals("VALIDATION_ERROR")) {
                throw new Exception("************ERROR: wrong fiscGateErrorCode************");
            }
        }
        pauseMethod(60000);
        responce.add(5, requestSearchForDB(environment, srcID));
        return responce;
    }


    public static List<String> checkFilials(String environment, String checkStatus, String isSentToGateway) throws Exception {
        String generatedString = RandomStringUtils.random(12, true, true);
        String srcID = "9SD0C9DZ-9289-4A09-" + generatedString;
        String filialCode = "";
        String inn = "";
        switch (checkStatus) {
            case "DONE":
                filialCode = "0031";
                inn = "7710026574";
                break;
            case "FAIL":
                filialCode = "0067";
                inn = "7725327863";
                break;
            case "NOFILIAL":
                filialCode = "NOFILIAL";
                inn = "7710026574";
        }
        sqlScriptVoid(environment, srcID, "sqlScript.sql", filialCode, inn, isSentToGateway);
        pauseMethod(110000);
        List<String> result2 = sqlNoSentToGateway(environment, srcID);
        log.info("Status: " + result2.get(2));
        log.info("fiscGateErrorCode: " + result2.get(3));
        log.info("fiscGateErrorMessage: " + result2.get(4));
        String checkID = "";
        String status = "";
        String url1 = getUrl(environment);
        switch (isSentToGateway) {
            case "YES":
                checkID = result2.get(6);
                status = Services.getReceiptStatusFromReceiptInfo(url1, String.valueOf(UUID.randomUUID()), checkID, "receiptInfo");
                switch (checkStatus) {
                    case "DONE":
                    case "NOFILIAL":
                        if (!result2.get(2).equals("DONE") && !result2.get(2).equals("SENDED")) {
                            throw new Exception("************ERROR: status code not DONE************");
                        }
                        if (result2.get(3) != null && !result2.get(3).equals("")) {
                            throw new Exception("************ERROR: fiscGateErrorCode not null************");
                        }
                        if (result2.get(4) != null && !result2.get(4).equals("")) {
                            throw new Exception("************ERROR: fiscGateErrorMessage not null************");
                        }
                        if (!status.equals("[DONE]")) {
                            throw new Exception("************ERROR: wrong status************");
                        }
                        break;
                    case "FAIL":
                        if (!result2.get(2).equals(checkStatus) && !result2.get(2).equals("SENDED")) {
                            throw new Exception("************ERROR: status code not FAIL************");
                        }
                        if (!result2.get(3).equals("BAD_REQUEST")) {
                            throw new Exception("************ERROR: wrong fiscGateErrorCode************");
                        }
                        if (!status.equals("[FAIL]")) {
                            throw new Exception("************ERROR: wrong status************");
                        }
                        break;
                }
                pauseMethod(20000);
                result2.add(7, requestSearchForDB(environment, srcID));
                break;
            case "NO":
                if (result2.get(2) != null && !result2.get(2).equals("")) {
                    throw new Exception("************ERROR: status code not NULL************");
                }
                if (result2.get(3) != null && !result2.get(3).equals("")) {
                    throw new Exception("************ERROR: fiscGateErrorCode not null************");
                }
                if (result2.get(4) != null && !result2.get(4).equals("")) {
                    throw new Exception("************ERROR: fiscGateErrorMessage not null************");
                }
                if (result2.get(6) != null && !result2.get(4).equals("")) {
                    throw new Exception("************ERROR: checkId not null************");
                }
                pauseMethod(20000);
                result2.add(7, requestSearchForDB(environment));
        }
        sqlScriptVoid(environment, srcID, "sqlUpdate.sql", filialCode, inn, isSentToGateway);
        return result2;
    }

    public static String kkmPhone(String environment, String iD, String filename, String phoneValue) throws IOException {
        String url = getUrl(environment);
        log.info("Swagger done");
        String checkId = "";
        try {
            checkId = Services.PostCheckId(url, iD, filename, "checkId", phoneValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseMethod(70000);
        String logs = requestSearchForPhone(environment, iD);
        if(phoneValue.equals("892577777")){
            assertThat(logs, containsString("phone=+70000000000"));
        }else {
            assertThat(logs, containsString("phone=+79257777777"));
        }
        String responce = "";
        long startMs = System.currentTimeMillis();
        boolean isDone = false;
        while (!isDone) {
            if (System.currentTimeMillis() - startMs >= 60000) {
                throw new RuntimeException("********************EXPIRED********************");
            }
            if ((Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo")).equals("[DONE]")) {
                responce = Services.getReceiptStatusFromReceiptInfo(url, iD + "1", checkId, "receiptInfo");
                log.info(responce);
                isDone = true;
            }
        }
        return responce;
    }

    public static String putCheckToShFI(String environment, String fileName) throws Exception {
        String messageId = String.valueOf(UUID.randomUUID());
        String result = requestPutCheckToShFI(messageId, environment, fileName);
        assertThat(result, containsString("correlationId"));
        assertThat(result, containsString("checkId"));
        assertThat(result, not(containsString("error")));
        String checkId = String.valueOf(getValuesForGivenKey("[" + result + "]", "checkId"));
        pauseMethod(100000);
        String responce = requestGetCheck(checkId.replace("[", "").replace("]", ""), environment, 200);
        return responce;
    }


    public static String getCheckNegative(String environment) {
        String responce = requestGetCheck("00000000-0000-0000-0000-000000000000/status", environment, 400);
        return responce;
    }


    public static String putCheckCorrectionNegative(String environment, String fileName) throws IOException {
        String responce = requestPutCheckCorrection("correction", environment, 400, fileName);
        return responce;
    }
}

