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

## Containers

* Existem dois projetos a serem construídos:
  - Serviço **CashFlower** responsável pelos lançamentos de crédito e débito
  - Serviço **ConsolidadoDiario** responsável pelo relatório de fluxo de caixa diário

### Build deste projeto ('CashFlower')

```sh
docker build -t cashflower .
```

### Build do serviço 'ConsolidadoDiario'

```sh
git clone https://github.com/cristiancmello/consolidadodiario
docker build -t consolidadodiario .
```

### Executando ambas aplicações integrados por rede

```sh
docker network create cashflower_network
docker run --name servico-cashflower --network cashflower_network -p 8080:8080 cashflower
docker run --name servico-consolidadodiario --network cashflower_network -p 8081:8081 consolidadodiario
```