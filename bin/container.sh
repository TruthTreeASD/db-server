#!/usr/bin/env bash
docker build -t database_service .

#docker run -p 8080:8080 --name database_service-container -d database_service

docker tag database_service:latest 869798252720.dkr.ecr.us-west-2.amazonaws.com/database_service:latest

docker push 869798252720.dkr.ecr.us-west-2.amazonaws.com/database_service:latest