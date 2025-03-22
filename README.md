# DM112 – Trabalho Final da Disciplina

## Pós-Graduação em Desenvolvimento Mobile e Cloud Computing – Inatel
### Prof. Roberto Ribeiro Rocha

### Trabalho 1 – Análise, modelagem e projeto orientados a serviços (30 pontos)
Para esta atividade, a dupla deve criar um arquivo PDF contendo todos os passos
de análise, modelagem e projeto orientados a serviços para o Provedor de Logística do
projeto do curso, contendo os seguintes requisitos:

- Consultar a lista de pedidos a serem entregues
- Registrar a entrega de um pedido
- Enviar um e-mail para o cliente quando o pedido for entregue

As fronteiras de análise são as seguintes:
- O entregador consulta a lista de pedidos a serem entregues
- O entregador registra a entrega de um pedido
- O sistema acessa o servidor de e-mails.

Informações sobre a entrega:
- Identificação do pedido
- CPF da pessoa que recebeu o pedido
- Data e hora da entrega do pedido

### Trabalho 2 – Implementação do serviço de logística (30 pontos)

Para esta atividade, a dupla deve criar os seguintes itens:
- Criar o serviço de logística utilizando REST e armazenar no banco de dados de sua
escolha (em memória ou arquivo).
- Integrar o serviço de e-mail (utilizado na aula) a este novo serviço de logística.

Atenção: o serviço de envio de e-mail necessita de um pequeno ajuste, pois aqui
não é necessário enviar o PDF como anexo.

### Comentários sobre os Serviços:

- **EntregaDM112**: Implementação do sistema de Entrega para os pedidos.
 - Integração com o serviço PedidoDM112 para obter a lista de pedidos a serem entregues
 - Integração com o serviço PedidoDM112 para atualizar o status do pedido
 **Obs.: Acrescentados novos status na lista de ENUM (SHIPPED | DELIVERED)**

 - Integração com o serviço UtilityDM112 para envio de e-mail
 

Projetos reaproveitados do conteúdo apresentado em aula:

- **PagamentoDM112**: Implementação do serviço de orquestração do pagamento. Usa cliente de boleto, pedido e email para consumir os outros serviços.

- **PedidoDM112**: Implementa o serviço de pedidos (REST). Salva os dados do pedido em banco de dados H2.

- **UtilityDM112**: Implementa os serviços de Email e Boleto.

### Instruções para acesso

**Métodos GET - Pedidos:**
[http://localhost:9090/PedidoDM112/api/orders]
[http://localhost:9090/PedidoDM112/api/orders/1]
[http://localhost:9090/PedidoDM112/api/orders/customer/111.111.111-11]


**Método POST para inserir novo Pedido:**

URL: http://localhost:9090/PedidoDM112/api/orders

```
{
"cpf": "111.111.111-11",
"value": 1000.0,
"status": 0,
"orderDate": 1587158424651
}
```

**Métodos PUT para atualizações do Status:**
http://localhost:9090/PedidoDM112/api/orders/1/pending
http://localhost:9090/PedidoDM112/api/orders/1/confirmation
http://localhost:9090/PedidoDM112/api/orders/1/shipped
http://localhost:9090/PedidoDM112/api/orders/1/delivered

**Métodos GET - Pagamento:**
[http://localhost:8080/PagamentoDM112/api/startPaymentOfOrder/222.222.222-22/2]
[http://localhost:8080/PagamentoDM112/api/confirmPaymentOfOrder/222.222.222-22/2]

**Métodos GET - Entrega:**
[http://localhost:6060/EntregaDM112/api/startDelivery/222.222.222-22/2]
[http://localhost:6060/EntregaDM112/api/confirmDelivery/222.222.222-22/2]
