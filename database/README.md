# Database

This is the database of the system. It is a MySql Docker container, built with the ```docker-compose.yaml``` file.

## Schema

The schema is designed using the ```data.sql``` file.

The command to build the container is:

## Environment variables

All variables used to run the container are stored into an ```environment``` file.

The command to build the container is:

```bash
docker-compose --env-file .env up
```
