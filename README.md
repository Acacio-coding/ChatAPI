# ChatAPI

Se trata de uma API utilizando Java, Springboot, RabbitMQ e Docker para uma aplicação de chat.

## Requerimentos

- JDK 17 ou superior
- Lombok Annotations Support for VS Code (extensão para o <b>Visual Studio Code</b>)
- Maven (caso não utilize o mesmo por uma IDE)
- Docker

## Para utilizar o projeto

Faça o clone do repositório:

```
git clone https://github.com/Acacio-coding/ChatAPI
```

Agora siga os seguintes passos:

1. Abra o projeto com a IDE, editor de código ou terminal de sua preferência
2. Utilize o <i><b>lifecycle</b></i> do maven para compilar e gerar os arquivos necessários com o comando:
```
mvn clean install
```
3. Gere e inicie os containers no Docker através do comando:
```
docker-compose up --build --force-recreate
```

<b>Obs</b>.: vale lembrar que o projeto está utilizando o <b>H2 Database</b> em memória, portanto nenhum dado ficará salvo após o encerramento da aplicação.