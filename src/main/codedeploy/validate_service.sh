#!/bin/sh

until $(curl --output /dev/null --silent --fail http://localhost:8080/info); do
    printf '.'
    sleep 5
done
