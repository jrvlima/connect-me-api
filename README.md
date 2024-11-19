# connect-me-api
## Overview
ConnectMe é uma plataforma de rede social que conecta usuários com base em suas habilidades e serviços. Os usuários podem encontrar e reservar serviços de outros usuários, gerenciar suas habilidades e construir uma rede de conexões. A plataforma é projetada como um conjunto de microsserviços que lidam com diferentes aspectos da rede social, como gerenciamento de usuários, catalogação de serviços, reservas e gerenciamento de conexões.

Este é um MVP com o propósito de validar alguns conceitos, requisitios não funcionais, alguns atributos de qualidade e a arquitetura de microsserviços, tanto quanto avaliar a arquitetura usando ATAM.

Para simplificação do MVP e das primeiras versões do sistemas os microserviços são organizadas em um único artefato e implementadas como um monolítico, onde cada microserviço representa um pacote no código fonte.

## Visão Geral dos Microsserviços e Fluxo de Dados
Cada serviço deve ter seu próprio banco de dados, permitindo escalabilidade e isolando a lógica de domínio.

Nas primeiras versões cada pacote usa um um DB diferente na mesmo servidor Postgres, tendo um `Data Source` por pacote;

- User Service: Lida com perfis de usuário, pessoas, endereços, ...
- Skill Management Service: Contém habilidades e categorias de uma pessoa.
- Booking Service: Gerencia solicitações de serviços e reservas.
- Serviço de Gerenciamento de Habilidades: Lida com operações CRUD de habilidades.
- Work History Service: Armazena todas as transações de serviços entre usuários.
- Connection Service: Gerencia o gráfico de conexões da rede social.
- Payment Service: Lida com pagamentos e transações financeiras.
- ...

## Scenarios ATAM
### Scenario 1: Performance
#### Full-text Search

```plaintext
Bitmap Heap Scan on persons  (cost=13.59..312.28 rows=146 width=679) (actual time=0.067..0.406 rows=146 loops=1)
  Recheck Cond: (search_vector @@ '''john'''::tsquery)
  Heap Blocks: exact=109
  ->  Bitmap Index Scan on persons_search_idx  (cost=0.00..13.55 rows=146 width=0) (actual time=0.038..0.038 rows=146 loops=1)
        Index Cond: (search_vector @@ '''john'''::tsquery)
Planning Time: 0.325 ms
Execution Time: 0.436 ms
```
#### Regular Search

```plaintext
Seq Scan on persons  (cost=0.00..551.00 rows=142 width=679) (actual time=0.026..5.237 rows=154 loops=1)
Filter: ((firstname)::text ~~* '%John%'::text)
Rows Removed by Filter: 9846
Planning Time: 0.512 ms
Execution Time: 5.263 ms
```

```plaintext
➜  connect-me-api git:(main) ✗ tree -I 'build|bin|gradle|__pycache__|test|resources'
.
├── HELP.md
├── README.md
├── build.gradle
├── compose.yaml
├── db
│   ├── init-db
│   │   └── init-multiple-dbs.sql
│   ├── mock_data.py
│   ├── people-full-text-search.sql
│   └── skills-full-text-search.sql
├── gradlew
├── gradlew.bat
├── locustfile.py
├── requirements.txt
├── settings.gradle
└── src
    └── main
        └── java
            └── io
                └── zeecode
                    └── connectmeapi
                        ├── ConnectMeApiApplication.java
                        ├── config
                        │   ├── BookingDataSourceConfig.java
                        │   ├── ConnectionDataSourceConfig.java
                        │   ├── JpaConfig.java
                        │   ├── SkillManagementDataSourceConfig.java
                        │   └── UsersDataSourceConfig.java
                        ├── domain
                        │   └── BaseEntity.java
                        └── services
                            ├── auth
                            ├── booking
                            │   ├── model
                            │   │   ├── Person.java
                            │   │   ├── PersonSkill.java
                            │   │   ├── ServiceAgreement.java
                            │   │   ├── Skill.java
                            │   │   └── SkillCategory.java
                            │   └── repositories
                            │       └── ServiceAgreementRepository.java
                            ├── connection
                            │   ├── model
                            │   │   ├── Friendship.java
                            │   │   ├── FriendshipStatus.java
                            │   │   └── Person.java
                            │   └── repositories
                            │       └── FriendshipRepository.java
                            ├── review
                            ├── skillmanagement
                            │   ├── model
                            │   │   ├── Person.java
                            │   │   ├── PersonSkill.java
                            │   │   ├── Skill.java
                            │   │   └── SkillCategory.java
                            │   └── repositories
                            │       ├── PersonSkillRepository.java
                            │       ├── SkillCategoryRepository.java
                            │       └── SkillRepository.java
                            ├── user
                            │   ├── model
                            │   │   ├── Address.java
                            │   │   ├── Person.java
                            │   │   ├── Phone.java
                            │   │   └── User.java
                            │   └── repositories
                            │       ├── AddressRepository.java
                            │       ├── PersonRepository.java
                            │       ├── PhoneRepository.java
                            │       └── UserRepository.java
                            └── workhistory
```

![img.png](docs/domain.png)