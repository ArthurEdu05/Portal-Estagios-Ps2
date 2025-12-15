# Portal de Estágios - Backend

Este é o repositório do **backend** do Portal de Estágios, uma API RESTful completa desenvolvida com **Java 21** e **Spring Boot 3**. A API foi projetada para ser o núcleo do [Portal de Estágios](https://github.com/ArthurEdu05/Portal-Estagios-PS2-NextApplication), fornecendo todos os endpoints necessários para a lógica de negócio, gerenciamento de dados e segurança da aplicação.

## Arquitetura e Visão Geral

O Portal de Estágios adota uma arquitetura de microsserviços, desacoplando a interface do usuário (frontend) da lógica de negócio e persistência de dados (backend). Esta abordagem garante manutenibilidade, escalabilidade e a separação clara de responsabilidades entre as camadas da aplicação.

-   **Backend (este repositório):** Desenvolvido como uma API RESTful, este projeto é responsável por toda a lógica de negócio, pela comunicação com o banco de dados PostgreSQL via Supabase e expor um conjunto de endpoints seguros e bem definidos. Ele serve como a única fonte de verdade para os dados, garantindo consistência e integridade através de suas operações.
-   **Frontend:** Desenvolvido em Next.js e disponível em um [repositório separado](https://github.com/ArthurEdu05/Portal-Estagios-PS2-NextApplication), atua como o cliente da API. Ele consome os endpoints fornecidos pelo backend para renderizar a interface, gerenciar a interação do usuário e apresentar os dados de forma dinâmica, sem nunca acessar o banco de dados diretamente.

## Funcionalidades da API

A API fornece endpoints para suportar todas as funcionalidades exigidas pelos painéis de estudantes, empresas e administradores, incluindo:

-   **Autenticação:** Sistema de login para os três perfis de usuário (Estudante, Empresa, Administrador).
-   **Gerenciamento de Usuários:** Operações de CRUD para os perfis de estudantes, empresas e administradores.
-   **Gerenciamento de Vagas de Estágio:** Endpoints completos para que empresas possam criar, listar, atualizar, encerrar e reabrir suas vagas.
-   **Inscrição em Vagas:** Permite que estudantes se candidatem às vagas disponíveis.
-   **Gerenciamento de Áreas de Interesse:** CRUD para as áreas de interesse que categorizam as vagas e os perfis dos estudantes.
-   **Consultas e Filtros:** Endpoints otimizados para listagem e filtragem de vagas e usuários.

## Documentação da API (Swagger)

A API está documentada com **Springdoc (Swagger UI)**. Após iniciar a aplicação, a documentação interativa fica disponível para consulta e teste de todos os encontros.

-   **URL da Documentação:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Tecnologias Utilizadas

-   **Java 21:** Versão mais recente da linguagem Java.
-   **Spring Boot 3:** Framework principal para a construção da aplicação.
-   **Spring Data JPA:** Para persistência de dados de forma simplificada.
-   **PostgreSQL (via Supabase):** O banco de dados relacional deste projeto está hospedado e gerenciado pelo **Supabase**. Todas as tabelas e a persistência de dados já estão configuradas e em pleno funcionamento neste ambiente.
-   **Springdoc (Swagger):** Para documentação completa da API.
-   **Lombok:** Para reduzir o código e deixar o desenvolvimento mais produtivo (getters, setters, construtores).
-   **Maven:** Gerenciador de dependências e build do projeto.

## Pré-requisitos

-   **Java (JDK) 21**
-   **Maven 3.8+**
-   O projeto já está configurado para utilizar o banco de dados **'Portal Estagios' no Supabase**. Não é necessário criar um novo banco, apenas garantir que as credenciais corretas sejam fornecidas e o projeto esteja ativo no supabase (apenas colaboradores conseguem alterar).

## Configuração e Execução

### 1. Banco de Dados (Supabase - Projeto 'Portal Estagios')

Este projeto já utiliza o banco de dados PostgreSQL **'Portal Estagios'**, hospedado e gerenciado pelo **Supabase**. Para que a aplicação funcione corretamente, você precisa obter as credenciais de conexão (URL, usuário e senha) deste projeto Supabase existente, o que já esta aplicado =)

### 2. Executando o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/ArthurEdu05/Portal-Estagios-Ps2.git
    cd Portal-Estagios-Ps2
    ```

2.  **Compile e execute com o Maven Wrapper:**
    
    Apenas rode o projeto! Mas se quiser ir pelo terminal:

        ./mvnw spring-boot:run
    
   

A API estará disponível em `http://localhost:8080`.

### 4. Executando o Ecossistema Completo

Para que o Portal de Estágios funcione em sua totalidade, o **frontend** e o **backend** devem ser executados simultaneamente. Essa interação simula um ambiente de produção real, onde o cliente (frontend, rodando no navegador do usuário) se comunica com o servidor (backend) por meio de requisições HTTP.

1.  **Inicie o Backend (esta aplicação)**
2.  **Inicie o Frontend:** Siga as instruções no [repositório do frontend](https://github.com/ArthurEdu05/Portal-Estagios-PS2-NextApplication) para iniciar a aplicação Next.js. Ela irá, por padrão, se comunicar com os endpoints do backend em `localhost:8080` para buscar e enviar dados.

Com ambas as aplicações em execução, você terá a experiência completa do Portal de Estágios. E poderá através do **frontend** interagir com o sistema.