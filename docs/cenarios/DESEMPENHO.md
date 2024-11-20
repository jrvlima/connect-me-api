# Desempenho
- **Atributo de Qualidade**: Desempenho
- **Requisito de Qualidade**: O sistema deve oferecer respostas rápidas para pesquisas.
- **Preocupação**: A experiência do usuário pode ser prejudicada por atrasos no carregamento.

## Cenário(s):

### Cenário 1

- Este objetivo é focado no tempo total de resposta percebido pelo usuário ao fazer algo no sistema, incluindo possíveis delays causados por carga no sistema ou lógica mais complexa no backend.
- Nesse caso, ≤ 2 segundo é aceitável porque envolve o tempo que o usuário percebe, mas ainda assim deve estar dentro de limites aceitáveis para a experiência do usuário.
- Requisição do usuário pode ser mais complexa: Envolve mais lógica, como múltiplos endpoints sendo chamados, agregações, ou queries pesadas.
- Rede e outros fatores externos: Latência da rede, client-side delays ou a necessidade de múltiplas interações entre o frontend e o backend.
- Tempo total de processamento: Pode incluir não apenas o tempo de resposta do backend, mas também o tempo de renderização no cliente.

- **Ambiente**: Sob condições normais de carga.
- **Estímulo**: Um cliente busca um prestador de serviço (ex.: encanador).
- **Mecanismo**: Solução completa: Frontend, Backend, Banco de Dados, cache ...
- **Medida de Resposta**: Tempo de resposta ≤ 2 segundos em 95% dos casos.
- **Considerações Arquiteturais**: Otimizar, regras de negócios, consultas no banco de dados e caches, renderização de componentes de usuários e número/tamanho de requisições ao Backend.
- **Riscos**: Consultas mal projetadas podem causar lentidão.
- **Pontos de Sensibilidade**: Rede, Renderização de Componentes, Desempenho do banco de dados e tempo de execução da lógica de negócios.
- **Tradeoff**: Investir em performance pode aumentar os custos operacionais.

### Cenário 2

- O objetivo é estritamente sobre o tempo que o backend leva para processar cada requisição.
- Cada requisição é avaliada individualmente.
- Isso significa que, mesmo com 1000 requisições chegando ao mesmo tempo, cada uma deve ser atendida em menos de 100ms, sem que o sistema "engargale".
- O sistema precisa atender alta carga simultaneamente, garantindo que nenhuma requisição leve mais que 100ms para ser processada.

- **Ambiente**: Sob condições normais de carga.
- **Estímulo**: 1000 requisições são enviadas a API de backend no intervalo de 1 seg e cada requisição deve ser processada em menos de 100ms.
- **Mecanismo**: Backend do serviço de busca.
- **Medida de Resposta**: Tempo de resposta ≤ 100ms em 95% dos casos.
- **Considerações Arquiteturais**: Otimizar lógica de negócios, consultas no banco de dados e caches.
- **Riscos**: Consultas mal projetadas podem causar lentidão.
- **Pontos de Sensibilidade**: Desempenho do banco de dados e tempo de execução da lógica de negócios.
- **Tradeoff**: Investir em performance pode aumentar os custos operacionais. 

## Orientações Gerais de melhoria de Desempenho:
### Indexing and Query Optimization
- Full-Text Search
- Otimizar consultas de banco de dados
- Paginação
### Redução do tamanho da resposta
- Compressão
- DTO
- Lazy Loading
### Cache
- In-Memory Cache
- Cache de Consultas
### Processamento Assíncrono
- Filas
- Processamento em Background
- Websockets
- Reactive Programming
### Infrastrustura Escalável
- Load Balancer
- Auto-Scaling
- CDN
- API Gateway
### Monitoramento
- Logs
- Métricas
- Alertas
- Tracing
