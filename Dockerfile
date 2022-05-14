FROM openjdk:11

EXPOSE 8081

WORKDIR /applications

COPY target/*.jar /applications/pengu-classroom.jar

ENTRYPOINT ["java","-jar", "pengu-classroom.jar"]

#FROM adoptopenjdk/openjdk11:alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#VOLUME /tmp
#ARG JAR_FILE
#ADD ${JAR_FILE} /app/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app/app.jar"]