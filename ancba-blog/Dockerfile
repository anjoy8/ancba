FROM adoptopenjdk:11-jre-hotspot
MAINTAINER laozhang <laozhang@azlinli.com>
VOLUME /tmp
COPY ./src/main/resources /opt
ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar
EXPOSE 9092
#指定server
#ENTRYPOINT ["java","-jar","/app/app.jar","0.0.0.0"]
CMD ["java", "-jar", "/app/app.jar","--spring.config.location=../opt/application.yml"]
