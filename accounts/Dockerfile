#Using jdk as base image for build(Use docker hub for finding images and tags) - FROM <image>:<tag>
FROM amazoncorretto:21-alpine

#Information of the party who manages the image
LABEL "org.opencontainers.image.authors"="com.sanish"

#Add application's JAR to this image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute command for the application to run
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]
