# Requisitos
1- Possuir docker instalado para subir a imagem do postgres;<br>
2- Adicione as variáveis de ambiente; * O script (```setup_project.sh```) executa o export das variáveis<br><br>
```
export CHALLENGE_POSTGRES_URL=jdbc:postgresql://localhost:5432/challenge<br>
export CHALLENGE_POSTGRES_USERNAME=postgres<br>
export CHALLENGE_POSTGRES_PASSWORD=dev1234#<br>
export CHALLENGE_JWT_SECRET=dGhpcyBpcyB0aGUgc2VjcmV0IDop<br><br>
```
<hr><br>

# Configurando o projeto e rodando com o shell script:<br>

O script foi testado nos SO Windows(Com o git bash) e no Mac OS terminal padrâo.<br>

O script vai exportar as variáveis de ambiente necessárias, fazer o build da imagem, montar e rodar o container
do postgres e por fim executar o mvn package seguido pelo java -jar.<br><br>

Para executar o script utilize o comando em seu terminal:&nbsp;&nbsp;&nbsp;```source ./setup_project.sh```
<hr><br>

# Caso a instalação com o shell script não funcionar siga esse passo a passo alternativo:

## 1 - Faça o build da imagem do postgres(docker):

```docker image build . -t postgres-db```<br>

## 2 - Execute o comando a seguir para iniciar o container em segundo plano

```docker run --name postgres-db -p 5432:5432 -e POSTGRES_PASSWORD=$CHALLENGE_POSTGRES_PASSWORD -d postgres-db```

## 3 - Faça o build da .jar ignorando os testes com o seguinte comando:

```mvn package -Dmaven.test.skip```

## 4 - Inicie a aplicação com os seguintes comandos

```cd target && java -jar nimbo1999-0.0.1-SNAPSHOT.jar```

# Informações úteis

As instruções para subir o front-end da aplicação estão no repositório https://github.com/Nimbo1999/spring-jpa-security-front

### CORS
<hr>
O servidor está permitindo apenas requisições vindas de http://localhost:3000.<br><br>

## Dúvidas sobre como subir a aplicação? Entre em contato pelo WhatsApp: (61) 98151-0636
