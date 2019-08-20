FROM openjdk:8-jdk-alpine
WORKDIR /root
RUN mkdir /root/upload
COPY ./PDFs/ /root/upload/
ADD http://10.252.7.162:8081/repository/maven-releases/com/semperti/trial/journals/5.0/journals-5.0.jar /opt/journals-5.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/journals-5.0.jar"]]

