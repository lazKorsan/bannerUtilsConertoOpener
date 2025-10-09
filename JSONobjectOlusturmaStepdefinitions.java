package cucumber.Stepdefinitions;

import io.cucumber.java.en.*;
import org.json.JSONObject;
import testNG.API_Test.AllDatas.denemeStrings.HerokuAppStrings;

public class JSONobjectOlusturmaStepdefinitions {
    JSONObject data ;


    @Given("JSONObject olustur ve verileri import et")
    public void json_object_olustur_ve_verileri_import_et() {
        JSONObject data = new JSONObject();
        data.put(HerokuAppStrings.FIRSTNAME_TITLE,"Ahmet");
        data.put(HerokuAppStrings.BODY_TITLE,"Merhaba");
        data.put(HerokuAppStrings.USER_ID_TITLE,1);
    }

    public class StepDefinitionStrings{
        // Base URLs
        public static final String BASE_URL_RESTFUL_BOOKER = "https://restful-booker.herokuapp.com";
        public static final String BASE_URL_JSON_PLACEHOLDER = "https://jsonplaceholder.typicode.com";

        // Endpoints
        public static final String ENDPOINT_BOOKING = "/booking/";
        public static final String ENDPOINT_POSTS = "/posts/";

        // IDs
        public static final String BOOKING_ID = "10";
        public static final String POST_ID = "70";

        // JSON Keys
        public static final String USER_ID = "userId";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String BODY = "body";
        public static final String DEPOSIT_PAID = "depositpaid";
        public static final String TOTAL_PRICE = "totalprice";
        public static final String FIRST_NAME = "firstname";
        public static final String LAST_NAME = "lastname";
        public static final String BOOKING_DATES = "bookingdates";
        public static final String CHECKIN = "checkin";
        public static final String CHECKOUT = "checkout";

        // Header Values
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String APPLICATION_JSON = "application/json";
    }


    static final class StepStrings {
        static final String BASE_URL_RESTFUL_BOOKER = "https://restful-booker.herokuapp.com";
        // ... diğer stringler
    }



}
