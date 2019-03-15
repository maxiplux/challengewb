# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add Maintainer Info
MAINTAINER Juan Francisco <juan.francisco.mosquera@gmail.com>
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 8080 available to the world outside this container
EXPOSE 3000
# The application's jar file
ARG JAR_FILE=target/wdvglab-0.0.1.jar
# Add the application's jar to the container
ADD ${JAR_FILE} wdvglab-0.0.1.jar
# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/wdvglab-0.0.1.jar"]
#http://localhost:3000/swagger-ui.html
#docker build -t wdvglab .
#docker run -p 3000:3000 wdvglab
#docker tag wdvglab maxiplux/wdvglab:latest
#docker push maxiplux/wdvglab:latest
