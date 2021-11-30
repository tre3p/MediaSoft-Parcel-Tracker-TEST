FROM ubuntu

RUN apt update \
        && apt install default-jre -y \
        && apt install default-jdk -y \
        && apt install openjdk-11-jdk -y \
        && apt-get -y install make

COPY . /usr/src/app

WORKDIR /usr/src/app

CMD make start

EXPOSE 4000

