#!/bin/bash

# Ensure, that docker-compose stopped
docker-compose stop

# Delete old containers
docker container prune -f

# Pull new changes
git pull

. ./setJava17.sh
#read -p "Press enter to continue"
echo $JAVA_HOME
#read -p "Press enter to continue"

# Prepare image
docker build --tag autoparts .

# Run docker compose
docker-compose up --build -d