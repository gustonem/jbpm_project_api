# spring-boot-jbpm-api

API for jBPM project


## Running the application locally

To run the API you need to be running postgres instance, you can run it on docker with:

```shell
docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=somePassword -d postgres
```

Then you can run the application by running:

```shell
mvn spring-boot:run
```

You can see the documentation of endpoints on

```shell
http://localhost:8090/swagger-ui.html
```

## Augustin Nemec