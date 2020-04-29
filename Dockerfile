FROM openjdk:11.0-jre-slim
MAINTAINER xinyue<xinyue@xinyue.com>
EXPOSE 8081
VOLUME /tmp
ADD /target/blog-0.0.1-SNAPSHOT.jar app.jar
# RUN sh -c 'touch /app.jar'
# ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "-jar", "/app.jar", "--spring.profiles.active=dev"]