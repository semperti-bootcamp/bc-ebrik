FROM openjdk:8-jdk-alpine
WORKDIR /root
RUN mkdir /root/upload
COPY ./PDFs/ /root/upload/
ADD http://10.252.7.162:8081/repository/maven-releases/com/semperti/trial/journals/VERSION/journals-VERSION.jar /opt/journals-VERSION.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/journals-VERSION.jar"]]

