FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/assessment-0.0.1-SNAPSHOT.war app.jar
RUN mkdir -p /filedir
ENTRYPOINT ["java","-jar","app.jar"]