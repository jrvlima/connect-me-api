# Escalabilidade
- **Atributo de Qualidade**: Escalabilidade
- **Requisito de Qualidade**: O sistema deve suportar picos de usuários sem queda de desempenho.
- **Preocupação**: Evitar falhas durante períodos de alta demanda.

**Cenário(s):**  
**Cenário 1**
- **Ambiente**: 10.000 usuários simultâneos acessando o sistema.
- **Estímulo**: O sistema enfrenta um aumento de usuários durante um feriado nacional.
- **Mecanismo**: Sistema web e banco de dados com escalabilidade horizontal.
- **Medida de Resposta**: Manter <5% de aumento no tempo de resposta sob carga.
- **Considerações Arquiteturais**: Usar balanceadores de carga e clusters.
- **Riscos**: Insuficiência de recursos para atender à demanda.
- **Pontos de Sensibilidade**: Capacidade do banco de dados e largura de banda do servidor.
- **Tradeoff**: A escalabilidade pode aumentar os custos de infraestrutura. 