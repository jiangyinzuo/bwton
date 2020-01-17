FROM openjdk:13.0.1
VOLUME /tmp

# springboot app's jar
ADD bwton-carbon-credit-1.0-SNAPSHOT.jar app.jar

# denpendency of app.jar
COPY /lib /lib
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]