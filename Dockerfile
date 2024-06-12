FROM ubuntu:focal

RUN export DEBIAN_FRONTEND=noninteractive && \
    apt-get update && \
    apt-get install -y --no-install-recommends tzdata ca-certificates git curl build-essential libfreetype6-dev libfontconfig-dev libcups2-dev libx11-dev libxext-dev libxrender-dev libxrandr-dev libxtst-dev libxt-dev libasound2-dev libffi-dev autoconf file unzip zip nano

RUN case $(uname -m) in \
        aarch64)   export BOOT_JDK_URL="https://download.bell-sw.com/java/22+37/bellsoft-jdk22+37-linux-aarch64.tar.gz" ;; \
        *)       export BOOT_JDK_URL="https://download.bell-sw.com/java/22+37/bellsoft-jdk22+37-linux-amd64.tar.gz" ;; \
    esac && \
    mkdir -p /opt/boot-jdk && \
    cd /opt/boot-jdk && \
    curl -L ${BOOT_JDK_URL} | tar zx --strip-components=1 && \
    test -f /opt/boot-jdk/bin/java && \
    test -f /opt/boot-jdk/bin/javac

RUN cd /opt && \
    git clone -b premain --depth 1 https://github.com/openjdk/leyden.git

RUN cd /opt/leyden && \
	bash configure --with-native-debug-symbols=none --with-boot-jdk=/opt/boot-jdk && \
	make images && \
	mv /opt/leyden/build/linux-$(uname -m)-server-release/images/jdk /opt

ENV JAVA_HOME /opt/jdk
ENV PATH $JAVA_HOME/bin:$PATH

COPY target/petclinic-jdbc-1.0.0-SNAPSHOT.jar petclinic-jdbc-1.0.0-SNAPSHOT.jar
RUN java -Djarmode=tools -jar /petclinic-jdbc-1.0.0-SNAPSHOT.jar extract && \
    rm /opt/jdk/lib/server/classes.jsa && \
    rm /opt/jdk/lib/server/classes_nocoops.jsa && \
    rm -rf /opt/jdk/demo && \
    rm -rf /opt/jdk/jmods

FROM ubuntu:focal
COPY --from=0 /opt/jdk /opt/jdk
COPY --from=0 /petclinic-jdbc-1.0.0-SNAPSHOT /petclinic-jdbc-1.0.0-SNAPSHOT
ENV JAVA_HOME /opt/jdk
ENV PATH $JAVA_HOME/bin:$PATH

RUN java -XX:CacheDataStore=/application.cds -Dspring.aot.enabled=true -Dspring.context.exit=onRefresh -jar /petclinic-jdbc-1.0.0-SNAPSHOT/petclinic-jdbc-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-Dspring.aot.enabled=true","-XX:CacheDataStore=/application.cds","-jar","/petclinic-jdbc-1.0.0-SNAPSHOT/petclinic-jdbc-1.0.0-SNAPSHOT.jar"]