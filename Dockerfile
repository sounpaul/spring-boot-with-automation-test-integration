FROM adoptopenjdk:8u292-b10-jre-hotspot-focal
VOLUME /tmp
ADD build/libs/employee-service-app-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Dspring.profiles.active=dev"
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar