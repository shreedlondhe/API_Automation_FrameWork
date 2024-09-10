package Tests;

import Tests.util.iTestListener;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
@Listeners(iTestListener.class)
public class Test1 {


    @Test
    void m1() {
        Map<String,String> h=new HashMap<>();
        h.put("x-tenant","turtlemint");
        h.put("x-broker","turtlemint");

        String url = "https://bankfab.uat.turtlefin.com/api/minterprise/v1/token/issue";

        RequestSpecBuilder RB = new RequestSpecBuilder();
        RB.setBaseUri(url);
        RB.setBody(h);

        requestSpecification = RB.build();


        ResponseSpecBuilder Rc = new ResponseSpecBuilder();
        Rc.expectStatusCode(200);
        Rc.log(LogDetail.ALL);
        Rc.expectBody("data[0].email", equalTo("george.bluth@reqres.in"));
        ResponseSpecification re = Rc.build();

        // get("api/users?page=2").then().spec(vv);
        //   given().spec(bbb).get("api/users?page=2").then().spec(vv);

        given().get().then().spec(re);

    }

    @Test
    public void test() throws IOException {
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String filePath = "/Users/shrinivaslondhe/Desktop/APIAutomation/src/test/java/Tests/load.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        jsonObject.put("job", fakeFirstName);
        // jsonObject.getJSONArray("data").getJSONObject(0).put("email", "l@example.com");
        System.out.println("Modified JSON: " + jsonObject.toString(2));

        RestAssured.given().contentType("application/JSON").body(jsonObject.toString())

                .when().post("https://reqres.in/api/users").then().statusCode(201).log().all();


    }

    @Test
    void post() {

        HashMap<String, String> jsonContent = new HashMap<>();
        jsonContent.put("name", "name");
        jsonContent.put("job", "job");

        JSONObject jsonObject = new JSONObject(jsonContent);

        HashMap<String, String> data = new HashMap();
        data.put("name", "shrinivas");
        data.put("job", "QA Engineer");

        RestAssured.given().contentType("application/JSON").body(jsonContent)

                .when().post("https://reqres.in/api/users").then().statusCode(201).log().all();

    }


    void Atest() {

        String filePath = "/Users/shrinivaslondhe/Desktop/APIAutomation/src/test/java/Tests/exelSheet/fab.xlsx"; // Path to the existing Excel file

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            // Get the existing sheet or create a new one
            Sheet sheet = workbook.getSheet("Sheet1");
            if (sheet == null) {
                sheet = workbook.createSheet("Sheet1");
            }

            // Create a new row and put some cells in it
            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRowNum + 1);

            Cell cell1 = row.createCell(0);
            cell1.setCellValue("Jane sdfghjm");

            Cell cell2 = row.createCell(1);
            cell2.setCellValue(25);

            // Write the output to the same file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
@Test
    public static void CC() throws InterruptedException {

        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      int LENGTH = 18;
        Faker faker = new Faker();
        StringBuilder sb = new StringBuilder(LENGTH);

        while (sb.length() < LENGTH) {
            String generatedString = faker.lorem().characters(LENGTH);
            for (char c : generatedString.toCharArray()) {
                if (ALPHABET.indexOf(c) >= 0) {
                    sb.append(c);
                    if (sb.length() == LENGTH) {
                        break;
                    }
                }
            }
        }
    System.out.println(sb.toString());

    }
@Test
    public static void mm(){
    int length = 18;
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random random = new Random();
    StringBuilder randomString = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
        int index = random.nextInt(characters.length());
        randomString.append(characters.charAt(index));
    }

    System.out.println("Random String: " + randomString.toString());
    }



    }













