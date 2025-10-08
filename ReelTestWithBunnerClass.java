package testNG.API_Test.LineerTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BannerWindow; // 🔹 Banner sınıfını import ettik

import static io.restassured.RestAssured.given;

public class ReelTestWithBunnerClass {

    @Test
    public void TC_06() throws InterruptedException {

        String url = "https://restful-booker.herokuapp.com/booking";

        // 🔹 Adım 1: Banner - Test Başlatılıyor
        BannerWindow.info("Rezervasyon Oluşturma Testi Başlıyor...");
        Thread.sleep(6000);
        BannerWindow.step("API bağlantısı hazırlanıyor...");
        Thread.sleep(6000);

        // Request Body oluşturma
        JSONObject innerData = new JSONObject();
        innerData.put("checkin", "2025-09-01");
        innerData.put("checkout", "2025-09-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname", "Selami");
        reqBody.put("lastname", "Hasman");
        reqBody.put("totalprice", 450);
        reqBody.put("depositpaid", true);
        reqBody.put("additionalneeds", "wi-fi");
        reqBody.put("bookingdates", innerData);

        System.out.println("=== GÖNDERİLEN REQUEST BODY ===");
        System.out.println(reqBody.toString(2));

        // 🔹 Adım 2: Banner - İstek gönderiliyor
        BannerWindow.step("POST isteği gönderiliyor → " + url);
        Thread.sleep(6000);


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post(url);

        // 🔹 Adım 3: Banner - Response alındı
        BannerWindow.info("API yanıtı alındı. Response işleniyor...");
        Thread.sleep(6000);


        System.out.println("=== GELEN RESPONSE ===");
        response.prettyPrint();

        // Response'dan gelen değerleri al
        String actualFirstName = response.jsonPath().getString("booking.firstname");
        String actualLastName = response.jsonPath().getString("booking.lastname");
        int actualTotalPrice = response.jsonPath().getInt("booking.totalprice");
        boolean actualDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");
        String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
        String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
        String actualAdditionalNeeds = response.jsonPath().getString("booking.additionalneeds");

        // 🔹 Adım 4: Banner - Doğrulamalar başlıyor
        BannerWindow.step("Response doğrulaması yapılıyor...");
        Thread.sleep(600);

        // Assertion'ları yap ve actual değerleri göster
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
            Assert.assertEquals(actualFirstName, "Selami");
            Assert.assertEquals(actualLastName, "Hasman");
            Assert.assertEquals(actualTotalPrice, 450);
            Assert.assertEquals(actualDepositPaid, true);
            Assert.assertEquals(actualCheckin, "2025-09-01");
            Assert.assertEquals(actualCheckout, "2025-09-10");
            Assert.assertEquals(actualAdditionalNeeds, "wi-fi");

            System.out.println("=== TÜM TESTLER BAŞARIYLA TAMAMLANDI ===");

            // 🔹 Adım 5: Banner - Başarılı
            BannerWindow.success("Tüm doğrulamalar başarılı ✅");
            Thread.sleep(6000);
            BannerWindow.info("Test başarıyla tamamlandı!");
            Thread.sleep(6000);


        } catch (AssertionError e) {
            // 🔹 Adım 6: Banner - Hata durumu
            BannerWindow.error("Doğrulama hatası: " + e.getMessage());
            throw e; // TestNG’ye hatayı fırlat
        }
    }
}
