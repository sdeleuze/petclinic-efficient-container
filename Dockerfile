FROM bellsoft/liberica-openjre-debian:21
COPY target/petclinic-jdbc-1.0.0-SNAPSHOT.jar petclinic-jdbc-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/petclinic-jdbc-1.0.0-SNAPSHOT.jar"]