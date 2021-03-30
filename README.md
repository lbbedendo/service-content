# Content Service

Esse projeto é uma refatoração de um projeto existente aplicando os conceitos de arquitetura hexagonal.

O projeto é constituído por dois módulos:

* `core`: Módulo responsável por conter as classes de domínio e casos de uso. Idealmente, ele deve ser livre de detalhes de implementação (framework, banco de dados, etc). Esse estágio ainda não foi totalmente atingido pois esse projeto é a refatoração de um projeto existente, por isso ainda é possível encontrar dependências de frameworks e outros projetos.
* `app`: Módulo onde a aplicação é implementada de fato, sendo, neste caso, uma API RESTful utilizando o framework [Micronaut](https://micronaut.io/) e banco de dados [MongoDB](https://www.mongodb.com/).

Tecnologias utilizadas:

* JDK 11
* Micronaut
* MongoDB
* JUnit 5 e AssertJ

Aviso: Esse projeto é um estudo de caso para o aprendizado de arquitetura hexagonal, portanto, não o considere como finalizado :).