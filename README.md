# OnSales

Sistema de vendas desenvolvido para estudo e prática de arquitetura moderna com foco em eventos, mensageria e boas práticas de backend.

## 🚀 Visão Geral

O **OnSales** é um projeto criado para simular um sistema de vendas real, inicialmente em formato monolítico, com evolução planejada para microserviços orientados a eventos utilizando **Kafka** e **RabbitMQ**.

O objetivo principal é aprofundar conceitos como:
- Comunicação assíncrona
- Processamento de eventos
- Idempotência
- Retry e Dead Letter Topic (DLT)
- Observabilidade

---

## 🧱 Arquitetura

- Monólito modular (fase inicial)
- Evolução para microserviços https://github.com/MuriloRicardoPrincipe/OnSales-Micro
- Arquitetura orientada a eventos (Event-Driven Architecture)

Serviços planejados:
- Venda
- Estoque
- Produto
- Controle de venda e estoque

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Java jwt
- Spring Retry
- Spring Boot DevTools
- Spring Web
- Spring Data JPA
- Lombok
- Validation
- MySQL Driver
- Flyway Migration

---

## 📦 Estrutura do Projeto

```text
onsales/
├── venda-service
├── estoque-service
├── produto-service
├── cliente-service
└── README.md
