FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/productoption-0.0.1-SNAPSHOT.jar /home/productoption-0.0.1-SNAPSHOT.jar
COPY products.db /home/products.db
EXPOSE 8090
ENTRYPOINT ["java","-jar","/home/productoption-0.0.1-SNAPSHOT.jar"]