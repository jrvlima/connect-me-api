# Segurança
- **Atributo de Qualidade**: Segurança
- **Requisito de Qualidade**: O sistema deve proteger contra tentativas de força bruta.
- **Preocupação**: Garantir que contas não sejam comprometidas.

## Cenário(s):
### Cenário 1
- **Ambiente**: Internet pública.
- **Estímulo**: Um usuário tenta realizar login com credenciais incorretas múltiplas vezes.
- **Mecanismo**: Serviço de autenticação.
- **Medida de Resposta**: Bloquear a conta após 5 tentativas falhas e notificar o usuário via e-mail.
- **Considerações Arquiteturais**: Implementar proteção contra força bruta no backend.
- **Riscos**: Possíveis falsos positivos ao bloquear usuários legítimos.
- **Pontos de Sensibilidade**: Lógica para detecção de tentativas repetidas.
- **Tradeoff**: Aumentar a segurança pode reduzir a usabilidade.  