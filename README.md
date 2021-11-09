# Instalação local:

## Configurando o projeto rodando um shell script:

Necessário possuir o docker instalado na sua máquina, já que vamos utiliza-lo para o projeto.<br>

O script vai exportar as variáveis de ambiente necessárias, fazer o build da imagem, montar e rodar o container
do postgres.<br>
Para executar o script utilize o comando: ```source ./setup_project.sh```

# Caso a instalação com o shell script não funcionar siga os seguintes passos para subir o ambiente localmente:

## Variáveis de ambiente:
Para rodar o projeto, é necessário possuir as seguintes variáveis de ambiente:<br>

export CHALLENGE_POSTGRES_URL=jdbc:postgresql://localhost:5432/challenge<br>
export CHALLENGE_POSTGRES_USERNAME=postgres<br>
export CHALLENGE_POSTGRES_PASSWORD=dev1234#<br>
export CHALLENGE_JWT_SECRET=dGhpcyBpcyB0aGUgc2VjcmV0IDop<br><br>

## Rode a imagem docker do postgres:

execute o comando a seguir para fazer o build da imagem:

```docker image build . -t postgres-db```<br>

Execute o comando a seguir para iniciar o container docker que chamarei de postgres-db

```docker run --name postgres-db -p 5432:5432 -e POSTGRES_PASSWORD=$CHALLENGE_POSTGRES_PASSWORD -d postgres-db```

## Instalação dos componentes necessários concluída

Agora basta rodar a aplicação spring boot que o flyway vai executar as migrations definidas em<br>

```./src/main/resources/db/migration```

# Informações úteis
## CORS

O servidor está permitindo apenas requisições vindas de http://localhost:3000.
