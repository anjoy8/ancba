FROM apache/skywalking-base:8.3.0-es6
MAINTAINER laozhang <laozhang@azlinli.com>
VOLUME /tmp
COPY ./src/main/resources /opt
ARG JAR_FILE
ADD ${JAR_FILE} /app/app-blog.jar
ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES="10.168.37.36:11800" \
    SW_AGENT_NAME="ancba-blog"

EXPOSE 9092
#指定server
#ENTRYPOINT ["java","-jar","/app/app.jar","0.0.0.0"]
CMD ["java", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "-Xmx128M", "/app/app-blog.jar","--spring.config.location=../opt/application.yml"]
