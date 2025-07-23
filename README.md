AUTHORS
-------
çağan Durgun
Sabri Mert Pişkin
-------
Smart Budget Tracker
Smart Budget Tracker, kullanıcıların kişisel finanslarını yönetmelerine yardımcı olmak için geliştirilmiş bir web uygulamasıdır. Bu proje, harcamaları izlemeyi, bütçeleri yönetmeyi ve finansal alışkanlıkları anlamayı hedefler. Proje, BIL482 - Tasarım Desenleri dersi kapsamında geliştirilmiştir.
Ana Özellikler
Harcama Yönetimi: Kategori, tutar ve tarihe göre harcama ekleme, düzenleme ve silme.
Bütçe Kontrolü: Kategoriler için bütçe limitleri belirleme ve durumu izleme.
Akıllı Bildirimler: Bütçe limitlerine yaklaşıldığında veya aşıldığında uyarılar.
Görsel Analiz: Harcama dağılımını ve finansal özetleri interaktif grafiklerle sunma.
Undo/Redo Desteği: Command tasarım deseni ile yapılan işlemleri geri alma ve yineleme.

Teknik Detaylar
--------------
Teknoloji Mimarisi
Backend: Spring Boot, Spring Data JPA, Hibernate
Frontend: React.js, Vite, Chart.js
Veritabanı: MySQL
Build Aracı: Maven
API: RESTful
Uygulanan Tasarım Desenleri
--------------
Factory Pattern: Harcama nesnelerinin türlerine göre (sabit, değişken) tutarlı bir şekilde oluşturulmasını sağlar.
Command Pattern: Kullanıcı eylemlerini (ekleme, silme vb.) nesne olarak kapsülleyerek geri alma (undo) işlevselliği sunar.
Observer Pattern: Bütçe durumu gibi olayları izleyerek ilgili bileşenleri (bildirim servisi vb.) bilgilendirir.
Builder & Facade Pattern: Karmaşık raporlama süreçlerini basitleştirir ve istemciye sade bir arayüz sunar.
Geliştirme Süreci ve Kalite Güvencesi
--------------
Proje geliştirme sürecinde, her bir özelliğin doğru ve eksiksiz bir şekilde tamamlandığından emin olmak için Kabul Kriterleri (Acceptance Criteria) ve Tamamlanma Tanımı (Definition of Done) metodolojileri benimsenmiştir.
Kabul Kriterleri: Her bir işlev için "Bu özellik hangi şartları sağlarsa kabul edilir?" sorusuna net cevaplar veren maddeler belirlenmiştir.
Tamamlanma Tanımı (DoD): Bir özelliğin tamamlanmış sayılması için "Unit testleri yazıldı", "Kod tekrar gözden geçirildi" gibi adımları içeren bir kontrol listesi kullanılmıştır.
Bu yaklaşım, geliştirme sürecini standart hale getirmiş ve projenin kalitesini artırmıştır.
Kurulum ve Çalıştırma
-------------
Gereksinimler
Java 17+
Maven 3.9+
Node.js 20+
MySQL 

1. Backend'i Çalıştırma (Spring Boot)
Bash
# Projeyi klonlayın ve dizine gidin
git clone https://github.com/sabrimertpiskin/bil482-smartbudgettracker.git
cd bil482-smartbudgettracker

# src/main/resources/application.properties dosyasını açıp
# kendi MySQL kullanıcı adı ve şifrenizle aşağıdaki satırları güncelleyin:
# spring.datasource.username=root
# spring.datasource.password=KENDI_SIFRENIZ

# Maven ile backend'i çalıştırın
./mvnw spring-boot:run

Backend sunucusu http://localhost:8080 adresinde başlayacaktır.

2. Frontend'i Çalıştırma (React)
Yeni bir terminal açın ve aşağıdaki adımları izleyin:
Bash
# Frontend klasörüne gidin
cd smart-budget-tracker-ui

# Gerekli paketleri yükleyin
npm install

# Geliştirme sunucusunu başlatın
npm run dev

Frontend uygulaması http://localhost:5173 adresinde başlayacaktır ve backend ile otomatik olarak iletişim kuracaktır.

Test Sonuçları
-----------------
Projenin backend'i için hazırlanan tüm testler başarıyla geçmiştir. Bu testler, yukarıda bahsedilen Kabul Kriterleri'nin karşılandığını ve Tamamlanma Tanımı'na uyulduğunu doğrulamaktadır.
Toplam Test Sayısı: 17
Başarılı Test: 17
Başarısız Test: 0
Durum: BUILD SUCCESS
Proje gereksinimleri doğrultusunda istenen formatta detaylı test raporu hazırlanmıştır.
Rapor Linki:
https://docs.google.com/spreadsheets/d/1qaPktjYuKLRz5wXonKIkaU1kbuWMgdJm1F_BZC9w5NQ/edit?pli=1&gid=1976844612#gid=1976844612
Proje Ekibi:
-----------
Berşan Kayra Korkmaz
Çağan Durgun
Emirhan Yavuz
Emre Karadoğan
Sabri Mert Piskin
