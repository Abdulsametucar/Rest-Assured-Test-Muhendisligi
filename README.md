# 🧪 API Otomatik Regresyon Testi

> **Ders:** Yazılım Test Mühendisliği  
> **Üniversite:** Bursa Teknik Üniversitesi (BTU)  
> **Dönem:** 2025–2026 Bahar  
> **Dil:** Java 17

---

## 📌 Proje Hakkında

Bu proje, **Yazılım Test Mühendisliği** dersi kapsamında hazırlanmış bir **API otomatik regresyon test** uygulamasıdır. Proje, [JSONPlaceholder](https://jsonplaceholder.typicode.com) public REST API'si üzerinde **JUnit 5** ve **Rest Assured** kütüphaneleri kullanılarak otomatik test senaryoları gerçekleştirir.

### 🎯 Projenin Amacı

- Yazılım test mühendisliğinin temel kavramlarını uygulamalı olarak göstermek
- Otomatik regresyon testlerinin nasıl yazıldığını ve çalıştırıldığını öğretmek
- API testi için endüstri standardı araçların (Rest Assured, JUnit 5) kullanımını sergilemek
- Yapay zekanın yazılım test süreçlerindeki rolünü tartışmak

---

## 🏗️ Proje Yapısı

```
TestMuhendisligiProje/
│
├── 📄 pom.xml                                  ← Maven yapılandırma (bağımlılıklar)
├── 📄 README.md                                ← Bu dosya
├── 📄 SUNUM.md                                 ← Sunum dokümanı (9 slayt)
├── 📄 mvnw.cmd                                 ← Maven Wrapper (Windows)
├── 📄 .gitignore                               ← Git ignore kuralları
│
├── 📂 .mvn/                                    ← Maven Wrapper yapılandırması
│
├── 📂 src/
│   └── 📂 test/
│       └── 📂 java/
│           └── 📂 com/
│               └── 📂 testmuhendisligi/
│                   └── 📄 ReqresApiTest.java   ← Ana test sınıfı
│
└── 📂 target/                                  ← Derleme çıktıları (otomatik oluşur)
    └── 📂 surefire-reports/                    ← Test sonuç raporları
```

---

## 🛠️ Kullanılan Teknolojiler

| Teknoloji | Versiyon | Rolü |
|---|---|---|
| **Java** | 17 | Programlama dili |
| **Maven** | 3.x (Wrapper) | Proje yönetimi ve bağımlılık yönetimi |
| **JUnit 5 (Jupiter)** | 5.10.2 | Test framework — testleri organize eder ve çalıştırır |
| **Rest Assured** | 5.4.0 | API test kütüphanesi — HTTP istekleri gönderir |
| **Hamcrest** | 2.2 | Assertion matcher kütüphanesi — yanıtları doğrular |
| **JSONPlaceholder** | — | Ücretsiz public REST API (test verisi kaynağı) |

### Teknolojilerin Kısa Açıklaması

- **JUnit 5:** Java'nın en yaygın test framework'üdür. `@Test`, `@BeforeAll`, `@AfterAll`, `@Order` gibi anotasyonlarla testlerin yaşam döngüsünü yönetir.
- **Rest Assured:** REST API'lere HTTP istekleri (GET, POST, PUT, DELETE) göndermek ve yanıtları doğrulamak için kullanılan Java kütüphanesidir. `given() → when() → then()` BDD (Behavior-Driven Development) sözdizimini kullanır.
- **Hamcrest:** `equalTo()`, `notNullValue()`, `hasSize()`, `lessThan()` gibi okunabilir assertion matcher'lar sağlar.
- **JSONPlaceholder:** Gerçek bir backend gerektirmeden API testleri yapmak için kullanılan sahte (fake) REST API servisidir.

---

## ⚙️ Kurulum ve Çalıştırma

### Ön Gereksinimler

- **Java 17** veya üzeri yüklü olmalıdır
- İnternet bağlantısı gereklidir (API'ye istek göndermek için)
- Maven yüklü olmasına **gerek yoktur** (Maven Wrapper dahildir)

### Java Versiyonu Kontrolü

```bash
java -version
```

Çıktıda `17` veya üzeri bir versiyon görmelisiniz.

### Testleri Çalıştırma

#### Windows
```bash
.\mvnw.cmd test
```

#### macOS / Linux
```bash
./mvnw test
```

### Beklenen Başarılı Çıktı

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.testmuhendisligi.ReqresApiTest
========================================
  API Otomatik Regresyon Testleri
  Base URL: https://jsonplaceholder.typicode.com
========================================

Request URI: https://jsonplaceholder.typicode.com/users/2
HTTP/1.1 200 OK
{ "id": 2, "name": "Ervin Howell", ... }

Request URI: https://jsonplaceholder.typicode.com/posts
HTTP/1.1 201 Created
{ "title": "Test Mühendisliği", "id": 101, ... }

Request URI: https://jsonplaceholder.typicode.com/posts
HTTP/1.1 200 OK

========================================
  Tüm testler tamamlandı.
========================================

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

---

## 🧪 Test Senaryoları

Projede 3 adet otomatik test senaryosu bulunmaktadır. Her senaryo **3 kritik kontrol (assertion)** içerir:

1. ✅ **Status Code Assertion** — HTTP durum kodu beklenen değere eşit mi?
2. ✅ **Response Body Assertion** — Yanıt gövdesinde beklenen veriler var mı?
3. ✅ **Response Time Assertion** — Yanıt süresi kabul edilebilir sürede mi?

---

### Test 1: Tek Kullanıcı Sorgulama (GET)

| Özellik | Değer |
|---|---|
| **Metot** | `shouldReturnSingleUser_whenGetUserByIdTwo()` |
| **HTTP Metodu** | `GET` |
| **Endpoint** | `/users/2` |
| **Beklenen Durum Kodu** | `200 OK` |

**Bu test ne yapar?**  
API'ye 2 numaralı kullanıcının bilgilerini sorgulamak için GET isteği gönderir. Dönen yanıtta kullanıcının adı (`Ervin Howell`), kullanıcı adı (`Antonette`), e-posta adresi ve diğer alanlar doğrulanır.

**Kontrol edilen alanlar:**
- `id` = 2
- `name` = "Ervin Howell"
- `username` = "Antonette"
- `email` = "Shanna@melissa.tv"
- `address` ≠ null
- `company.name` ≠ null

---

### Test 2: Yeni Kayıt Oluşturma (POST)

| Özellik | Değer |
|---|---|
| **Metot** | `shouldCreateNewPost_whenPostWithValidBody()` |
| **HTTP Metodu** | `POST` |
| **Endpoint** | `/posts` |
| **Beklenen Durum Kodu** | `201 Created` |

**Bu test ne yapar?**  
API'ye yeni bir post (yazı) oluşturmak için JSON verisi gönderir. Gönderilen verilerin doğru şekilde kaydedildiğini ve API'nin yeni bir `id` atadığını doğrular.

**Gönderilen veri:**
```json
{
    "title": "Test Mühendisliği",
    "body": "Otomatik regresyon testleri yazılım kalitesini artırır.",
    "userId": 1
}
```

**Kontrol edilen alanlar:**
- `title` = "Test Mühendisliği"
- `body` = "Otomatik regresyon testleri yazılım kalitesini artırır."
- `userId` = 1
- `id` ≠ null (API tarafından otomatik atanır)

---

### Test 3: Liste Sorgulama (GET)

| Özellik | Değer |
|---|---|
| **Metot** | `shouldReturnPostList_whenGetAllPosts()` |
| **HTTP Metodu** | `GET` |
| **Endpoint** | `/posts` |
| **Beklenen Durum Kodu** | `200 OK` |

**Bu test ne yapar?**  
API'den tüm postların listesini çeker ve listenin beklenen formatta geldiğini doğrular. Toplam 100 adet post döndüğü ve ilk postun doğru yapıda olduğu kontrol edilir.

**Kontrol edilen alanlar:**
- Listenin boyutu = 100
- İlk elemanın `id` = 1
- İlk elemanın `title` ≠ null
- İlk elemanın `body` ≠ null
- İlk elemanın `userId` = 1

---

## 📊 Test Sonuçlarını Okuma Rehberi

### Terminaldeki Sonuçlar

Testleri çalıştırdıktan sonra terminalde aşağıdaki bilgileri kontrol edin:

| Ne kontrol ediyorsunuz? | Nereye bakıyorsunuz? | Başarılı ✅ | Başarısız ❌ |
|---|---|---|---|
| Genel sonuç | En alttaki satır | `BUILD SUCCESS` | `BUILD FAILURE` |
| Test istatistikleri | `Tests run: X, Failures: Y` | `Failures: 0` | `Failures: 1+` |
| HTTP durum kodu | `HTTP/1.1 XXX` | `200 OK` veya `201 Created` | `400`, `404`, `500` |
| Yanıt verisi | JSON çıktısı | Beklenen değerler mevcut | Eksik veya yanlış değerler |

### XML Test Raporları

Maven Surefire eklentisi her test çalıştırmasında otomatik olarak XML raporları oluşturur:

```
target/surefire-reports/
├── com.testmuhendisligi.ReqresApiTest.txt      ← Metin formatında özet
└── TEST-com.testmuhendisligi.ReqresApiTest.xml ← XML formatında detaylı rapor
```

---

## 🔑 Temel Kavramlar

### Regresyon Testi Nedir?

**Regresyon testi**, yazılıma yapılan her değişiklikten (yeni özellik, hata düzeltme, refactoring) sonra, **mevcut fonksiyonların bozulmadığını** doğrulamak için tekrarlanan test sürecidir.

### Neden Otomatik Test?

| Kriter | Manuel Test | Otomatik Test |
|---|---|---|
| **Hız** | Yavaş (saatler) | Çok hızlı (saniyeler) |
| **Tutarlılık** | İnsan hatası riski | Her seferinde aynı sonuç |
| **Tekrarlanabilirlik** | Zor | Kolay |
| **CI/CD Uyumu** | Zor | Mükemmel |
| **Maliyet (uzun vade)** | Yüksek | Düşük |

### BDD Sözdizimi (Given-When-Then)

Rest Assured, **Behavior-Driven Development (BDD)** sözdizimini kullanır:

```java
given()                          // ÖN KOŞULLAR: Header, body, parametre ayarla
    .header("Content-Type", "application/json")
    .body(requestBody)
.when()                          // EYLEM: HTTP isteğini gönder
    .post("/posts")
.then()                          // DOĞRULAMA: Yanıtı kontrol et
    .statusCode(201)
    .body("title", equalTo("Test"));
```

Bu yapı şu şekilde okunur:
- **Given** (Verilen): "Şu koşullar sağlandığında..."
- **When** (Ne zaman): "Şu işlem yapıldığında..."
- **Then** (O zaman): "Şu sonuç beklenir."

---

## 📝 JUnit 5 Yaşam Döngüsü (Lifecycle)

Projede kullanılan JUnit 5 anotasyonları ve çalışma sıraları:

```
@BeforeAll  →  Tüm testlerden ÖNCE bir kez çalışır (API base URL ayarlanır)
    │
    ├── @Test @Order(1)  →  Test 1: GET /users/2
    ├── @Test @Order(2)  →  Test 2: POST /posts
    └── @Test @Order(3)  →  Test 3: GET /posts
    │
@AfterAll   →  Tüm testlerden SONRA bir kez çalışır (temizlik yapılır)
```

| Anotasyon | Açıklama |
|---|---|
| `@BeforeAll` | Tüm testlerden önce bir kez çalışır (static metot) |
| `@AfterAll` | Tüm testlerden sonra bir kez çalışır (static metot) |
| `@Test` | Metodu test metodu olarak işaretler |
| `@Order(n)` | Testlerin çalışma sırasını belirler |
| `@DisplayName` | Test için okunabilir bir isim tanımlar |
| `@TestMethodOrder` | Sınıf düzeyinde sıralama stratejisini belirler |

---

## 🌐 API Endpoint'leri

Test edilen [JSONPlaceholder](https://jsonplaceholder.typicode.com) endpoint'leri:

| HTTP Metodu | Endpoint | Açıklama | Durum Kodu |
|---|---|---|---|
| `GET` | `/users/2` | 2 numaralı kullanıcıyı getirir | `200 OK` |
| `POST` | `/posts` | Yeni bir post oluşturur | `201 Created` |
| `GET` | `/posts` | Tüm postları listeler | `200 OK` |

---

## 🔧 Olası Sorunlar ve Çözümleri

| Sorun | Neden | Çözüm |
|---|---|---|
| `BUILD FAILURE` — Response time hatası | İlk istek yavaş olabilir (cold start, ağ gecikmesi) | `MAX_RESPONSE_TIME_MS` değerini artırın (örn: 5000L) |
| `java: error: release version 17` | Java 17 yüklü değil | JDK 17 veya üzeri yükleyin |
| `Connection refused` | İnternet bağlantısı yok | İnternet bağlantınızı kontrol edin |
| Türkçe karakterler bozuk görünüyor | Konsol encoding sorunu | Terminal encoding'ini UTF-8 yapın |

---

## 📚 Kaynaklar

- [JSONPlaceholder — Fake REST API](https://jsonplaceholder.typicode.com/)
- [Rest Assured — Resmi Dokümantasyon](https://rest-assured.io/)
- [JUnit 5 — Kullanıcı Kılavuzu](https://junit.org/junit5/docs/current/user-guide/)
- [Hamcrest — Matcher Kütüphanesi](http://hamcrest.org/JavaHamcrest/)
- [ISTQB — Uluslararası Yazılım Test Yeterlilik Kurulu](https://www.istqb.org/)
- [Martin Fowler — Test Piramidi](https://martinfowler.com/bliki/TestPyramid.html)

---

## 📄 Lisans

Bu proje, Bursa Teknik Üniversitesi Yazılım Test Mühendisliği dersi kapsamında eğitim amaçlı hazırlanmıştır.

---

*BTU — Yazılım Test Mühendisliği Dersi, 2025–2026 Bahar Dönemi*
