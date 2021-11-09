#!/bin/bash
set -e

psql --username "$CHALLENGE_POSTGRES_USERNAME" <<-EOSQL
    CREATE DATABASE challenge OWNER postgres;
    GRANT ALL PRIVILEGES ON DATABASE challenge TO postgres;
EOSQL