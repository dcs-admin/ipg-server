FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER Ram T
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY aws/application.properties /app/application.properties
COPY aws/application-aws.properties /app/application-aws.properties
COPY --from=MAVEN_BUILD /build/target/nest-server-0.0.1.jar /app/
ENTRYPOINT ["java", "-jar", "nest-server-0.0.1.jar"]
