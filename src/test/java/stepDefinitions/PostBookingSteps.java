package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.BookerEndpoints;
import entities.Booking;
import entities.BookingDates;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import utils.Request;

import java.util.List;

import static org.hamcrest.Matchers.not;

public class PostBookingSteps {

    Response response;

    @And("I perform a POST call to the create endpoint with the following data")
    public void postEmployee(DataTable bookingInfo) throws JsonProcessingException {
        // data = {"Mauricio", "28", "6000"}
        List<String> data = bookingInfo.transpose().asList(String.class);
        Booking booking = new Booking();
        booking.setFirstname(data.get(0));
        booking.setLastname(data.get(1));
        booking.setTotalprice(Integer.valueOf(data.get(2)));
        booking.setDepositpaid(Boolean.valueOf(data.get(3)));
        BookingDates bkdates = new BookingDates();
        bkdates.setCheckin(data.get(4));
        bkdates.setCheckout(data.get(5));
        booking.setBookingdates(bkdates);
        booking.setAdditionalneeds(data.get(6));

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        response = Request.post(BookerEndpoints.POST_BOOKING, payload);

        // Log para verificar que tenemos una respuesta
        System.out.println("Respuesta recibida con código de estado: " + response.getStatusCode());

    }

    @And("I verify the following data in the body response")
    public void verifyEmployeeData(DataTable bookingInfo) throws JsonProcessingException {
        // data = {"Mauricio", "28", "6000"}
        List<String> data = bookingInfo.transpose().asList(String.class);
        response.then().assertThat().body("booking.firstname", Matchers.equalTo(data.get(0)));
        response.then().assertThat().body("booking.lastname", Matchers.equalTo(data.get(1)));
        response.then().assertThat().body("booking.totalprice", Matchers.equalTo(Integer.valueOf(data.get(2))));
        response.then().assertThat().body("booking.depositpaid", Matchers.equalTo(Boolean.valueOf(data.get(3))));
        response.then().assertThat().body("booking.bookingdates.checkin", Matchers.equalTo(data.get(4)));
        response.then().assertThat().body("booking.bookingdates.checkout", Matchers.equalTo(data.get(5)));
        response.then().assertThat().body("booking.additionalneeds", Matchers.equalTo(data.get(6)));

    }

    @And("I verify that the status code is {int} (post)")
    public void verifyStatusCode(int statusCode){
        response.then().assertThat().statusCode(statusCode);
    }

}
