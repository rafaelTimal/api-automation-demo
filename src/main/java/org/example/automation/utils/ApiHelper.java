package org.example.automation.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiHelper {
    private String baseUri;
    private String token;

    public ApiHelper(String baseUri){
        this.baseUri=baseUri;
        RestAssured.baseURI = this.baseUri;
    }

    public void setToken(String token){
        this.token= token;
    }

    private RequestSpecification getBaseRequest(){
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).log().all();

        if(null!=this.token){
            request.header("Authorization", String.format("Bearer %s", this.token));
        }

        return request;
    }

    public Response get(String endpoint){
        return getBaseRequest()
                .when().get(endpoint)
                .then().log().all().extract().response();
    }

    public Response post(String endpoint, Object body){
        return this.getBaseRequest().body(body).when().post(endpoint).then().log().all().extract().response();
    }

    public Response put(String endpoint, Object body){
        return this.getBaseRequest().body(body).when().put(endpoint).then().log().all().extract().response();
    }

    public Response patch(String endpoint, Object body){
        return this.getBaseRequest().body(body).when().patch(endpoint).then().log().all().extract().response();
    }


    public Response delete(String endpoint){
        return this.getBaseRequest().when().delete(endpoint).then().log().all().extract().response();
    }

}
