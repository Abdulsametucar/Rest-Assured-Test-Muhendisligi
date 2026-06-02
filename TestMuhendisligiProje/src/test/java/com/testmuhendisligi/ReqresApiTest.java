package com.testmuhendisligi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * ReqresApiTest - JSONPlaceholder API Otomatik Regresyon Test Sınıfı
 *
 * Bu sınıf, JSONPlaceholder (jsonplaceholder.typicode.com) public API'si
 * üzerinde GET ve POST istekleri ile otomatik regresyon testleri
 * gerçekleştirir.
 *
 * NOT: reqres.in API'si artık API key gerektirdiğinden, aynı işlevi gören
 * ve tamamen ücretsiz olan JSONPlaceholder API'si tercih edilmiştir.
 *
 * Her test metodunda üç temel kontrol (assertion) yapılır:
 * 1. Status Code kontrolü
 * 2. Response Body kontrolü (Hamcrest matchers ile)
 * 3. Response Time kontrolü (2000ms altında)
 *
 * @author BTU - Yazılım Test Mühendisliği
 * @version 1.0.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API Regresyon Testleri - JSONPlaceholder")
class ReqresApiTest {

    // ==========================================
    // SABİTLER (Constants)
    // ==========================================
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final long MAX_RESPONSE_TIME_MS = 2000L;

    // ==========================================
    // TEST YAŞAM DÖNGÜSÜ (Lifecycle)
    // ==========================================

    /**
     * Tüm testlerden önce bir kez çalışır.
     * Rest Assured base URI konfigürasyonunu yapar.
     */
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = BASE_URL;
        System.out.println("========================================");
        System.out.println("  API Otomatik Regresyon Testleri");
        System.out.println("  Base URL: " + BASE_URL);
        System.out.println("========================================");
    }

    /**
     * Tüm testler bittikten sonra bir kez çalışır.
     * Rest Assured konfigürasyonunu sıfırlar.
     */
    @AfterAll
    static void tearDown() {
        RestAssured.reset();
        System.out.println("========================================");
        System.out.println("  Tüm testler tamamlandı.");
        System.out.println("========================================");
    }

    // ==========================================
    // TEST METOTLARI
    // ==========================================

    /**
     * Test 1 - GET İsteği: Tek Kullanıcı Bilgisi Sorgulama
     *
     * Endpoint : GET /users/2
     * Beklenen : HTTP 200 OK
     *
     * Assertions:
     * - Status code 200 olmalı
     * - Response body'de kullanıcı bilgileri doğrulanmalı
     * - Response süresi 2000ms altında olmalı
     */
    @Test
    @Order(1)
    @DisplayName("GET /users/2 → Tek kullanıcı bilgisi başarıyla döner")
    void shouldReturnSingleUser_whenGetUserByIdTwo() {
        given()
                .header("Content-Type", "application/json")
                .log().uri()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()

                // Assertion 1: Status Code Kontrolü
                .statusCode(200)

                // Assertion 2: Response Body Kontrolü (Hamcrest Matchers)
                .body("id", equalTo(2))
                .body("name", equalTo("Ervin Howell"))
                .body("username", equalTo("Antonette"))
                .body("email", equalTo("Shanna@melissa.tv"))
                .body("address", notNullValue())
                .body("company.name", notNullValue())

                // Assertion 3: Response Time Kontrolü
                .time(lessThan(MAX_RESPONSE_TIME_MS));
    }

    /**
     * Test 2 - POST İsteği: Yeni Kayıt (Post) Oluşturma
     *
     * Endpoint : POST /posts
     * Body : {"title": "test başlığı", "body": "test içeriği", "userId": 1}
     * Beklenen : HTTP 201 Created
     *
     * Assertions:
     * - Status code 201 olmalı
     * - Response body'de gönderilen veriler ve ek alanlar doğrulanmalı
     * - Response süresi 2000ms altında olmalı
     */
    @Test
    @Order(2)
    @DisplayName("POST /posts → Yeni kayıt başarıyla oluşturulur")
    void shouldCreateNewPost_whenPostWithValidBody() {
        // Request body hazırlığı
        String requestBody = """
                {
                    "title": "Test Mühendisliği",
                    "body": "Otomatik regresyon testleri yazılım kalitesini artırır.",
                    "userId": 1
                }
                """;

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().status()
                .log().body()

                // Assertion 1: Status Code Kontrolü
                .statusCode(201)

                // Assertion 2: Response Body Kontrolü (Hamcrest Matchers)
                .body("title", equalTo("Test Mühendisliği"))
                .body("body", equalTo("Otomatik regresyon testleri yazılım kalitesini artırır."))
                .body("userId", equalTo(1))
                .body("id", notNullValue())

                // Assertion 3: Response Time Kontrolü
                .time(lessThan(MAX_RESPONSE_TIME_MS));
    }

    /**
     * Test 3 (Bonus) - GET İsteği: Post Listesi Sorgulama
     *
     * Endpoint : GET /posts
     * Beklenen : HTTP 200 OK
     *
     * Assertions:
     * - Status code 200 olmalı
     * - Yanıtta liste ve beklenen alanlar doğrulanmalı
     * - Response süresi 2000ms altında olmalı
     */
    @Test
    @Order(3)
    @DisplayName("GET /posts → Post listesi başarıyla döner")
    void shouldReturnPostList_whenGetAllPosts() {
        given()
                .header("Content-Type", "application/json")
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().status()

                // Assertion 1: Status Code Kontrolü
                .statusCode(200)

                // Assertion 2: Response Body Kontrolü (Hamcrest Matchers)
                .body("$", hasSize(100))
                .body("[0].id", equalTo(1))
                .body("[0].title", notNullValue())
                .body("[0].body", notNullValue())
                .body("[0].userId", equalTo(1))

                // Assertion 3: Response Time Kontrolü
                .time(lessThan(MAX_RESPONSE_TIME_MS));
    }
}
