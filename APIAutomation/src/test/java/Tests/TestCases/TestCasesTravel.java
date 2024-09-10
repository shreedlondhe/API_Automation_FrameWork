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
public class TestCasesTravel {

    Requests Requests;

    @BeforeMethod
    public void setUp() {
        Requests = new Requests();
    }
    @Test(priority = 1)
    public void getTocanTravel() {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");

        DynamicVarialbles.tocan = Requests.get(Urls.tocanEndPoint, headers).then().log().all().statusCode(200)
                .extract().response().jsonPath().getString("data.accessToken");
        System.out.println("request tocan is : " + DynamicVarialbles.tocan);

    }
    @Test(priority = 2)
    void createQuoteTravel() throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-tenant", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
        Response Responce = Requests.post(Urls.createQuoteTravel, DynamicVarialbles.craeteQuotePayloadTravel(), headers).extract().response();
        Responce.then().log().all();
        DynamicVarialbles.referenceId = Responce.jsonPath().getString("data.referenceId");
        System.out.println("referenceId is : " + DynamicVarialbles.referenceId);
        DynamicVarialbles.fetchQuoteLinks = Responce.jsonPath().getString("data.fetchQuoteLinks[0].link");
        System.out.println("fetchQuoteLinks is : " + DynamicVarialbles.fetchQuoteLinks);

    }
    @Test(priority = 3)
    void premiumResultIdTravel() throws IOException {

        DynamicVarialbles.getPremiumResultId();
        Map<String, String> headers = new HashMap<>();
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
        Response Responce = Requests.get(Urls.premiumResultId, headers, DynamicVarialbles.fetchQuoteLinks)
                .extract().response();
        DynamicVarialbles.premiumResultId = Responce.jsonPath().getString("data.premiumResultId");

    }
    @Test(priority = 4)
    void createProposalTravel() throws IOException {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("Authorization", DynamicVarialbles.tocan);
        Response Responce = Requests.post(Urls.proposalTravel, DynamicVarialbles.craeteProposalTravelPayload(), headers).extract().response();
        DynamicVarialbles.proposalId = Responce.jsonPath().getString("data.proposalId");
        System.out.println("proposal id = " + DynamicVarialbles.proposalId);


    }
    @Test(priority = 5)
    void transactionFlowTravel() throws IOException {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("x-xxxxxxx", "xxxxxxx");
        headers.put("xxxxxxx", DynamicVarialbles.tocan);
        Response Responce = Requests.post(Urls.transactionTravel, DynamicVarialbles.craeteTransactionTravelPayload(), headers).extract().response();
        Responce.then().statusCode(200);
        DynamicVarialbles.policyNumber = Responce.jsonPath().getString("data.fabSerialNumber");
        AddToExcel.addIdToSheet();
    }
    @Test(priority = 6)
    void cancellationTravel() throws IOException {

        Map<String,String> headers=new HashMap<>();
        headers.put("xxxxxxx","xxxxxxx");
        headers.put("xxxxxxx","xxxxxxx");
        headers.put("xxxxxxx",DynamicVarialbles.tocan);
        Response Responce=Requests.post(Urls.cancelTravel,DynamicVarialbles.craeteCancellationTravelPayload(),headers).statusCode(200).extract().response();
        Assert.assertEquals(Responce.jsonPath().getString("meta.status"),"SUCCESS");
    }
}
