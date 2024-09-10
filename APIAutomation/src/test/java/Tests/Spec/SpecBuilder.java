package Tests.Spec;


import Urls.Urls;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

public class SpecBuilder {



    public static RequestSpecification reqBuilder(Map<String,String> headers){
        RequestSpecBuilder RB = new RequestSpecBuilder();
        RB.setBaseUri(Urls.baseUrl);
        RB.setBasePath(Urls.basePath);
      //  RB.setContentType(ContentType.JSON);
        RB.addHeaders(headers);
        return  RB.build();
    }

    public static RequestSpecification reqBuilder(){
        RequestSpecBuilder RB = new RequestSpecBuilder();
        RB.setBaseUri(Urls.baseUrl);
        RB.setBasePath(Urls.basePath);
        RB.setContentType(ContentType.JSON);
        return  RB.build();
    }

    public static ResponseSpecification resBuilder(){
        ResponseSpecBuilder RC = new ResponseSpecBuilder();
        RC.log(LogDetail.ALL);
        return RC.build();
    }



}
