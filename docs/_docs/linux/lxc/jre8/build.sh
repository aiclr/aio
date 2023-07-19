#!/bin/bash

image_name="server-jre"
version="8u311-alpine"

echo -e "\n==> begin delete all containers of " $image_name
docker rm $(docker stop $(docker ps -a | grep $image_name | awk '{print $1}'))

echo -e "\n==> begin delete all images of " $image_name
docker rmi -f $(docker images | grep $image_name | awk '{print $3}')

echo -e "\n==> begin build your images of " $image_name
docker build -f Dockerfile -t $image_name:$version .

echo -e "\n==> begin to package your image to tar file"
docker save $image_name:$version > ./$image_name-$version.tar