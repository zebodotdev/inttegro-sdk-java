# syntax=docker/dockerfile:1.7

FROM maven:3.9-eclipse-temurin-17@sha256:a8746f15d5bb26b5b8bacb056cc76211553850f4c71d16aff845cfa004cbc197 AS base
WORKDIR /app
COPY pom.xml ./
RUN mvn -B -q -DskipTests dependency:go-offline
COPY src ./src

# Build Maven artifacts for distribution
FROM base AS dist
RUN mvn -B -DskipTests package
RUN mkdir -p /out && cp -R target /out/

# CI target (use in GitHub Actions)
FROM base AS ci
RUN mvn -B test

# Local/development target
FROM base AS dev
CMD ["bash"]
