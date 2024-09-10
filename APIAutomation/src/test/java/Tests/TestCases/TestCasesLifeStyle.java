package Tests.TestCases;

import DynamicVariables.DynamicVarialbles;
import Tests.Requests.Requests;
import Tests.exelSheet.AddToExcel;
import Tests.util.iTestListener;
import Urls.Urls;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Listeners(iTestListener.class)
public class TestCasesLifeStyle {
    Requests Requests;

    @BeforeMethod
    public void setUp() {
        Requests = new Requests();
    }

    @Test(priority = 1)
    public void getTocanLifeStyle() {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");

        DynamicVarialbles.tocan = Requests.get(Urls.tocanEndPoint, headers).then().log().all().statusCode(200)
                .extract().response().jsonPath().getString("data.accessToken");
        System.out.println("request tocan is : " + DynamicVarialbles.tocan);

    }

    @Test(priority = 2)
    void createQuoteLifeStyle() throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("xxxxxxx", DynamicVarialbles.tocan);

        Response Responce = Requests.post(Urls.createQuote, DynamicVarialbles.craeteQuotePayload(), headers).extract().response();
        Responce.then().log().all();
        DynamicVarialbles.referenceId = Responce.jsonPath().getString("data.referenceId");
        System.out.println("referenceId is : " + DynamicVarialbles.referenceId);
        DynamicVarialbles.fetchQuoteLinks = Responce.jsonPath().getString("data.fetchQuoteLinks[0].link");
        System.out.println("fetchQuoteLinks is : " + DynamicVarialbles.fetchQuoteLinks);
    }

    @Test(priority = 3)
    void premiumResultIdLifeStyle() throws IOException {

        DynamicVarialbles.getPremiumResultId();
        Map<String, String> headers = new HashMap<>();
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
        Response Responce = Requests.get(Urls.premiumResultId, headers, DynamicVarialbles.fetchQuoteLinks)
                .extract().response();
        DynamicVarialbles.premiumResultId = Responce.jsonPath().getString("data.premiumResultId");
        Assert.assertEquals(Responce.jsonPath().getString("meta.status"),"SUCCESS");

    }

    @Test(priority = 4)
    void createProposalLifeStyle() throws IOException {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
        Response Responce = Requests.post(Urls.proposal, DynamicVarialbles.craeteProposalPayload(), headers).extract().response();
        DynamicVarialbles.proposalId = Responce.jsonPath().getString("data.proposalId");
        Assert.assertEquals(Responce.jsonPath().getString("meta.status"),"SUCCESS");



    }

    @Test(priority = 5)
    void transactionFlowLifeStyle() throws IOException {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
        Response Responce = Requests.post(Urls.transaction, DynamicVarialbles.craeteTransactionPayload(), headers).extract().response();
        DynamicVarialbles.IDforRenewal = Responce.jsonPath().getString("data.referenceId");
      //  AddToExcel.addIdToSheet();
    }
     @Test(priority = 6)
    void renewal() throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
         Response Responce = Requests.patch(Urls.renewal, DynamicVarialbles.craeteRenewalPayload(), headers).extract().response();
         DynamicVarialbles.newReferenceId = Responce.jsonPath().getString("data.referenceId");
         DynamicVarialbles.newProposalId = Responce.jsonPath().getString("data.proposalId");
         Assert.assertEquals(Responce.jsonPath().getString("meta.status"),"SUCCESS");
         System.out.println("previousReferenceId is = "+Responce.jsonPath().getString("data.previousReferenceId"));
         System.out.println("parentReferenceId is = "+Responce.jsonPath().getString("data.parentReferenceId") );
         System.out.println("referenceId is = "+DynamicVarialbles.newReferenceId );
         System.out.println("referenceId is = "+DynamicVarialbles.newProposalId );
    }

   @Test(priority = 7)
    void transactionFlowForRenewalIDLifeStyle() throws IOException {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
        Response Responce = Requests.post(Urls.transaction, DynamicVarialbles.craeteRenewalTransactionPayload(), headers).extract().response();
        DynamicVarialbles.IDforCancel = Responce.jsonPath().getString("data.policyNumber");
       Assert.assertEquals(Responce.jsonPath().getString("meta.status"),"SUCCESS");
       // AddToExcel.addIdToSheet();
    }



   @Test(priority = 8)
    void cancellationLifeStyle() throws IOException {

        Map<String,String> headers=new HashMap<>();
        headers.put("xxxxxxx","xxxxxxx");
        headers.put("xxxxxxx","xxxxxxx");
        headers.put("xxxxxxx",DynamicVarialbles.tocan);
       Response Responce=Requests.post(Urls.cancel,DynamicVarialbles.craeteCancellationPayload(),headers).statusCode(200).extract().response();
       Assert.assertEquals(Responce.jsonPath().getString("meta.status"),"SUCCESS");

    }

}