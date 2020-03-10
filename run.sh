#!/bin/sh
app=file-sorting-app.jar
mvn clean package
cd target
mv file-sorting-app-1.0-SNAPSHOT.jar $app
java -jar $app