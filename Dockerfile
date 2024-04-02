FROM bellsoft/liberica-openjre-debian:21
COPY target/petclinic-jdbc-1.0.0-SNAPSHOT.jar petclinic-jdbc-1.0.0-SNAPSHOT.jar
RUN java -Djarmode=tools -jar /petclinic-jdbc-1.0.0-SNAPSHOT.jar extract
RUN rm /petclinic-jdbc-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-Dspring.aot.enabled=true","-jar","/petclinic-jdbc-1.0.0-SNAPSHOT/petclinic-jdbc-1.0.0-SNAPSHOT.jar"]