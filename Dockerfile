FROM eclipse-temurin:17-jre-alpine
# This takes the JAR created by your successful Jenkins build
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]