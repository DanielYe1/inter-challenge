## Back-end trabalho de projeto de software

Aplicação back-end de um sistema de usuários e cálculo de digitos únicos.

## Como compilar o código

Para rodar o projeto é necessário a utilização do java versão 8+, maven, docker e docker-compose.

Para rodar apenas os testes do projeto:

```
./mvnw test
```

Para rodar a aplicação basta rodar os comandos:

  ```
  docker-compose up -d
  sh run.sh
  ```
o script run.sh vem com os comandos necessários para recompilar o projeto e rodar o artefato gerado.

A aplicação utiliza a porta padrão 8080.

Para realizar os testes, pode-se utilizar o dump do projeto. Basta rodar o script de restaurar o dump, com o comando:

```
    sh restore-dump.sh
```

Os testes utilizados no postman podem ser encontrados no arquivo InterChallenge.postman_collection.json

## Decisões
Foram utilizados dois objetos referentes a usuários, um possuindo apenas os dados de nome e email, e outro contendo os
 dados sobressalentes, isso foi mapeado dessa forma a fim de proteger a integridade dos digitos verificadores.
 
A cache utiliza o padrão LRU.

Referente ao cálculo do digito verificador, temos que a soma do digito da primeira String N se mantém mesmo se multiplicado 
pela quantidade de vezes que a mesma se repete em K. Para a solução proposta, foi calculado o valor do digito verificador de N
 e após isso multiplicado pelo valor de vezes que o mesmo se repete, e calculado novamente seu valor de digito verificador.
 
Referente a criptografia, foi implementado um endpoint para armazenamento das chaves públicas. Os usuários que possuírem 
chaves cadastradas terão seus dados criptografados automaticamente. 

Com esse modelo,os usuários não terão os dados criptografados durante o cadastro, mas isso poderia ser resolvido com o 
recebimento via header ou pelo próprio DTO de usuário.