#!/bin/bash

SONAR_URL="http://localhost:9000"
SONAR_LOGIN="28468fb8a8bc98e17f6dc981eae0a87c563e2d51"
SONAR_BRANCH="localtest"
SONAR_PROJECT_NAME="event-driven-api-pub1"

./gradlew clean sonarqube build \
    -Dsonar.projectKey=${SONAR_PROJECT_NAME}
    -Dsonar.host.url=${SONAR_URL} \
    -Dsonar.login=${SONAR_LOGIN} \
    -x detekt \
    -x spotlessKotlinCheck