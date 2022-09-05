# ChatAPI

Se trata de uma API utilizando Java, Springboot, RabbitMQ e Docker para uma aplicação de chat.

## Requerimentos

- JDK 17 ou superior
- Lombok Annotations Support for VS Code (extensão para o <b>Visual Studio Code</b>)
- Docker

## Para utilizar o projeto

Faça o clone do repositório:

```
git clone https://github.com/Acacio-coding/ChatAPI
```

Agora siga os seguintes passos:

1. Abra o projeto com a IDE ou editor de código de sua preferência
2. Gere e inicie o container do RabbitMQ no Docker através do comando:

```
docker-compose up --build --force-recreate -d
```

3. Realize um build na aplicação e execute a mesma a partir da classe <b>ChatApiApplication</b>

<b>Obs</b>.: vale lembrar que o projeto está utilizando o <b>H2 Database</b> em memória, portanto nenhum dado ficará
salvo após o encerramento da aplicação.

## Comandos úteis

- Para parar o container do RabbitMQ:

```
docker-compose down
```
