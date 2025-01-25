# Changelog
Todas as alterações do projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), e este projeto segue o [Versões Semânticas](https://semver.org/spec/v2.0.0.html).

---

## [1.0.0] - 2025-01-25

### Adicionado
- Suporte para criação de contas com informações de Pessoa Jurídica (CNPJ, Razão Social, Nome Fantasia, etc.).
- Integração com RabbitMQ para publicar eventos de criação/atualização de conta na fila `account-upsert`.
- Estrutura de endereços detalhada, com suporte para estado (via `ENUM`), CEP, complemento, bairro, cidade, e número.
- Estrutura de projeto baseada em boas práticas com separação de responsabilidades

---

## Como Usar Este Changelog

- **Adicionado**: Funcionalidades novas introduzidas no serviço.
- **Alterado**: Ajustes ou melhorias em funcionalidades existentes.
- **Removido**: Funcionalidades que foram descontinuadas ou removidas.
- **Corrigido**: Erros e bugs corrigidos.

---

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

