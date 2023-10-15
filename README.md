# Set Up

## STEP 1 (Set up - Postgres with docker)

We need to execute this command to run the container with PostgresSQL:

- docker run --name postgres -p 5433:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=library -d postgres

