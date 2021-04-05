package kkm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class UnitTests extends Template {


    @BeforeEach
    public void executedBeforeEach(TestInfo testInfo) {
        log.info("Starting test: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void executedAfterEach() {
        log.info("End test\n");
    }
////
////    @Test
////    public void KKM_Services_makeAndCancelCheckCashier() throws Exception {
////        String iD = String.valueOf(UUID.randomUUID());
////        String value = Template.kkmWithCancel(environment, iD, "kkmreceipts_cashier.json");
////        assertThat(value, containsString("REST response Operation 'CancelReceipt' was complete"));
////        assertThat(value, containsString("error=null"));
////    }
//
////    @Test
////    public void KKM_Services_makeCheckNoCashier() throws Exception {
////        String iD = String.valueOf(UUID.randomUUID());
////        String value = Template.kkm(environment, iD, "kkmreceipts_nocashier.json");
////        assertThat(value, containsString("DONE"));
////    }
//
//    @Test
//    public void KKM_Services_makeCheckCashier() throws Exception {
//        String iD = String.valueOf(UUID.randomUUID());
//        String value = Template.kkm(environment, iD, "kkmreceipts_cashier.json");
//        assertThat(value, containsString("DONE"));
//    }
//
//    @Test
//    public void KKM_Services_getStatus() throws Exception {
//        String iD = String.valueOf(UUID.randomUUID());
//        String value = Template.kkmGetStatus(environment, iD, "kkmreceipts_cashier.json");
//        assertThat(value, containsString("DONE"));
//    }
//
//
////    @Test
////    public void KKM_Services_getDocCustomer() throws Exception {
////        String iD = String.valueOf(UUID.randomUUID());
////        String value = Template.kkmDOC(environment, iD, "customerDoc.json");
////        assertThat(value, containsString("customerDocSerial"));
////        assertThat(value, containsString(":"));
////        assertThat(value, containsString("1000"));
////        assertThat(value, containsString("customerDocNum"));
////        assertThat(value, containsString(":"));
////        assertThat(value, containsString("111000"));
////    }
//
//
//    @Test
//    public void KKM_Services_WAcheckStatus() throws Exception {
//        String iD = String.valueOf(UUID.randomUUID());
//        String value = Template.kkm(environment, iD, "WA.json");
//        assertThat(value, containsString("DONE"));
//    }
//
//    @Test
//    public void KKM_Services_SIPheckStatus() throws Exception {
//        String iD = String.valueOf(UUID.randomUUID());
//        String value = Template.kkm(environment, iD, "sip.json");
//        assertThat(value, containsString("DONE"));
//    }
//
//
//    @Test
//    public void KKM_Services_PostCorrectionReceipt() throws Exception {
//        String iD = String.valueOf(UUID.randomUUID());
//        String value = Template.kkmReceiptCorrection(environment, iD, "PostCorrectiom.json");
//        assertThat(value, containsString("DONE"));
//    }
//
//    @Test
//    public void KKM_Services_PostCorrectionReceipt_negative() throws Exception {
//        String iD = String.valueOf(UUID.randomUUID());
//        String value = Template.kkmReceiptCorrectionNegative(environment, iD, "PostCorrectiomNegative.json");
//        assertThat(value, containsString("REQUEST_ERROR"));
//    }
//

//
//
////    @Test
////    public void KKM_Services_CanceleNotFound() throws Exception {
////        String iD = String.valueOf(UUID.randomUUID());
////        String value = Template.kkmCancelNotFound(environment, iD);
////        assertThat(value, containsString("RECEIPT_NOTE_FOUND"));
////    }









    @Test
    public void KKM_PutShopCheckToShFI_RequiredFields() throws Exception {
        String value = Template.putCheckToShFI(environment, "PutShopCheckToShFI.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "customerPhone"));
        assertThat(value, containsString( "+70000000000"));
//        assertThat(value, containsString( "customerEmail"));
        assertThat(value, containsString( "test@gmail.com"));
        assertThat(value, containsString( "paymentType"));
        assertThat(value, containsString( "INCOME"));
    }


    @Test
    public void KKM_PutWebAutoCheckToShFI_RequiredAndOptionalFields() throws Exception {
        String value = Template.putCheckToShFI(environment, "putWebAutoCheckToShFI.json");
//        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "customerPhone"));
//        assertThat(value, containsString( "+70000000000"));
        assertThat(value, containsString( "customerEmail"));
        assertThat(value, containsString( "test@gmail.com"));
        assertThat(value, containsString( "paymentType"));
        assertThat(value, containsString( "INCOME"));
        assertThat(value, containsString( "paymentForm"));
        assertThat(value, containsString( "CASH"));
    }


    @Test
    public void KKM_PutSipCheckToShFI() throws Exception {
        String value = Template.putCheckToShFI(environment, "putSipCheckToShFI.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "customerPhone"));
        assertThat(value, containsString( "+70000000000"));
//        assertThat(value, containsString( "customerEmail"));
//        assertThat(value, containsString( "test@gmail.com"));
        assertThat(value, containsString( "paymentType"));
        assertThat(value, containsString( "INCOME"));
    }


    @Test
    public void KKM_PutCheckCorrection() throws Exception {
        String value = Template.putCheckToShFI(environment, "putCheckCorrection.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "paymentDatetime"));
        assertThat(value, containsString( "fpd"));
        assertThat(value, containsString( "fn"));
        assertThat(value, containsString( "fd"));
        assertThat(value, containsString( "DONE"));
        assertThat(value, containsString( "paymentMethod"));
    }

    @Test
    public void KKM_allPutCheckCorrection() throws Exception {
        String value = Template.putCheckToShFI(environment, "allPutCheckCorrectionToShFI.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "paymentDatetime"));
        assertThat(value, containsString( "fpd"));
        assertThat(value, containsString( "fn"));
        assertThat(value, containsString( "fd"));
        assertThat(value, containsString( "DONE"));
        assertThat(value, containsString( "paymentMethod"));
    }

    @Test
    public void KKM_Services_getStatusCheck() throws Exception {
        String value = Template.putCheckToShFI(environment, "getStatusCheck.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "customerPhone"));
        assertThat(value, containsString( "+70000000000"));
//        assertThat(value, containsString( "customerEmail"));
//        assertThat(value, containsString( "test@gmail.com"));
        assertThat(value, containsString( "paymentType"));
        assertThat(value, containsString( "INCOME"));
    }


    @Test
    public void KKM_Services_getStatusCheckNegative() {
        String value = Template.getCheckNegative(environment);
        assertThat(value, containsString( "error"));
        assertThat(value, containsString( "INTERNAL_ERROR"));
        assertThat(value, containsString( "Request with specified ID not found"));
    }

    @Test
    public void KKM_Services_putCheckCorrectionNegative() throws IOException {
        String value = Template.putCheckCorrectionNegative(environment, "negPutCheckCorrection.json");
        assertThat(value, containsString( "error"));
        assertThat(value, containsString( "REQUEST_ERROR"));
    }

    @Test
    public void KKM_Services_EmailResend() throws Exception {
        String value = Template.putCheckToShFI(environment, "getStatusCheck.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "customerPhone"));
        assertThat(value, containsString( "+70000000000"));
//        assertThat(value, containsString( "customerEmail"));
    }

    @Test
    public void KKM_Services_EmailResendStatusCheckDONE() throws Exception {
        String value = Template.putCheckToShFI(environment, "getStatusCheck.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "customerPhone"));
        assertThat(value, containsString( "+70000000000"));
//        assertThat(value, containsString( "customerEmail"));
    }


    @Test
    public void KKM_Services_EmailResendCheckNotFound() throws Exception {
        String value = Template.putCheckToShFI(environment, "getStatusCheck.json");
        assertThat(value, not(containsString( "error")));
        assertThat(value, containsString( "customerPhone"));
        assertThat(value, containsString( "+70000000000"));
//        assertThat(value, containsString( "customerEmail"));
    }

    @Test
    public void KKM_Services_SendMessageEmail() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmEmail(environment, iD, "kkmreceipts_cashier.json");
        assertThat(value, not(containsString("error")));
        assertThat(value, containsString("checkId"));
    }


    @Test
    public void KKM_Services_SendMessageNotFound() {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmSendMessageNotFound(environment, iD);
        assertThat(value, containsString("RECEIPT_NOTE_FOUND"));
    }


    @Test
    public void KKM_Services_CancelReceiptCheckWasCanceled() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmSendMessageWasCanceled(environment, iD, "kkmreceipts_cashier.json");
        assertThat(value, containsString("canceledCheckId"));
    }


    @Test
    public void KKM_Services_CancelReceiptCheckNotFound() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmCancelNotFound(environment, iD);
        assertThat(value, containsString("RECEIPT_NOTE_FOUND"));
    }



        @Test
    public void KKM_Services_CancelReceiptCheckCancelPositive() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmWithCancel(environment, iD, "kkmreceipts_cashier.json");
        assertThat(value, containsString("canceledCheckId"));
        assertThat(value, containsString("checkId"));
        assertThat(value, not(containsString("erro")));
    }


    @Test
    public void KKM_Services_SendEmailWrongStatus() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmEmailWrongStatus(environment, iD, "kkmreceipts_cashier.json");
        assertThat(value, containsString("checkId"));
        assertThat(value, containsString("error"));
        assertThat(value, containsString("CHECK_WAS_CANCELLED"));
    }

}





    class Express extends Template {
        @BeforeEach
        public void executedBeforeEach(TestInfo testInfo) {
            log.info("Starting test: " + testInfo.getDisplayName());
        }

        @AfterEach
        public void executedAfterEach() {
            log.info("End test\n");
        }

        @Test
        public void KKM_Services_makeAndCancelCheckCashier() throws Exception {
            String iD = String.valueOf(UUID.randomUUID());
            String value = Template.kkmWithCancelExpess(environment, iD, "kkmreceipts_cashier.json");
            assertThat(value, containsString("REST response Operation 'CancelReceipt' was complete"));
            assertThat(value, containsString("error=null"));
        }

}


class Phone extends Template {
    @BeforeEach
    public void executedBeforeEach(TestInfo testInfo) {
        log.info("Starting test: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void executedAfterEach() {
        log.info("End test\n");
    }

        @Test
    public void KKM_Phone1() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "79257 777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone2() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "79257777777 ");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone3() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", " 79257 777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone4() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "7925x7777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone5() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "79257777777y");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone6() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "z79257a777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone7() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "7 9257777777y");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone8() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "++79257777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone9() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "79257+777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone10() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "79257777777+");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone11() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "79257777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone12() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "9257777777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone13() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "8 925 777 7777");
        assertThat(value, containsString( "DONE"));
    }

    @Test
    public void KKM_Phone14() throws Exception {
        String iD = String.valueOf(UUID.randomUUID());
        String value = Template.kkmPhone(environment, iD, "noCashierPhone.json", "892577777");
        assertThat(value, containsString( "DONE"));
    }


}


 class DB extends Template {
        @BeforeEach
        public void executedBeforeEach(TestInfo testInfo) {
            log.info("Starting test: " + testInfo.getDisplayName());
        }

        @AfterEach
        public void executedAfterEach() {
            log.info("End test\n");
        }

     @Test
     public void KKM_DB_NoSentToGateway_Error() throws Exception {
         List<String> answerValue = checkSentToGateway(environment, "ERROR");
         assertThat(answerValue.get(5), containsString(answerValue.get(0)));
         assertThat(answerValue.get(5), containsString(answerValue.get(1)));
         assertThat(answerValue.get(5), containsString(answerValue.get(4)));
     }


     @Test
     public void KKM_DB_SentToGatewayWithStubs_DONE() throws Exception {
         List<String> answerValue = checkSentToGateway(environment, "DONE");
         assertThat(answerValue.get(5), containsString(answerValue.get(0)));
         assertThat(answerValue.get(5), containsString(answerValue.get(1)));
         assertThat(answerValue.get(5), containsString(answerValue.get(4)));
         assertThat(answerValue.get(5), containsString("ReadyForSendingReceiptKeys selected"));
     }

     @Test
     public void KKM_DB_SentToGateway_UTC12_DONE() throws Exception {
         List<String> answerValue = checkFilials(environment, "DONE", "YES");
         assertThat(answerValue.get(7), containsString(answerValue.get(0)));
         assertThat(answerValue.get(7), containsString(answerValue.get(1)));
         assertThat(answerValue.get(7), containsString(answerValue.get(5)));
     }


     @Test
     public void KKM_DB_SentToGateway_UTC2_FAIL() throws Exception {
         List<String> answerValue = checkFilials(environment, "FAIL", "YES");
         assertThat(answerValue.get(7), containsString(answerValue.get(0)));
         assertThat(answerValue.get(7), containsString(answerValue.get(1)));
         assertThat(answerValue.get(7), containsString(answerValue.get(5)));
     }


     @Test
     public void KKM_DB_SentToGateway_NOFILIAL() throws Exception {
         List<String> answerValue = checkFilials(environment, "NOFILIAL", "YES");
         assertThat(answerValue.get(7), containsString(answerValue.get(0)));
         assertThat(answerValue.get(7), containsString(answerValue.get(1)));
         assertThat(answerValue.get(7), containsString(answerValue.get(5)));
     }


     @Test
     public void KKM_DB_NoSentToGateway_UTC12_DONE() throws Exception {
         List<String> answerValue = checkFilials(environment, "DONE", "NO");
         assertThat(answerValue.get(7), not(containsString("ReadyForSendingReceiptKeys selected")));
     }


     @Test
     public void KKM_DB_NoSentToGateway_UTC2_FAIL() throws Exception {
         List<String> answerValue = checkFilials(environment, "FAIL", "NO");
         assertThat(answerValue.get(7), not(containsString("ReadyForSendingReceiptKeys selected")));
     }


     @Test
     public void KKM_DB_NoSentToGateway_NOFILIAL() throws Exception {
         List<String> answerValue = checkFilials(environment, "NOFILIAL", "NO");
         assertThat(answerValue.get(7), not(containsString("ReadyForSendingReceiptKeys selected")));
     }

}
