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

### Testando APIs (com cURL)

* Lançamento de Crédito

```sh
curl -X POST \
  --url http://localhost:8080/lancamento/credito \
  --header 'Content-Type: application/json' \
  --data '{"valor": "300.00"}'
  
# Exemplo de saida
{"mensagem":"Lancado com sucesso!","dataEHoraLancamento":"2023-06-05T04:49:31.579075847"}
```

* Lançamento de Débito

```sh
curl -X POST \
  --url http://localhost:8080/lancamento/debito \
  --header 'Content-Type: application/json' \
  --data '{"valor": "100.0"}'
  
# Exemplo de saida
{"mensagem":"Lancado com sucesso!","dataEHoraLancamento":"2023-06-05T04:51:20.972471983"}
```

* Consolidado Diário

```sh
curl -X GET \
  --url 'http://localhost:8081/consolidado-diario?data=2023-06-05' \
  --header 'Content-Type: application/json'
  
# Exemplo de saida
{"mensagem":"No dia Jun 5, 2023 seu saldo foi de R$ 2900.00"}
```

* Logs de execução via Docker Desktop

![](./docs/report-cashflower.png)

![](./docs/report-consolidadodiario.png)