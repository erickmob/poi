Author: [erickmob](https://github.com/erickmob)
<br/>mail: erickmob@gmail.com
#Tecnologias utilizadas:
* Java 12
* Maven 3.6.1
* Spring boot 2.1.5
* Spring JPA
* Spring Security
* JWT Authentication
* h2database
* Lombok
* Swagger
* Integration Testing with @SpringBootTest

#Instructions
Clonar o repositório e executar o comando abaixo para baixar as dependências e rodar os testes:

        mvn clean install
        
Rodar o comando abaixo para subir a aplicação:
        
        mvn spring-boot:run
 
A aplicação usa autenticação JWT, então para todas as requisições (menos get, /auth e swagger) deverá conter no header:
 * Key - Authorization
 * Value Bearer SEUTOKEN
 * Key   Content-Type
 * Value application/json
 
 Para obter seu token JWT, basta fazer a seguinte requisição:
 
    curl -X POST "http://localhost:8080/auth/signin" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"admin\", \"username\": \"admin\"}"
 
 O retorno deverá conter o token. Exemplo:
 
    {
      "username": "admin",
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNTYwMzA1NjY0LCJleHAiOjE1NjAzMDkyNjR9.jLVWUT36AcNs7PEVGVV7auL6ctsdZfp9LyD_cmZkG34"
    }


##Serviço para cadastrar pontos de interesse 
Cadastrar pontos de interesse com 3 atributos:

* Nome do POI (ponto de intersse)
* Coordenada X (inteiro não negativo)
* Coordenada Y (inteiro não negativo).

Exemplo de requisição:

    curl -X POST "http://localhost:8080/api/poi" -H "accept: */*" -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNTYwMzA1NjY0LCJleHAiOjE1NjAzMDkyNjR9.jLVWUT36AcNs7PEVGVV7auL6ctsdZfp9LyD_cmZkG34" -d "[ { \"x\": 27, \"y\": 12 }, { \"x\": 31, \"y\": 18 }, { \"x\": 15, \"y\": 12 }, { \"x\": 19, \"y\": 21 }, { \"x\": 12, \"y\": 8 }, { \"x\": 23, \"y\": 6 }, { \"x\": 28, \"y\": 2 }]"
   
   

##Serviço para listar todos os POIs cadastrados
Exemplo de requisição:

    curl -X GET "http://localhost:8080/api/poi" -H "accept: */*"
    
##Serviço para listar POIs por proximidade
Este serviço receberá uma coordenada X e uma coordenada Y, especificando um ponto de referência, bem como uma distância máxima (d-max) em metros.

O serviço deverá retornar: todos os POIs da base de dados que estejam a uma distância menor ou igual a d-max a partir do ponto de referência.

Exemplo de requisição:

    curl -X POST "http://localhost:8080/api/poi/nearby" -H "accept: */*" -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNTYwMzA1NjY0LCJleHAiOjE1NjAzMDkyNjR9.jLVWUT36AcNs7PEVGVV7auL6ctsdZfp9LyD_cmZkG34" -d "{ \"distance\": 10, \"x\": 20, \"y\": 10}"
    
##Swagger:
[Link Swagger](http://localhost:8080/swagger-ui.html#/User/changePasswordUsingPOST) <br/>
Fazer requisição para logar (usarname: admin, password:admin) e receber o token em:<br/>
![alt text](auth.png)

Clicar no botão "Authorize" para adicionar o token e poder fazer as outras requisições:
![alt text](auth2.png)

Exemplo de model para enviar para salvar os Pois mencionados na descrição do teste:

       [
         {
           "x": 27,
           "y": 12
         },
         {
           "x": 31,
           "y": 18
         },
         {
           "x": 15,
           "y": 12
         },
         {
           "x": 19,
           "y": 21
         },
         {
           "x": 12,
           "y": 8
         },
         {
           "x": 23,
           "y": 6
         },
         {
           "x": 28,
           "y": 2
         }
       ]


