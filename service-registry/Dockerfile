FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} serviceregistry.jar

ENTRYPOINT ["java", "-jar", "/serviceregistry.jar"]


#  docker  build -t dailycodebuffer/serviceregistry:0.0.1 .

# export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
