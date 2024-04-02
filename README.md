## Spring Petclinic Efficient Container

This repository is designed to demonstrate how to improve Spring Boot applications efficiency and performances by upgrading from Spring Boot 2 / Java 8 to Spring Boot 3 / Java 21. This migration is done [step by step](https://github.com/sdeleuze/petclinic-efficient-container/commits/main/) and intermediate benchmarks can be performed.

### Pre-requisites

If [SDKMAN!](https://sdkman.io/) is installed:
```bash
sdk env install
```

A recent version of Docker, Docker Compose should be installed.

[Oha](https://github.com/hatoo/oha) should be installed for benchmarks.

### Getting started

If [SDKMAN!](https://sdkman.io/) is installed:
```bash
sdk env
```

To build the container image:
```bash
./build-container-image.sh
```

To run the application:
```bash
docker-compose up
```

### Branches
- `main` is leveraging [Spring Boot 3.3+ executable JAR self-extracting capabilities](https://docs.spring.io/spring-boot/reference/deployment/efficient.html#deployment.efficient.unpacking) and Dockerfiles.
- [`buildpacks`](https://github.com/sdeleuze/petclinic-efficient-container/tree/buildpacks) is leveraging [Paketo Buildpacks](https://github.com/paketo-buildpacks/spring-boot) CDS and Spring AOT support.
- [`premain`](https://github.com/sdeleuze/petclinic-efficient-container/tree/premain) is leveraging [Project Leyden `premain` experimental branch](https://github.com/openjdk/leyden/tree/premain).

### Benchmarks

By default, benchmarks are done with 2 CPUs and 2G RAM in order to emulate a cheap Cloud instance (see related
configuration in the [`docker-compose.yml` file](https://github.com/sdeleuze/petclinic-efficient-container/blob/main/docker-compose.yml). [Oha](https://github.com/hatoo/oha) is used to benchmarks web endpoints with a 60s warmup and a 30s benchmark.  

To benchmark a Thymeleaf template that is using cached data, run:
```bash
oha -z60s http://localhost:8080/vets.html && oha -z30s http://localhost:8080/vets.html
```

To benchmark a REST endpoint with JSON serialization using cached data, run:
```bash
oha -z60s http://localhost:8080/vets && oha -z30s http://localhost:8080/vets
```

To benchmark a Thymeleaf template that is using data retrieved by Spring Data JDBC, comment the `@Cacheable("vets")` annotation in [`VetRepository`](https://github.com/sdeleuze/petclinic-efficient-container/blob/8a387727b455866a66332e238ea79a88bf43e08f/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java#L38), rebuild and restart the container, then run:
```bash
oha -z60s http://localhost:8080/vets.html && oha -z30s http://localhost:8080/vets.html
```

To benchmark static resources, run:
```bash
oha -z60s http://localhost:8080/resources/images/spring-logo.png && oha -z30s http://localhost:8080/resources/images/spring-logo.png
```
