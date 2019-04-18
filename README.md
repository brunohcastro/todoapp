# Todo APP

DEMO: https://todoing-app.herokuapp.com

### TechStack

- Bootstrap 4.3.1
- Primefaces 6.2
- Mojarra JSF 2.3.9
- SpringBoot 2.1.4
- SpringFramework 5.1.6
- HSQLDB 2.4.1
- Flyway 5.2.4

### App React

O app em React está disponível em:  
Repositório: https://github.com/brunohcastro/todoapp-react  
DEMO: https://todoing-react.herokuapp.com

## Itens contemplados

Os itens do desafio que foram cumpridos por essa implementação são: A. B. C. D. E. F. G. H. e I.

## Sobre o sistema

A aplicação usa o SpringBoot e o SpringFramework para configurar todas as bibliotecas.
O Hibernate é usado como implementação do JPA e está conectado a um banco de dados HSQLDB.
O sistema faz uso do Flyway para migração de banco de dados no lugar da geração automática de esquema
oferecido pelo Hibernate.

O Spring Data JPA fornece o repositório que é acessado pela camada de serviço, que age como a barreira transacional do sistema.
A validação fica por conta das anotações presentes no modelo.

Dois controladores consomem a interface do serviço disponível. Um dos controladores é o BackingBean da página JSF. 
O outro controlador é um controlador REST que permite o consumo da API pela app React.

## Rodando o sistema

Para iniciar o sistema, execute:

`$ ./gradlew bootRun`

no Linux, ou:

`> gradlew.bat bootRun`

no Windows.

## Licença

Copyright (c) 2019 Bruno Henrique de Castro

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
