FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG PACKAGE_FILE
COPY ${PACKAGE_FILE} app.tar.gz
RUN tar -zxvf app.tar.gz
RUN mv go-fastdfs-web/* /
RUN rm -rf go-fastdfs-web
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/go-fastdfs-web.jar"]