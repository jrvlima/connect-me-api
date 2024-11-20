# Confiabilidade
- **Atributo de Qualidade**: Confiabilidade
- **Requisito de Qualidade**: O sistema deve garantir durabilidade durante falhas planejadas ou não.
- **Preocupação**: Evitar perda de transações durante reinícios do servidor.

**Cenário(s):**  
**Cenário 1**
- **Ambiente**: Durante manutenção do sistema.
- **Estímulo**: Um cliente tenta agendar um serviço durante a reinicialização do servidor.
- **Mecanismo**: Sistema de fila de transações.
- **Medida de Resposta**: 100% de durabilidade das transações durante reinicializações.
- **Considerações Arquiteturais**: Implementar filas robustas para persistência.
- **Riscos**: Perda de dados caso as filas não sejam corretamente configuradas.
- **Pontos de Sensibilidade**: Capacidade da fila de suportar alto volume.
- **Tradeoff**: A introdução de filas pode adicionar complexidade.  