# 🛍️ CleanEcommerce

## 🌐 Visão Geral do Projeto

Este é um projeto de API REST para um sistema de e-commerce utilizando a arquitetura limpa (Clean Architecture) com o framework Spring Boot. A aplicação oferece funcionalidades completas de backend, incluindo:

- Autenticação de usuários com JWT
- Gerenciamento de produtos, pedidos e clientes
- Envio de e-mails transacionais via SendGrid
- Geração de relatórios em formato CSV
- Documentação da API com OpenAPI/Swagger

## ☕ Clean Architecture

Foi utilizada a arquitetura limpa porque esta aplicação inicial ainda não possui todos os elementos externos definidos, como o banco de dados, que inicialmente será o PostgreSQL. Essa abordagem garante flexibilidade e independência dos frameworks.

## 🚀 Tecnologias Principais

- **Java**: Versão 21 
- **Spring Boot**: Versão 3.4.1
- **Banco de Dados**: PostgreSQL
- **Gerenciamento de Dependências**: Maven

## 🔧 Principais Recursos

- Autenticação JWT
- Controle de Usuários e Permissões
- Gestão de Produtos e Pedidos
- Integração com SendGrid para E-mails
- Geração de Relatórios em CSV
- Documentação de API com OpenAPI/Swagger

## 📦 Dependências Chave

- Spring Data JPA
- Spring Security
- Flyway Migration
- JUnit
- Mockito
- SendGrid
- OpenCSV
- Auth0 JWT
- java-dotenv

## 💽 Migração de Banco de Dados

### Estrutura de Migração

- **V1**: Criação inicial das tabelas
- **V2**: Insert de usuários

## 💻 Configurações de Ambiente Mínimas (.env)

Configure as variáveis de ambiente no arquivo `.env` para que a aplicação funcione corretamente:

```dotenv
SENDGRID_API_KEY=SG.eQv5qfWlSSyzG

DB_URL=jdbc:postgresql://localhost:5432/cleanEcomerce
DB_USERNAME=postgres
DB_PASSWORD=admin
```

##🔐 Configuração de Segurança

### 🚪 Registro de Usuário
> **Nota**:  
O administrador inicial da aplicação foi inserido diretamente no banco de dados durante as migrações.

#### Usuário Administrador Inicial
``` json
{
    "login": "Administrador",
    "password": "senha"
}
```

#### Exemplo de Registro
```
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "login": "adm",
    "email": "teste@email.com",
    "password": "senha",
    "tipo": "ADMIN"
}'
```

##🛡️ Configurações de Segurança
Autenticação baseada em JWT
Controle de acesso por roles

##💸 Teste de Pagamento
Para simular o processo de pagamento, usamos um microserviço em Python que valida as requisições. Aqui está o exemplo:

```
from flask import Flask, request, jsonify
import logging

# Configuração do logger
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

app = Flask(__name__)

@app.route('/api/pagamento', methods=['POST'])
def validar_pagamento():
    data = request.json

    # Log da requisição recebida
    logging.info(f"Requisição recebida: {data}")

    # Validação dos campos do JSON
    if "pedido_id" in data and "valor_total" in data:
        if data["valor_total"] > 0:
            return jsonify({
                "status": "Pagamento Efetuado"
            }), 202
        elif data["valor_total"] == 0:
            return jsonify({
                "status": "Pagamento Não Efetuado"
            }), 402
        else:
            return jsonify({"erro": "Valor total inválido"}), 400
    
    return jsonify({"erro": "Dados inválidos"}), 400

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
```

### Exemplo de Requisição
```
curl --location 'http://localhost:5000/api/pagamento' \
--header 'Content-Type: application/json' \
--data '{"valor_total": 113212,"pedido_id": 1}'
```

## 🔗 Links Úteis
Repositório: Spring_ecomerce
Documentação da API: http://localhost:8080/swagger-ui.html

## 📋 Próximos Passos

Planejamos implementar os seguintes recursos nas próximas versões do projeto:

- [ ] adicionar camada de use case (usuário)
- [ ] adicionar camada de interfaceAdapter (usuário)
- [ ] Revisar migração V2
- [ ] adicionar camada de entidade (produto)
- [ ] adicionar camada de use case (produto)
- [ ] adicionar camada de interfaceAdapter (produto)
- [ ] adicionar validação de usuário com JWT
- [ ] adicionar camada de entidade (pedido/pedido item)
- [ ] adicionar camada de use case (pedido/pedido item)
- [ ] adicionar camada de interfaceAdapter (pedido/pedido item)
- [ ] Ajustar configurações de segurança
- [ ] Implementar validações adicionais
- [ ] Dockerizar aplicação
- [ ] Subir para AWS

##📧 Contato

[LinkedIn](https://www.linkedin.com/in/felipe-alves-3223191bb)
