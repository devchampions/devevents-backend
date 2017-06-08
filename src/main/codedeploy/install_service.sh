#!/bin/sh

sudo rm -rf /etc/init.d/devevents-backend
sudo ln -s /webapps/devevents-backend.jar /etc/init.d/devevents-backend
