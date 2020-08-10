#!/usr/bin/env bash

mvn clean -U package -P $TEMPLATE_ENV  -DskipTests=true