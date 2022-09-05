FROM openjdk:17

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}
ENV H2CONSOLE=${H2CONSOLE}

WORKDIR /opt/chat_api

COPY /target/chatapi*.jar chat_api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar chat_api.jar ${H2CONSOLE} --spring.profiles.active=${PROFILE}