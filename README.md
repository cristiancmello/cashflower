# CashFlower

Um App de Gerenciamento de Fluxo de Caixa.

Tecnologias utilizadas:

* Spring Boot 3
* Apache Maven 3

## Build

```sh
mvn clean install
```

## Testing

```sh
mvn clean verify
```

## Container

### Build

```sh
docker build -t cashflower .
```

### Run

```sh
docker network create cashflower_network
docker run --name servico-cashflower --network cashflower_network -p 8080:8080 cashflower
```