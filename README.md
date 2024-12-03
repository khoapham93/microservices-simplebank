handle exception when running docker compose with multiple db postgres:
error: `/docker-entrypoint-initdb.d/init-multiple-databases.sh: /bin/bash: bad interpreter: Permission denied`

run `chmod +x init-multiple-databases.sh` to fix

Ref: https://dev.to/nietzscheson/multiples-postgres-databases-in-one-service-with-docker-compose-4fdf