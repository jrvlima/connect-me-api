# Atributo de Qualidade: Interoperabilidade

## Requisito de Qualidade
O sistema deve se comunicar com outras tecnologias.

## Preocupação
O sistema deve ter como resposta a uma requisição uma saída de fácil leitura por outro componente.

## Cenário(s)
**Cenário 1**

### Ambiente
Sistema em operação normal

### Estímulo
O sistema de monitoramento envia uma requisição para o serviço REST do módulo de informações gerenciais.

### Mecanismo
Criar um serviço REST para atender às requisições do sistema de monitoramento.

### Medida de Resposta
Retornar os dados requisitados no formato JSON.

## Considerações sobre a Arquitetura

### Riscos
Alguma instabilidade na rede pode deixar a conexão lenta ou mesmo a perda de pacotes.

### Pontos de Sensibilidade
Não há.

### Tradeoff
Não há.
