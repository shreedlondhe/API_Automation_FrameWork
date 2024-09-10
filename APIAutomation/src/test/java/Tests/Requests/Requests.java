package Tests.Requests;
import Tests.Spec.SpecBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import java.util.Map;
public class Requests {




    public Response get(String endPoints,Map<String,String> headres){

        return  RestAssured.given().spec(SpecBuilder.reqBuilder()).headers(headres).get(endPoints);

    }
//    public ValidatableResponse get(Map<String,String> headres){
//        return RestAssured.given().spec(SpecBuilder.reqBuilder()).get()
//                .then().spec(SpecBuilder.resBuilder());
//    }
    public ValidatableResponse get(String endPoints,Map<String,String> header,String URI){
        return RestAssured.given().spec(SpecBuilder.reqBuilder()).headers(header).when().get(endPoints)
                .then().spec(SpecBuilder.resBuilder());
    }

    public ValidatableResponse post(String endPoints, JSONObject jsonObject, Map<String,String> headers){
        return  RestAssured.given().spec(SpecBuilder.reqBuilder()).headers(headers).body(jsonObject.toString())
                .post(endPoints)
                .then().spec(SpecBuilder.resBuilder());
    }
    public ValidatableResponse patch(String endPoints, JSONObject jsonObject, Map<String,String> headers){
        return  RestAssured.given().spec(SpecBuilder.reqBuilder()).headers(headers).body(jsonObject.toString())
                .patch(endPoints)
                .then().spec(SpecBuilder.resBuilder());
    }
    public void post(String endPoints,String payload){
                RestAssured.given().spec(SpecBuilder.reqBuilder()).body(payload)
                .post(endPoints)
                .then().spec(SpecBuilder.resBuilder());
    }

}
