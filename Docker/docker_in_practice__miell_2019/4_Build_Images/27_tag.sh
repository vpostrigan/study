#!/bin/bash
x=1
for id in $(docker history -q "docker_in_practice_myimage:latest" | grep -vw missing | tac)
do
    docker tag "${id}" "docker_in_practice_myimage:latest_step_${x}"
    ((x++))
done