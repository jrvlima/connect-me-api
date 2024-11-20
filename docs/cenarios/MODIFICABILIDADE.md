# Modificabilidade
- **Atributo de Qualidade**: Modificabilidade
- **Requisito de Qualidade**: O sistema deve permitir mudanças rápidas com interrupção mínima.
- **Preocupação**: Garantir que novas categorias sejam adicionadas sem impacto negativo nos usuários.

**Cenário(s):**  
**Cenário 1**
- **Ambiente**: Sistema em produção com atividade de usuários em andamento.
- **Estímulo**: Um administrador solicita a adição de uma nova categoria de serviço (ex.: adestramento de animais).
- **Mecanismo**: Modelo de dados do backend e UI.
- **Medida de Resposta**: A nova categoria aparece no sistema em até 1 semana, sem downtime.
- **Considerações Arquiteturais**: Uso de arquitetura modular e pipelines automatizados.
- **Riscos**: Mudanças no modelo de dados podem causar problemas imprevistos.
- **Pontos de Sensibilidade**: Dependência entre serviços e esquema de banco de dados.
- **Tradeoff**: Aumentar a modificabilidade pode reduzir a eficiência do sistema.  