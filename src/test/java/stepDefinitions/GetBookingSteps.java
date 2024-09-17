package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.BookerEndpoints;
import entities.Booking;
import entities.BookingDates;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import utils.Request;

import static org.hamcrest.Matchers.not;

public class GetBookingSteps {
    Response response;

    @Given("I perform a GET call to the booking endpoint with id {string}")
    public void getBoookingById(String id){
        response = Request.getById(BookerEndpoints.GET_BOOKING, id);
    }

    @And("I verify that the status code is {int}")
    public void verifyStatusCode(int statusCode){
        response.then().assertThat().statusCode(statusCode);
    }

    @And("I verify that the body does not have size {int}")
    public void verifyResponseSize(int size){
        response.then().assertThat().body("size()", not(size));
    }


}
