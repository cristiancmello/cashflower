# CashFlower

An App for Cash Flow Management.

Made out:

* Spring Boot 3.1
* Apache Maven 3.*

## Build

* Building YOLO-style

```sh
mvn clean install -DskipTests -DskipITs
```

## Testing

* Calling all tests

```sh
mvn clean verify
```

### Unit Tests

```sh
mvn clean verify -skipITs
```

### Integration Tests

```sh
mvn clean verify -skipTests
```