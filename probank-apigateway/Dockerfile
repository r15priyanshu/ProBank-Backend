FROM eclipse-temurin:17.0.10_7-jre-alpine
LABEL maintainer="r15priyanshu" website="https://www.linkedin.com/in/r15priyanshu/"
# Install curl
RUN apk update && apk add --no-cache curl
RUN mkdir -p /app/probank
WORKDIR /app/probank
COPY target/probank-apigateway.jar .
EXPOSE 9090
CMD ["sh", "-c", "java $JAVA_OPTS -jar probank-apigateway.jar"]