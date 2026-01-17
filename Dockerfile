# 1. Aşama: Build (Derleme)
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Önce sadece pom.xml'i kopyalayıp bağımlılıkları indiriyoruz (Cache avantajı)
COPY pom.xml .
RUN mvn dependency:go-offline

# Şimdi kaynak kodları kopyalayıp derliyoruz
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Aşama: Runtime (Çalıştırma)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# İlk aşamadan sadece oluşan .jar dosyasını alıyoruz
COPY --from=build /app/target/*.jar app.jar

# Uygulama portu
EXPOSE 8080

# Başlatma komutu
ENTRYPOINT ["java", "-jar", "app.jar"]