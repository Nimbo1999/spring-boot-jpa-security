#!/bin/bash

if ! type docker >/dev/null 2>&1 
then
    echo "docker not found"
    exit 1
fi

export CHALLENGE_POSTGRES_URL=jdbc:postgresql://localhost:5432/challenge
export CHALLENGE_POSTGRES_USERNAME=postgres
export CHALLENGE_POSTGRES_PASSWORD=dev1234#
export CHALLENGE_JWT_SECRET=dGhpcyBpcyB0aGUgc2VjcmV0IDop

docker image build . -t postgres-db && docker run --name postgres-db -p 5432:5432 -e POSTGRES_PASSWORD=$CHALLENGE_POSTGRES_PASSWORD -d postgres-db
