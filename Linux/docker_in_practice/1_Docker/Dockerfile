# 1) Создание образа Docker

# определение базового образа
FROM node
# автор
LABEL maintainer v@gmail.com
# установка git, клонирование проекта
RUN git clone -q https://github.com/docker-in-practice/todo.git
# переход в папку to do, теперь это папка по умолчанию
WORKDIR todo
# установка npm и зависимости nodejs приложения. Вывод консоли в '/dev/null'
RUN npm install > /dev/null
# контейнеры из собранного образа должны прослушивать этот порт
EXPOSE 8000
# команда при запуске контейнера
CMD ["npm", "start"]

# 2) Собираем образ Docker
# $ docker build .

# ...
# => [4/4] RUN npm install > /dev/null                                                                                               42.6s
# => exporting to image                                                                                                               2.1s
# => => exporting layers                                                                                                              2.1s
# => => writing image sha256:165fd87dc20fb49231a534f68f0d0af8f5ecbbc89894f3868ef0b081b2b47940                                         0.0s

# $ docker tag 165fd87dc20fb49231a534f68f0d0af8f5ecbbc89894f3868ef0b081b2b47940 docker_in_practice_todoapp

# 3) Start контейнер Docker
# $ docker run -i -t -p 8000:8000 --name docker_in_practice_todoapp1 docker_in_practice_todoapp

# > todomvc-swarm@0.0.1 prestart
# > make all

# npm install
# npm WARN EBADENGINE Unsupported engine {
# npm WARN EBADENGINE   package: 'minifyify@4.4.0',
# npm WARN EBADENGINE   required: { node: '0.10.x' },
# npm WARN EBADENGINE   current: { node: 'v19.3.0', npm: '9.2.0' }
# npm WARN EBADENGINE }

# up to date, audited 273 packages in 10s

# 11 packages are looking for funding
#   run `npm fund` for details

# 23 vulnerabilities (3 moderate, 12 high, 8 critical)

# To address issues that do not require attention, run:
#   npm audit fix

# To address all issues (including breaking changes), run:
#   npm audit fix --force

# Run `npm audit` for details.
# if [ ! -e dist/ ]; then mkdir dist; fi
# cp node_modules/react/dist/react.min.js dist/react.min.js
# (node:35) [DEP0128] DeprecationWarning: Invalid 'main' field in '/todo/node_modules/find-parent-dir/package.json' of 'find-parent-dir.js'.
#  Please either fix that or report it to the module author
# (Use `node --trace-deprecation ...` to show where the warning was created)
# (node:47) [DEP0128] DeprecationWarning: Invalid 'main' field in '/todo/node_modules/find-parent-dir/package.json' of 'find-parent-dir.js'.
#  Please either fix that or report it to the module author
# (Use `node --trace-deprecation ...` to show where the warning was created)

# LocalTodoApp.js:9:    // TODO: default english version
# LocalTodoApp.js:84:            fwdList = this.host.get('/TodoList#'+listId); // TODO fn+id sig
# TodoApp.js:117:        // TODO scroll into view
# TodoApp.js:176:        if (i>=list.length()) { i=list.length()-1; } // TODO .length
# local.html:30:    <!-- TODO 2-split, 3-split -->
# model/TodoList.js:29:        // TODO one op - repeated spec? long spec?
# view/Footer.jsx:61:        // TODO: show the entry's metadata
# view/Footer.jsx:80:            todoList.addObject(new TodoItem()); // TODO create default
# view/Header.jsx:25:        // TODO list some meaningful header (apart from the id)


# > todomvc-swarm@0.0.1 start
# > node TodoAppServer.js

# Swarm server started port 8000
# incomingWS /


# 4) Статус
# $ docker ps -a
# CONTAINER ID   IMAGE                        COMMAND                  CREATED         STATUS                          PORTS
#                NAMES
# 367d0ab99d5e   docker_in_practice_todoapp   "docker-entrypoint.s…"   7 minutes ago   Exited (1) About a minute ago
#                docker_in_practice_todoapp1
# 3c05e3d3d825   mysql:8.0.28                 "docker-entrypoint.s…"   2 weeks ago     Exited (255) 23 minutes ago     0.0.0.0:3306->3306/tcp, 33060/tcp   MySQLContainer8.0.28
# 3c75151955af   postgres                     "docker-entrypoint.s…"   2 weeks ago     Exited (0) 2 weeks ago
#                postgresql-container
# cdfee63b3328   alpine/git                   "git clone https://g…"   2 weeks ago     Exited (0) 2 weeks ago
#                repo

# 4) Статус
# $ docker ps

# 5) Старт контейнера в фоновом режиме
# $ docker start docker_in_practice_todoapp1

# 6) Какие файлы были изменены в контейнере по сравнению с образом
# $ docker diff docker_in_practice_todoapp1
