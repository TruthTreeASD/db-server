FROM openjdk:8-jre

COPY ./target/*SNAPSHOT.jar /database_service/

CMD java -jar /database_service/*SNAPSHOT.jar