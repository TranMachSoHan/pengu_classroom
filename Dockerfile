FROM openjdk:11

EXPOSE 8080

WORKDIR /applications

COPY target/*.jar /applications/pengu-classroom.jar

ENTRYPOINT ["java","-jar", "pengu-classroom.jar"]
