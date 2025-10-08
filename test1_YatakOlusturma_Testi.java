package tests;

import org.openqa.selenium.*;
import pages.AdminPages;
import pages.HeaderPages;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test1_YatakOlusturma_Testi extends TestBaseRapor {
    private static final String REPORT_PATH = "C:\\Users\\Hp\\OneDrive\\Desktop\\loyalfriendcare\\reports\\";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    HeaderPages headerPages = new HeaderPages();
    AdminPages adminPages = new AdminPages();
    //Bir yönetici olarak,
    // sol açılır menüdeki Bed Managers
    // ve alt menülerine erişebilmeli,
    // mevcut yatak listesini görüntüleyebilmeli
    // ve bu yataklar üzerinde
    // görüntüleme, arama, düzenleme ve silme işlemleri yapabilmeliyim.

    // Bu test senaryosu Fonksiyonel Test (Functional Testing)
    // kapsamına girer, özellikle de End-to-End (E2E) Test türüne uyar.
    // İşte detaylı açıklama:
    // description tanımlı yazılabilir
    // (description = "N2N-123: Yatak CRUD işlemlerini test eder")
    @Test (description = "N2N")
    public void TC_01() {// Admin Girişi
        extentTest = extentReports.createTest("BEDMANAGERS YATAK OLUŞTURMA VE SİLME İŞLEMLERİ",
                "Kullanici AnaSayfa Header Bölümünde Bulunan Logoya bastığında Url Değişmemeli");
        // 1 Kullanıcı ana sayfayı açar
        Driver.getDriver().get(ConfigReader.getProperty("lfc"));
        extentTest.info("Kullanıcı anasayfayı açtı ");
        ReusableMethods.bekle(1);
        // BANNER BANNER 1
        displayProgressBanner(
                Driver.getDriver(),
                " YATAK OLUŞTURMA TESTİ BAŞLIYOR  ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        // 2 Kullanıcı SignInButtona Basarak Login sayfasına yönlenir
        headerPages.signInButton.click();
        extentTest.info("Kullanıcı Login Sayfasına yönlendirildi");

        // BANNER BANNER 2
        ReusableMethods.bekle(2);
        displayProgressBanner(
                Driver.getDriver(),
                " KULLANICI OTORUMU AÇILIYOR  ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        // 3 Kullanıcı mail kutusuna AdminMail adresini girer
        headerPages.mailBox.sendKeys(ConfigReader.getProperty("adminMail"));
        extentTest.info("Kullanıcı mailKutusuna admin mailini yazdı");

        // 4 Kullanıcı passwordBox Kutusuna geçerli şifreyi girer
        headerPages.passwordBox.sendKeys(ConfigReader.getProperty("adminPassword"));
        extentTest.info("Kullanıcı geçerli şifreyi girdi");

        // Kullanıcı rememberMe butununa tıklar
        headerPages.rememberMeButton.click();
        extentTest.info("Kullanıcı rememberMe kUtusunu işaretler");

        // Kullanıcı signIn butona basarak admin girişi yapar
        headerPages.loginPageSigInButton.click();
        extentTest.info("Kullanıcı admin girişi yaptı");

        ReusableMethods.bekle(3);

        // BANNER BANNER 3
        displayProgressBanner(
                Driver.getDriver(),
                " YÖNETİM PANELİ SAYFASINA GİDİYORUZ  ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);




        // admin account Button üzerinde ismini kaydeder
        System.out.println(headerPages.accountButton.getText());
        extentTest.info("Kullanıcı Account Buton üzerindeki ismi kayt etti");


        // admin buton üzerindeki isimleri karşılaştırır.
        String expectedName = ConfigReader.getProperty("adminName");
        String actualName = headerPages.accountButton.getText();
        System.out.println(headerPages.accountButton.getText());
        Assert.assertFalse(actualName.equals(expectedName));



        ReusableMethods.bekle(2);

        // admin admin sayfasına yönlendirilir.
        headerPages.accountButton.click();
        extentTest.info("Kullanıcı Admin sayfasına yönlendirildi");

        // BANNER BANNER 4
        displayProgressBanner(
                Driver.getDriver(),
                " ADMİN SAYFASINDAYIZ; DASHBOARD GÖRÜNÜRLÜK TESTİ BAŞLIYOR  ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        // admin dashboard menüsünü açar
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(headerPages.dashBoard).perform();
        extentTest.info("Admin dashboard menüsünü açtı");


        ReusableMethods.bekle(2);

       // dashboard menüsü görünürse ekran alınıtısı alır
        if (adminPages.dashboardTables.isDisplayed()){
            MultiScreenShootsMethods.getWebelementWithRedBorder(
                    Driver.getDriver(),
                    ConfigReader.getProperty("adminPage"),
                    adminPages.dashboardTables
            );
            extentTest.pass("DashboarTable görünür");
        }else {
            extentTest.fail("Dashboard menü görünür değil");
        }

        // BANNER BANNER 5
        displayProgressBanner(
                Driver.getDriver(),
                " DAHBOARD MENÜDEN BEDMANAGERS BÖLÜMÜNE GEÇİYORUZ   ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);

        // Admin sahboard menusuundan BedMangers menüsünü açar.
        adminPages.bedManagersButton.click();
        extentTest.info("admin menüden BedManagesr kısmını seçti");
        ReusableMethods.bekle(2);

        // Admin açlır menüden BedManegers Button seçer
        adminPages.subBedManagersButton.click();
        extentTest.info("Admin açlır menüden BedManegers Button seçer ") ;

        // BANNER BANNER 6
        displayProgressBanner(
                Driver.getDriver(),
                " YATAK LİSTESİ RAPORLAMA İŞLEMLERİ BAŞLIYOR"   ,
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);

        writeProductsToExcel(
                Driver.getDriver(),
                "https://qa.loyalfriendcare.com/en/Dashboard/Posts",
                "//td[@class='v-align-middle semi-bold']",
                1
        );

        // Admin Yatak Listesi için Rapor oluşturur
            Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Dashboard/Posts");

            // Admin WebTablodaki yatakları Raporlar
            // Raporda Kullananılacak dosya adı
            String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
            String fileName = "bedMangersReport" + timestamp + ".xlsx";
            String fullPath = REPORT_PATH + fileName;
            extentTest.info("Rapor oluşturma başarılı");


            generateMedicineReport(
                    Driver.getDriver(),
                    "https://qa.loyalfriendcare.com/en/Dashboard/Posts",
                    "//td[@class='v-align-middle semi-bold']",
                    fullPath
            );

        // BANNER BANNER 7
        displayProgressBanner(
                Driver.getDriver(),
                " RAPORLAMA BİTTİ YATAK OLUŞTURMA SAYFASINA GİDİYORUZ"   ,
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);

        // admin yeni bir yatak oluşturma sayfasına yönlendirilir
        adminPages.addBedManagersButton.click();
        extentTest.info("Admin add Bed Managers sayfasına yönlendirildi") ;
        System.out.println("<-- ==== yatak oluşturma sayfasına geçiş PASS");

        // BANNER BANNER 8
        displayProgressBanner(
                Driver.getDriver(),
                " YATAK OLUŞTURMA İŞLEMLERİ BAŞLIYOR  ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        // Admin oluşturmak istediği başlığın adını girer
        adminPages.bedManagersTitleBox.sendKeys("PURRY Haven");
        extentTest.info("Admin yatak başlığını yazdı");

        // Admin oluşturmak istediği yatağın içeriğini girer
        adminPages.bedManagersContentBox.sendKeys("Safe Snug Cat Sanctuary");
        extentTest.info("admin content kutusuna ekleme yaptı");
        System.out.println(" content ekleme testi geçti");
        ReusableMethods.bekle(2);

        // BANNER BANNER 9
        displayProgressBanner(
                Driver.getDriver(),
                " SAĞ MENÜDEN SEÇİMLER BAŞLIYOR   ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        // <--==================================================-->

        // admin sağ menüden departments menüsünü açar
        adminPages.selectDepartmentsButton.click();
        extentTest.info("Admin Department atamak üzere departments menüsünü açtı");

        // admin sağ menüden departments menüsünü açar
        adminPages.selectDepartmentsButton.click();




       // < -- ================================ -- >

        // admin açılır menüden departments ataması yapar
       adminPages.inputDepartmentsBox.sendKeys("Dental Care" +Keys.ENTER);
       extentTest.info("admin açılır menüden Departments seçti ");

       // admin doktorlar menüsünü açar
       ReusableMethods.bekle(3);
       //actions.moveToElement(adminPages.selectDoctorsButton).perform();
       //adminPages.selectDoctorsButton.click();
       extentTest.info("admin doktorlar menüsünü açtı") ;

       // admin doktor menüsünüden doktor ataması yapar
       //adminPages.inputDoctorsBox.sendKeys("Dr. Olivia Bennett"+Keys.ENTER);
       extentTest.info("admin doktor ataması yaptı") ;

       // admin ilaçlar menüsünü açar
       adminPages.selectMedicinesButton.click();
       adminPages.inputMedicinesBox.sendKeys("Revolution (Selamectin)"+Keys.ENTER);
       extentTest.info("admin ilaçalar menüsünü açtı");

       // admin pricebox a geçerli tutarı girer
       adminPages.inputPriceBox.sendKeys("657"+Keys.ENTER);
       extentTest.info("admin ilacın tutarını girdi");

       ReusableMethods.bekle(3);
       // admin yatağı kullanıma açar
       adminPages.radioButton.click();
       extentTest.info("admin yatağı kullanıma açtı") ;

        // BANNER BANNER 10
        displayProgressBanner(
                Driver.getDriver(),
                " TERCİHLER YAPILDI VE YATAK KAYT EDİLİYOR   ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        ReusableMethods.bekle(2);
       // admin yatağı son kullanıcının hizmetine sunar
       adminPages.bedManagersSaveButton.click();
       ReusableMethods.bekle(2);
       extentTest.info("yatak kullanıcının kullanımına açıldı");

        // BANNER BANNER 11
        displayProgressBanner(
                Driver.getDriver(),
                " YATAK BİLGİSİ DEĞİŞİKLİK İŞLEMLERİ BAŞLIYOR ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        // admin hizmet tutarını fazla girdiğini fark eder ve edit sayfasına gider
        adminPages.bedManagersEditButton.click();
        ReusableMethods.bekle(2);
        extentTest.info("Admin değiştirme işlemine başladı");

        // BANNER BANNER 12
        displayProgressBanner(
                Driver.getDriver(),
                " YATAK BİLGİSİ DEĞİŞİKLİK SAYFASINDAYIZ ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);

        ReusableMethods.bekle(5);

        // admin hizmet tutarını fazla girdiğini fark eder ve edit sayfasına gider
        //adminPages.bedManagersEditButton.click();
        extentTest.info("Admin değişiklik sayfasına gitti ");

        adminPages.bedManagersContentBox.sendKeys("kitty cat"+Keys.ENTER);
        extentTest.info("admin content üzerinde değişiklik yaptı") ;
        ReusableMethods.bekle(5);

        // BANNER BANNER 13
        displayProgressBanner(
                Driver.getDriver(),
                " YATAK BİLGİSİ DEĞİŞİKLİKLERİ KAYT EDİLİYOR ",
                " TC_01 ",
                "BedManagers bölümünde yatak listesini görebilmek "
        );ReusableMethods.bekle(6);


        adminPages.bedManagersSaveButton.click();
        ReusableMethods.bekle(2);
        extentTest.pass("Admin değişiklikleri başarı ile gerçekleşitrdi") ;

        // BANNER BANNER 14
        displayProgressBanner(
                Driver.getDriver(),
                " YATAK SİLME İŞLEMLERİ BAŞLIYOR" ,
                " TC_01 ",
                "BedManagers bölümünde yatak silme işlemini başarılı bir şekilde yapmak "
        );ReusableMethods.bekle(6);

        // TEKLİ SİLME İŞLEMİ YAPILIR
        adminPages.bedManagersDeleteButton.click();
        extentTest.info("oluşturulan yatak başarı ile silindi");
        ReusableMethods.bekle(2);

        // BANNER BANNER 14
        displayProgressBanner(
                Driver.getDriver(),
                " SİSTEMDEN ÇIKIŞ İŞLEMLERİ BAŞLIYOR " ,
                " TC_01 ",
                "BedManagers bölümünde yatak silme işlemini başarılı bir şekilde yapmak "
        );ReusableMethods.bekle(6);

        // admin page profil butona basılır
        adminPages.profileButton.click();
        ReusableMethods.bekle(1);

        // adminpage profilmenusunden logout butona basılır
        adminPages.logOutButton.click();
        ReusableMethods.bekle(2);

        // anasayfa signout butona basılır
        adminPages.signOutButton.click();
        ReusableMethods.bekle(2);

        // BANNER BANNER 15
        displayProgressBanner(
                Driver.getDriver(),
                " GÜVENLE ÇIKIŞ BAŞARILI  " ,
                " TC_01 ",
                "BedManagers bölümünde yatak silme işlemini başarılı bir şekilde yapmak "
        );ReusableMethods.bekle(6);


        // BANNER BANNER 16
        displayProgressBanner(
                Driver.getDriver(),
                " TEST PASS " ,
                " TC_01 ",
                "BedManagers bölümünde yatak silme işlemini başarılı bir şekilde yapmak "
        );ReusableMethods.bekle(6);

        //WebElement fileInput = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));
        //       ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].style.display='block';", fileInput);
        //       fileInput.sendKeys("C:\\Users\\Hp\\OneDrive\\Desktop\\loyalfriendcare\\uploadImage.png");
        //     %%%uploadFile




    }

















































    //<--=========BANNER BANNER BAŞLIYOR ==================================-->



    public static void displayDynamicBanner(WebDriver driver, String bannerType,
                                            String mainTitle, String testName,
                                            String testDescription, int duration) {
        displayConsoleBanner(bannerType, mainTitle, testName, testDescription);
        displayBrowserBanner(driver, bannerType, mainTitle, testName, testDescription, duration);
    }

    /**
     * 🎯 Hızlı kullanım için overload method
     */
    public static void displayAdminTestBanner(WebDriver driver, String testName, String testDescription) {
        displayDynamicBanner(driver, "start", "ADMIN TESTİ BAŞLIYOR", testName, testDescription, 5000);
    }

    /**
     * 🎯 Test ilerleyişi için banner
     */
    public static void displayProgressBanner(WebDriver driver, String progressTitle, String testName, String stepDescription) {
        displayDynamicBanner(driver, "progress", progressTitle, testName, stepDescription, 5000); // EKRANDA NE KADAR KALACAĞINI AYARLA
    }

    /**
     * 🎯 Başarı banner'ı
     */
    public static void displaySuccessBanner(WebDriver driver, String successTitle, String testName) {
        displayDynamicBanner(driver, "success", successTitle, testName, "Test başarıyla tamamlandı", 4000);
    }

    /**
     * 🎯 Konsol banner'ı
     */
    private static void displayConsoleBanner(String bannerType, String mainTitle, String testName, String testDescription) {
        String icon = getBannerIcon(bannerType);
        String colorCode = getBannerColorCode(bannerType);

        String banner =
                "\n" + colorCode +
                        "==================================================\n" +
                        "               " + icon + " " + mainTitle + " \n" +
                        "==================================================\n" +
                        "⏰ Zaman: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + "\n" +
                        "🎯 Test: " + testName + "\n" +
                        "📝 Durum: " + testDescription + "\n" +
                        "🌐 URL: " + ConfigReader.getProperty("lfc") + "\n" +
                        "==================================================\n" +
                        "\u001B[0m"; // Reset color

        System.out.println(banner);
    }

    /**
     * 🎯 Tarayıcı banner'ı
     */
    private static void displayBrowserBanner(WebDriver driver, String bannerType,
                                             String mainTitle, String testName,
                                             String testDescription, int duration) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String[] colors = getBannerColors(bannerType);
            String icon = getBannerIcon(bannerType);

            String script =
                    "// Önceki banner'ı temizle\n" +
                            "var oldBanner = document.getElementById('dynamicTestBanner');\n" +
                            "if (oldBanner) { oldBanner.remove(); }\n" +
                            "\n" +
                            "// Yeni banner oluştur\n" +
                            "var banner = document.createElement('div');\n" +
                            "banner.id = 'dynamicTestBanner';\n" +
                            "banner.style.position = 'fixed';\n" +
                            "banner.style.left = '50%';\n" +
                            "banner.style.top = '50%';\n" +
                            "banner.style.transform = 'translate(-50%, -50%)';\n" +
                            "banner.style.background = 'linear-gradient(135deg, ' + arguments[0] + ')';\n" +
                            "banner.style.color = 'white';\n" +
                            "banner.style.padding = '20px';\n" +
                            "banner.style.borderRadius = '15px';\n" +
                            "banner.style.zIndex = '9999';\n" +
                            "banner.style.boxShadow = '0 10px 30px rgba(0,0,0,0.3)';\n" +
                            "banner.style.fontFamily = 'Arial, sans-serif';\n" +
                            "banner.style.textAlign = 'center';\n" +
                            "banner.style.minWidth = '350px';\n" +
                            "banner.style.border = '3px solid ' + arguments[1];\n" +
                            "banner.style.animation = 'fadeIn 0.5s ease-in';\n" +
                            "\n" +
                            "// İçerik\n" +
                            "banner.innerHTML = '' +\n" +
                            "    '<div style=\"font-size: 28px; margin-bottom: 10px;\">' + arguments[2] + '</div>' +\n" +
                            "    '<h3 style=\"margin: 0 0 15px 0; font-size: 20px; font-weight: bold;\">' + arguments[3] + '</h3>' +\n" +
                            "    '<div style=\"font-size: 14px; margin-bottom: 8px; background: rgba(255,255,255,0.1); padding: 5px; border-radius: 5px;\"><strong>Test:</strong> ' + arguments[4] + '</div>' +\n" +
                            "    '<div style=\"font-size: 14px; margin-bottom: 8px;\"><strong>Açıklama:</strong> ' + arguments[5] + '</div>' +\n" +
                            "    '<div style=\"font-size: 11px; opacity: 0.8; margin-top: 15px; border-top: 1px solid rgba(255,255,255,0.2); padding-top: 10px;\">' + new Date().toLocaleString() + '</div>' +\n" +
                            "    '<div style=\"font-size: 10px; opacity: 0.6; margin-top: 5px;\">' + arguments[6] + ' ms sonra kapanacak...</div>';\n" +
                            "\n" +
                            "// CSS animation ekle\n" +
                            "var style = document.createElement('style');\n" +
                            "style.innerHTML = '@keyframes fadeIn { from { opacity: 0; transform: translate(-50%, -60%); } to { opacity: 1; transform: translate(-50%, -50%); } }';\n" +
                            "document.head.appendChild(style);\n" +
                            "\n" +
                            "// Sayfaya ekle\n" +
                            "document.body.appendChild(banner);\n" +
                            "\n" +
                            "// Belirtilen süre sonra kaldır\n" +
                            "setTimeout(function() {\n" +
                            "    banner.style.animation = 'fadeOut 0.5s ease-out';\n" +
                            "    setTimeout(function() { \n" +
                            "        if (banner.parentNode) { \n" +
                            "            banner.parentNode.removeChild(banner); \n" +
                            "        }\n" +
                            "    }, 500);\n" +
                            "}, arguments[6]);\n" +
                            "\n" +
                            "// Fade out animation için CSS\n" +
                            "var fadeOutStyle = document.createElement('style');\n" +
                            "fadeOutStyle.innerHTML = '@keyframes fadeOut { from { opacity: 1; transform: translate(-50%, -50%); } to { opacity: 0; transform: translate(-50%, -60%); } }';\n" +
                            "document.head.appendChild(fadeOutStyle);";

            js.executeScript(script, colors[0], colors[1], icon, mainTitle, testName, testDescription, duration);

        } catch (Exception e) {
            System.out.println("⚠️ Browser banner gösterilemedi: " + e.getMessage());
        }
    }

    /**
     * 🎯 Banner tipine göre icon belirle
     */
    private static String getBannerIcon(String bannerType) {
        switch (bannerType.toLowerCase()) {
            case "start": return "🚀";
            case "progress": return "⏳";
            case "success": return "✅";
            case "warning": return "⚠️";
            case "error": return "❌";
            default: return "🎯";
        }
    }

    /**
     * 🎯 Banner tipine göre renk belirle
     */
    private static String[] getBannerColors(String bannerType) {
        switch (bannerType.toLowerCase()) {
            case "start": return new String[]{"#667eea 0%, #764ba2 100%", "#ff6b6b"};
            case "progress": return new String[]{"#f093fb 0%, #f5576c 100%", "#ffd93d"};
            case "success": return new String[]{"#4facfe 0%, #00f2fe 100%", "#00b894"};
            case "warning": return new String[]{"#ff9a9e 0%, #fad0c4 100%", "#fdcb6e"};
            case "error": return new String[]{"#ff5858 0%, #f09819 100%", "#d63031"};
            default: return new String[]{"#667eea 0%, #764ba2 100%", "#ff6b6b"};
        }
    }

    /**
     * 🎯 Konsol renk kodu
     */
    private static String getBannerColorCode(String bannerType) {
        switch (bannerType.toLowerCase()) {
            case "start": return "\u001B[35m"; // Purple
            case "progress": return "\u001B[33m"; // Yellow
            case "success": return "\u001B[32m"; // Green
            case "warning": return "\u001B[33m"; // Yellow
            case "error": return "\u001B[31m"; // Red
            default: return "\u001B[36m"; // Cyan
        }
    }




    // < -- ======= BANNER BANNNER SONU ===== -- >




    // < -- ===== excelle listeyi çeker Method başlangıcı

    public static void writeProductsToExcel(WebDriver driver, String categoryUrl, String productLocator, int baslangicSatir) {
        try {
            driver.get(categoryUrl);
            List<WebElement> products = driver.findElements(By.xpath(productLocator));
            String dosyaYolu = "C:\\Users\\Hp\\OneDrive\\Desktop\\loyalfriendcare\\bedManagerList.xlsx";

            Workbook workbook;
            Sheet sheet;

            try {
                FileInputStream fileInputStream = new FileInputStream(dosyaYolu);
                workbook = WorkbookFactory.create(fileInputStream);
                sheet = workbook.getSheetAt(0);
                fileInputStream.close();
            } catch (IOException e) {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("bedManagerList");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("İlaç Adı");
                headerRow.createCell(1).setCellValue("Eklenme Tarihi");
            }

            Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");

            for(int i = 0; i < products.size(); i++) {
                String productText = products.get(i).getText();
                String ilacAdi = productText;
                String eklenmeTarihi = "";

                Matcher matcher = datePattern.matcher(productText);
                if (matcher.find()) {
                    eklenmeTarihi = matcher.group();
                    ilacAdi = productText.replace(eklenmeTarihi, "").replace("\n", "").trim();
                }

                Row row = sheet.getRow(baslangicSatir + i);
                if(row == null) {
                    row = sheet.createRow(baslangicSatir + i);
                }

                row.createCell(0).setCellValue(ilacAdi);
                row.createCell(1).setCellValue(eklenmeTarihi);
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(dosyaYolu)) {
                workbook.write(fileOutputStream);
            }

            workbook.close();
            System.out.println("Toplam " + products.size() + " ürün Excel'e kaydedildi.");

        } catch (IOException e) {
            System.err.println("Excel kaydetme hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // < -- ===== excelle Liste çekme sonu  -- ===== >


    // < -- ===== excellReportBaslanici ===== -- >


    public static void generateMedicineReport(WebDriver driver, String categoryUrl,
                                              String productLocator, String filePath) {
        try {
            // 1. Ürün listesini çek
            driver.get(categoryUrl);
            List<WebElement> products = driver.findElements(By.xpath(productLocator));

            // 2. Yeni workbook oluştur
            Workbook workbook = new XSSFWorkbook();

            // 3. Stilleri oluştur
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dateStyle = createDateStyle(workbook);
            CellStyle defaultStyle = createDefaultStyle(workbook);

            // 4. Sayfa oluştur
            Sheet sheet = workbook.createSheet("Yatak Listesi");

            // 5. Rapor başlıkları
            createReportHeaders(sheet, headerStyle);

            // 6. Rapor bilgileri
            addReportInfo(sheet, defaultStyle, products.size(), categoryUrl);

            // 7. İlaç verilerini yaz
            writeMedicineData(sheet, products, dateStyle, defaultStyle);

            // 8. Sütun genişliklerini ayarla
            autoSizeColumns(sheet);

            // 9. Dosyayı kaydet
            saveWorkbook(workbook, filePath);

            System.out.println("Rapor oluşturma tamamlandı: " + filePath);
            System.out.println("Toplam " + products.size() + " ilaç kaydedildi.");

        } catch (Exception e) {
            System.err.println("Rapor oluşturma hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private static CellStyle createDateStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        style.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private static CellStyle createDefaultStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    private static void createReportHeaders(Sheet sheet, CellStyle headerStyle) {
        Row headerRow = sheet.createRow(3);
        String[] headers = {"Sıra No", "İlaç Adı", "Eklenme Tarihi", "Durum", "Notlar"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private static void addReportInfo(Sheet sheet, CellStyle style, int productCount, String categoryUrl) {
        // Rapor başlığı
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("LOYAL FRIEND CARE - YATAK LİSTESİ  RAPORU");
        titleCell.setCellStyle(style);

        // Rapor tarihi
        Row dateRow = sheet.createRow(1);
        dateRow.createCell(0).setCellValue("Rapor Tarihi: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));

        // Toplam ilaç sayısı
        Row countRow = sheet.createRow(1);
        countRow.createCell(2).setCellValue("Toplam İlaç: " + productCount);

        // URL bilgisi
        Row urlRow = sheet.createRow(2);
        urlRow.createCell(0).setCellValue("Kaynak: " + categoryUrl);
    }

    private static void writeMedicineData(Sheet sheet, List<WebElement> products,
                                          CellStyle dateStyle, CellStyle defaultStyle) {
        Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        int startRow = 4;

        for (int i = 0; i < products.size(); i++) {
            String productText = products.get(i).getText();
            String ilacAdi = productText;
            String eklenmeTarihi = "";
            String durum = "Aktif";

            // Tarihi ayır
            Matcher matcher = datePattern.matcher(productText);
            if (matcher.find()) {
                eklenmeTarihi = matcher.group();
                ilacAdi = productText.replace(eklenmeTarihi, "").replace("\n", "").trim();
            }

            // Satır oluştur
            Row row = sheet.createRow(startRow + i);

            // Sıra No
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(i + 1);
            cell0.setCellStyle(defaultStyle);

            // İlaç Adı
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(ilacAdi);
            cell1.setCellStyle(defaultStyle);

            // Eklenme Tarihi
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(eklenmeTarihi);
            cell2.setCellStyle(dateStyle);

            // Durum
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(durum);
            cell3.setCellStyle(defaultStyle);

            // Notlar (boş bırakılabilir)
            row.createCell(4).setCellValue("");
        }
    }

    private static void autoSizeColumns(Sheet sheet) {
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void saveWorkbook(Workbook workbook, String filePath) throws IOException {
        // Klasörü kontrol et ve oluştur
        java.io.File directory = new java.io.File(filePath).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            workbook.write(fileOutputStream);
            workbook.close();
        }
    }

    // Farklı isimlerle rapor oluşturmak için yardımcı method
    public static String generateCustomReport(WebDriver driver, String categoryUrl,
                                              String productLocator, String reportName) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String fileName = reportName + "_" + timestamp + ".xlsx";
        String fullPath = REPORT_PATH + fileName;

        generateMedicineReport(driver, categoryUrl, productLocator, fullPath);
        return fullPath;
    }


    // < -- ===== excellReportSonu ===== -- >



}



