#build multi-stage
#builder
#FROM maven:3-jdk-8 as builder
#WORKDIR /project
#COPY . .
#RUN mvn package
#end buider

#FROM java:8-alpine
#WORKDIR /app
#COPY --from=builder /project/target/ecommdocker-0.0.1-SNAPSHOT.jar ./ecommdocker.jar


#ENTRYPOINT ["/bin/sh", "-c"]
#CMD  [ "java -jar ecommdocker.jar" ]



#build single-stage
FROM maven:3-jdk-8
WORKDIR /app
COPY . .
RUN mvn package


ENTRYPOINT ["/bin/sh", "-c"]
CMD  [ "java -jar target/ecommdocker-0.0.1-SNAPSHOT.jar" ]


