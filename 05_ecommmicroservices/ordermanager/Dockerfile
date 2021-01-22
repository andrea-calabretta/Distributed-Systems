FROM maven:3-jdk-8 as builder
WORKDIR /project
COPY entities/ entities
WORKDIR /project/entities
RUN mvn install #crea il jar e lo installa nel repository maven locale (del container)
WORKDIR /project
COPY ordermanager .
#ADD ./target/productmanager-0.0.1-SNAPSHOT.jar ./productmanager.jar
RUN mvn package

FROM java:8-alpine
WORKDIR /app
COPY --from=builder /project/target/ordermanager-0.0.1-SNAPSHOT.jar ./ordermanager.jar
#ENTRYPOINT ["/bin/sh", "-c"]
CMD java -jar ordermanager.jar



#FROM docker.io/java:8
#WORKDIR /app
#ADD ./target/ordermanager-0.0.1-SNAPSHOT.jar ./ordermanager.jar
#CMD java -jar ordermanager.jar