FROM apache/skywalking-java-agent:8.8.0-java11
MAINTAINER laozhang <laozhang@azlinli.com>
VOLUME /tmp
COPY ./src/main/resources /opt
ARG JAR_FILE
ADD ${JAR_FILE} /app/app-oauth.jar
EXPOSE 8181
#指定server
#ENTRYPOINT ["java","-jar","/app/app.jar","0.0.0.0"]
CMD ["java", "-jar", "-Xmx128M", "/app/app-oauth.jar","--spring.config.location=../opt/application.yml"]
