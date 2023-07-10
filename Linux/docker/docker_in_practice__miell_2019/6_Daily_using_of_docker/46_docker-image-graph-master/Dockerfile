FROM ubuntu

MAINTAINER ian.miell@gmail.com

RUN apt-get update

RUN DEBIAN_FRONTEND=noninteractive apt-get install -y graphviz ruby-dev gem build-essential
RUN gem install docker-api sinatra

ADD . /usr/src/app/
WORKDIR /usr/src/app
RUN chmod +x image-graph.sh

CMD [""]
ENTRYPOINT ["./image-graph.sh"]
