package utils;

import constants.BookerEndpoints;
import entities.Booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Request {

    public static Response get(String endpoint){
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .when().get(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response getById(String endpoint, String id){
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .given().pathParam("id", id)
                .when().get(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response post(String endpoint, String payload){
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response put(String endpoint, String id, String payload){
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .and().pathParam("id",id)
                .when().put(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response delete(String endpoint, String id){
        RestAssured.baseURI = BookerEndpoints.BASE_URL;
        Response response = RestAssured
                .given().pathParam("id", id)
                .when().delete(endpoint);
        response.then().log().body();
        return response;
    }

}
