FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Autoparts.jar
ENTRYPOINT ["java","-jar","/Autoparts.jar"]

#RUN mkdir /opt/app
#COPY Autoparts.jar /opt/app
#CMD ["java", "-jar", "/opt/app/Autoparts.jar"]