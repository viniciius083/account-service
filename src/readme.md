# Account Service

O **Account Service** é um microserviço responsável por gerenciar informações de contas para Pessoa Jurídica (PJ). Ele permite a criação, atualização e consulta de contas, além de publicar eventos de criação/atualização para integração com outros serviços através do RabbitMQ.

---

## **Funcionalidades**

- Criar uma nova conta com informações como:
    - CNPJ
    - Nome da Empresa (Razão Social)
    - Nome Fantasia
    - E-mail
    - Telefone
    - Endereço detalhado (Rua, Número, Bairro, Cidade, Estado, CEP e Complemento)
- Atualizar o status de uma conta (`PENDING`, `APPROVED`, `REJECTED`).
- Publicar eventos de criação ou atualização de conta para uma fila RabbitMQ.

---

## **Requisitos**

- **Java 21+**
- **Maven 3.8+**
- **MySQL 8.0+**
- **RabbitMQ**

---

## **Configuração do Ambiente**

### Banco de Dados (MySQL)

1. Crie o banco de dados chamado `account`:
```sql
   CREATE DATABASE account;
```
2. Atualize as credenciais no arquivo `application.yml` caso necessário:
```yaml
   spring:
   datasource:
   url: jdbc:mysql://localhost:3306/account_service
   username: root
   password: root
````

---

## **Configuração do RabbitMQ**

Certifique-se de que o RabbitMQ esteja em execução. Por padrão, o serviço usa as seguintes configurações:

* Host: localhost
* Porta: 5672
* Usuário: guest
* Senha: guest

Caso precise alterar, edite o arquivo `application.yml`:

```yaml
spring:
rabbitmq:
host: localhost
port: 5672
username: guest
password: guest
```

---


## **Arquitetura**

## Fluxo de Requisição

1. O cliente envia uma requisição para o account-service.
2. O serviço processa a lógica de negócio, como criação ou atualização da conta.
3. Após o processamento, um evento é enviado para o RabbitMQ (fila account-upsert) notificando outros serviços sobre a mudança.

---

## **Estrutura de Diretórios**
```css
src/
├── main/
│   ├── java/
│   │   └── br/com/accountservice/
│   │       ├── controller/
│   │       │   └── AccountController.java
│   │       ├── model/
│   │       │   ├── Account.java
│   │       │   ├── Address.java
│   │       │   ├── AccountStatus.java
│   │       │   └── State.java
│   │       ├── repository/
│   │       │   └── AccountRepository.java
│   │       ├── service/
│   │       │   └── AccountService.java
│   │       ├── events/
│   │       │   ├── AccountUpsertEvent.java
│   │       │   └── EventPublisher.java
│   │       ├── exceptions/
│   │       │   └── CustomExceptionHandler.java
│   │       └── AccountServiceApplication.java
│   └── resources/
│       ├── application.yml
```

---

## **Execução**
1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/account-service.git
cd account-service
```

2. Configure o banco de dados e o RabbitMQ conforme necessário.

3. Execute o serviço:

```bash
mvn clean install
mvn spring-boot:run
```

---

## **Contribuições**

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do repositório.

2. Crie uma branch para sua feature:
```bash
git checkout -b minha-feature
```

3. Faça o commit de suas alterações:
```bash
git commit -m "Minha nova feature"
```

4. Envie suas alterações:
```bash
git push origin minha-feature
```

5. Abra um Pull Request.

---

## **Licença**
Este projeto está licenciado sob a MIT License.
