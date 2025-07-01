# README — Smart Budget Tracker Projesi Hakkında

## Authors
- Çağan Durgun

## Git ile Değişiklikleri GitHub’a Gönderme (Push) — 3 Adım
Projede değişiklik yaptıktan sonra, bu değişiklikleri GitHub’a göndermek için sadece 3 adımı takip et.

---

### 1. Değişiklikleri Git’e Ekle
```bash
git add .
```
### 2. Değişiklikleri Commit Et
```bash
git commit -m "Yaptığın değişikliğin kısa ve net açıklaması"
```
### 3. Değişiklikleri GitHub’a Gönder (Push)

```bash
git push origin main
```
Eğer ana dalın adı farklıysa `main` yerine onun ismini yaz.

## Yapılanlar

- `application.properties` dosyası yapılandırıldı ve MySQL bağlantısı başarıyla tanımlandı. Aşağıdaki konfigürasyonlar kullanılarak uygulamanın yerel ortamda sorunsuz çalışması sağlandı:

```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/smart_budget_tracker
  spring.datasource.username=root
  spring.datasource.password=trunu+O+*ISP!xa!3ach
```

⚠️ Not: Bu yapılandırma, projenin yerel geliştirme ortamında (localhost) sağlıklı çalışması için gereklidir. Aksi durumda bağlantı hataları meydana gelebilir. 

- `pom.xml` dosyası güncellenerek gerekli bağımlılıklar eklendi. Özellikle Spring Boot, JPA, Lombok ve MySQL için ihtiyaç duyulan bağımlılıklar doğru versiyonlarla projeye entegre edildi.

- DB ayakta, edit fonksiyonu dahil çalışıyor, frontend güncellendi.