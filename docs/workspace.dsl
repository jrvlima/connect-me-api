workspace "conectme" "Conectme é uma plataforma que conecta prestadores de serviço e clientes" {

    model {
        u = person "User"
        paypal = softwareSystem "Paypal" "Sistema de pagamento"
        cognito = softwareSystem "AWS Cognito" "Sistema de autenticação"
        apigw = softwareSystem "AWS API Gateway" "Redirects HTTP requests to the backend"  "API Gateway"

        ss = softwareSystem "conectme" {
            wa = container "Web Application" "Interfaces de Usuário" "Javascrit and Angular" "Web Browser"
            ma = container "Mobile" "Interfaces de Usuário" "Javascript and Ionic" "Mobile"

            conectmebackend = container "Conectme Backend" "Aplicação Backend" "Spring Boot" {
                description "Regras de negócio do Conectme"
                connection = component "Conectme Connection Service" "Serviço de Rede Social para Clientes e Prestadores" "Spring Boot" "Spring Boot"
                booking = component "Conectme Booking Service" "Serviço de Contratação de Serviços" "Spring Boot" "Spring Boot"
                skills = component "Conectme Skills Service" "Serviço para gerenciar as habilidades de prestadores de serviços" "Spring Boot" "Spring Boot"
                persons = component "Conectme Person Service" "Serviço para gerenciar os perfirs de pessoas, clientes e prestadores" "Spring Boot" "Spring Boot"
                payments = component "Conectme Pagamento" "Serviço de Pagamento entre Clientes e Prestadores" "Spring Boot" "Spring Boot"
                work_history = component "Conectme Booking History Service" "Serviço para gerenciar o histórico de serviços contratados" "Spring Boot" "Spring Boot"
                review = component "Conectme Review Service" "Serviço para gerenciar as avaliações de serviços" "Spring Boot" "Spring Boot"
            
            }

            conectmedb = container "Conectme Database" "Postgres" {
                description "Persistência de dados do Conectme"
                tags "Database" 
                connection_db = component "Conectme Connection Database" "Serviço de conexão com o banco de dados" "Postgres" "Postgres"
                booking_db = component "Conectme Booking Database" "Serviço de conexão com o banco de dados" "Postgres" "Postgres"
                skills_db = component "Conectme Skills Database" "Serviço de conexão com o banco de dados" "Postgres" "Postgres"
                persons_db = component "Conectme Person Database" "Serviço de conexão com o banco de dados" "Postgres" "Postgres"
                payments_db = component "Conectme Pagamento Database" "Serviço de conexão com o banco de dados" "Postgres" "Postgres"
                work_history_sdb = component "Conectme Booking History Database" "Serviço de conexão com o banco de dados" "Postgres" "Postgres"
                review_db = component "Conectme Review Database" "Serviço de conexão com o banco de dados" "Postgres" "Postgres"
                
            }
        }

        u -> wa "Uses"
        u -> ma "Uses"
        ma -> apigw "HTTP requests"
        wa -> apigw "HTTP requests"
        apigw -> conectmebackend "HTTP Redirect"
        conectmebackend -> conectmedb "Reads from and writes to"

        ma -> cognito "Authenticate"
        wa -> cognito "Authenticate"

        payments -> paypal "Uses"

        apigw -> cognito "Authenticate"

        apigw -> connection "HTTP Redirect"
        apigw -> booking "HTTP Redirect"
        apigw -> skills "HTTP Redirect"
        apigw -> persons "HTTP Redirect"
        
        apigw -> payments "HTTP Redirect"
        apigw -> work_history "HTTP Redirect"
        apigw -> review "HTTP Redirect"


        connection -> connection_db "Reads from and writes to"
        booking -> booking_db "Reads from and writes to"
        skills -> skills_db "Reads from and writes to"
        persons -> persons_db "Reads from and writes to"
        payments -> payments_db "Reads from and writes to"
        work_history -> work_history_sdb "Reads from and writes to"
        review -> review_db "Reads from and writes to"
        
        persons -> cognito "Authenticate"
        booking -> cognito "Validate JWT"
        skills -> cognito "Validate JWT"
        payments -> cognito "Validate JWT"
        work_history -> cognito "Validate JWT"
        review -> cognito "Validate JWT"
    }

    views {
        systemContext ss "Diagram1" {
            include *
        }

        container ss "Diagram2" {
            include *
            exclude "ma"
        }

        component conectmebackend "Todos" {
            include booking persons skills payments work_history review connection apigw cognito ma u
            
            exclude "payments -> cognito"
            exclude "skills -> cognito"
            exclude "work_history -> cognito"
            exclude "review -> cognito"
            exclude "connection -> cognito"
            
        }

        component conectmebackend "Contrata" {
            include booking persons apigw  ma u cognito persons_db booking_db
            exclude "apigw -> persons"
        }

        styles {
            element "Element" {
                color #ffffff
            }
            element "Person" {
                background #2E8B57
                shape person
            }
            element "Software System" {
                background #ffffff
                color #000000
            }
            element "Container" {
                background #4682B4
            }
            element "Component" {
                background #4B0082
            }
            element "Web Browser" {
                shape WebBrowser
                background #7B68EE
            }
            element "Database" {
                shape cylinder
            }
            element "Mobile" {
                shape MobileDevicePortrait
                background #FF6B81
            }
            element "Spring Boot" {
                background #696969
            }
            element "API Gateway" {
                width 1800
                height 150
                stroke #ffffff
                strokeWidth 3
                background #696969
                border dashed
            }
        }
    }

    configuration {
        scope softwaresystem
    }

}