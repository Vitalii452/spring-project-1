FROM maven:3.9.6-amazoncorretto-21 as build
WORKDIR /spring-project-1

COPY src /spring-project-1/src
COPY pom.xml /spring-project-1

RUN mvn clean package -DskipTests

FROM tomcat:11.0-jre21

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /spring-project-1/target/*.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]