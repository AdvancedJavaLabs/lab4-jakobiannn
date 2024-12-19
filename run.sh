#!/bin/bash

JAR_PATH="/Users/alexeychuyko/IdeaProjects/lab4-jakobiannn/build/libs/lab4-jakobiannn-1.0-SNAPSHOT.jar"
JAR_DEST="/tmp/lab4-jakobiannn-1.0-SNAPSHOT.jar"
CONTAINER_NAME="namenode"

echo "Copying JAR file to container..."
docker cp "$JAR_PATH" "$CONTAINER_NAME:$JAR_DEST"

echo "Executing Hadoop job..."
docker exec -it "$CONTAINER_NAME" hadoop jar "$JAR_DEST" v1.SalesAnalysis