FROM maven:3-jdk-8 as builder
WORKDIR /project
COPY registrationserver .
#ADD ./target/productmanager-0.0.1-SNAPSHOT.jar ./productmanager.jar
RUN mvn package

FROM java:8-alpine
WORKDIR /app
COPY --from=builder /project/target/registrationserver-0.0.1-SNAPSHOT.jar ./registrationserver.jar
#ENTRYPOINT ["/bin/sh", "-c"]
CMD java -jar registrationserver.jar