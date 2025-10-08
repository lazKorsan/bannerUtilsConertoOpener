package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.awt.Color;

public class ApiTestWithBanner {
    @Test
    public void yatakOlusturmaApiTest() throws InterruptedException {
        // Adım 1: API çağrısı öncesi banner (mavi arka plan, 3 saniye)
        BannerUtils.showProgressStep("Hazırlık", "API endpoint'leri yükleniyor...", Color.YELLOW, 7000);
        Thread.sleep(3000);
        // API çağrısı
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"odano\": \"101\", \"yatakTipi\": \"Tek\"}")
                .post("https://api.example.com/yatak-olustur");

        // Adım 2: Response kontrolü ve banner (yeşil arka plan, 5 saniye)
        if (response.statusCode() == 200) {
            BannerUtils.showProgressStep("Yatak Oluşturma", "Başarılı! Response: " + response.jsonPath().getString("id"),
                    Color.GREEN, 5000);
        } else {
            BannerUtils.showInfoPopup("Hata", "API Hatası: " + response.statusCode(), Color.RED, 5000);
        }

        // Adım 3: Final banner (sarı arka plan, kalıcı)
        BannerUtils.showStepBanner("Test Tamamlandı", "Tüm adımlar bitti. Rapor hazır.", true, Color.YELLOW, 0);
    }
}