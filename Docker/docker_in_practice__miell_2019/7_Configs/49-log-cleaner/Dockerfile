# хотите определить команду с аргументами, которую будет выполнять контейнер
FROM ubuntu:16.04
ADD clean_log /usr/bin/clean_log   
RUN chmod +x /usr/bin/clean_log    
# точка входа как сценарий clean_log
ENTRYPOINT ["/usr/bin/clean_log"]
# аргумент по умолчанию для команды entrypoint (7 дней)
CMD ["7"]

# разница между ENTRYPOINT и CMD. ENTRYPOINT будет всегда запускатся при запуске образа
# даже если для  docker run  указана команда
# переопределить можно только если явно указать --entrypoint команде docker run

# CMD - означает что предоставленный аргумент проверять не нужно

# docker build -t log-cleaner .
# docker run -v /var/log/myapplogs:/log_dir log-cleaner 365


# если не указать количество дней
# docker run -ti log-cleaner /bin/bash
# Cleaning logs over /bin/bash days old
# find: invalid argument '-name' to '-ctime'