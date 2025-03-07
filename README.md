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