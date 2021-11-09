FROM postgres:13.4-alpine
COPY create_db.sh /docker-entrypoint-initdb.d/