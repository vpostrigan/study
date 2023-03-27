#!/bin/bash
# Note that if you are using Mac/Windows then the volume mount of docker may not work.
# dockerinpractice/image-stepper    is image from DockerHub
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock dockerinpractice/image-stepper myimage

docker images | grep myimage
#docker_in_practice_myimage              latest            21742c344f6a   48 minutes ago   124MB
#docker_in_practice_myimage              latest_step_1     21742c344f6a   48 minutes ago   124MB

docker run myimage:latest_step_8 ls / | grep file      # shows created files for this step
file1
file2
file3
..
file7