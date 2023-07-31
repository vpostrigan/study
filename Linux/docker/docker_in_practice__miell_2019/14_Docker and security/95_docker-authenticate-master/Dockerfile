# docker-authenticate
# Sets up basic HTTP authentication on your docker daemon.
# $ docker run -d -p 2375:2375 -v /var/run:/var/run dockerinpractice/docker-authenticate
FROM debian:8.2
RUN apt-get update && apt-get install -y nginx apache2-utils
RUN htpasswd -c /etc/nginx/.htpasswd username
RUN htpasswd -b /etc/nginx/.htpasswd username password
RUN sed -i 's/user .*;/user root;/' /etc/nginx/nginx.conf
ADD etc/nginx/sites-enabled/docker /etc/nginx/sites-enabled/docker
CMD service nginx start && sleep infinity
