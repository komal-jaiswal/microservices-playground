Git repo url: https://github.com/komal-jaiswal/microservices-config.git

steps to run without docker compose:
Run rabbitmq locally
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
Run config server - springboot app
Run Eureka server - service discovery
open sqlectron application
run mysql image for three DB's -> Account,loan,cards
1. accounts db
   docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql
2. cards db
   docker run -p 3307:3306 --name cardsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cardsdb -d mysql
3. loans db
   docker run -p 3308:3306 --name loansdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loansdb -d mysql
Test DB connection in sqlectron for all 3 DB's
Run account ms, followed by loans and cards (springboot application)
4. check all three microservices are listed in Eureka server also gateway -->http://localhost:8761
5. using actuator endpoint of api gateway 
Run API gateway
6. official doc spring cloud gateway-please read https://spring.io/projects/spring-cloud-gateway

Current issues:
1. unable to start eureka server
2. docker compose AMD issue

mvn clean install
java -jar filename-SNAPSHOT-1.0.0.jar 

config externalizing commands
1. CLI - java run snapshot.jar --<property>=<propery val>
2. JVM - 
3. Env variable -

docker commands
docker build
mvn compile jib:dockerBuild
mvn compile jib:dockerBuild -Dimage=jaiswalkomal18/cards:s6    
docker image push docker.io/jaiswalkomal18/configserver-microservice:s6
docker compose up -d

jib:dockerbuild ---->local build of docker images
jib:build --->remote build

option 1: rebuild images with jib then Re-run compose
cd accounts
mvn clean compile jib:dockerBuild -Dimage=accounts:s7
docker compose up -d
Jib will rebuild the local image.
When you restart Compose, it will use the new image.
If a container is already running, add --force-recreate:
docker-compose up -d --force-recreate --build

option 2: push to dockerhub then pull images and then run compose
cd accounts
mvn clean compile jib:build -Dimage=jaiswalkomal18accounts -->since we have used jib:build this generate remote image not local one
docker compose pull
docker compose up -d
 To check service is up
docker exec -it eureka-server curl http://localhost:8761/actuator/health
hookdeck
url : https://console.hookdeck.com
brew install hookdeck/hookdeck/hookdeck
hookdeck login --cli-key 1nrzzranio95zu4z7b1bh1t9s4gdgsuz2u82g4pf41x6curg12
hookdeck listen 8071 source --cli-path /monitors

RabbitMq image :- docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management

#mysql commands
1. accounts db
docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql
2. cards db 
docker run -p 3307:3306 --name cardsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cardsdb -d mysql
3. loans db
docker run -p 3308:3306 --name loansdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loansdb -d mysql
download SQLETRON
   https://github.com/sqlectron/sqlectron-gui/releases/tag/v1.38.0


