# Resources:

## Environment variables:

CHALLENGE_POSTGRES_URL<br>
CHALLENGE_POSTGRES_USERNAME<br>
CHALLENGE_POSTGRES_PASSWORD<br><br>

## Fundamental SQL script:

CREATE DATABASE challenge OWNER postgres;<br><br>

## Docker

```docker run --name postgres-c -p 5432:5432 -e POSTGRES_PASSWORD=$NIMBO_CORE_POSTGRES_PASSWORD -d postgres```