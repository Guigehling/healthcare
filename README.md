# Healthcare-App
> Service aimed at managing health institutions and their exams.

[![Java Version][java-image]][npm-url]
[![spring Version][spring-image]][travis-url]

This API performs the creation of institutions and allows the management of your exams.

Each new institution generated receives an access key, which will give you permission to perform the management of your exams.
![](header.png)

## Development Setup

### Maven
To build this application, first perform the project complication with Maven using the following command.

```sh
mvn install
```
### Database
The application uses a SQLServer database to record information, the database was made available on AWS, so there is no need to upload a local database.

Liquibase was used for versioning the database structure, so if you want to recreate the structures just change the property below to ** TRUE ** in the application.properties file.
```sh
spring.liquibase.drop-first: true
```

### Documentation (Swagger)

The documentation of the services of this API are available on a page with the swagger of it, for first access it is necessary to upload the application and then access the link http://localhost:8080/api/healthcare/swagger-ui/

## Releases

* 0.0.1-SNAPSHOT
    * Initial version of the project

## Next steps

* Use a JWT token to manage access to exams.

<!-- Markdown link & img dfn's -->
[java-image]: https://img.shields.io/badge/java-v14-orange
[spring-image]: https://img.shields.io/badge/spring-v2.3.8.RELEASE-green
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
