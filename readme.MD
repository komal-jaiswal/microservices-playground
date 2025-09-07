Git repo url: https://github.com/komal-jaiswal/microservices-config.git

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

hookdeck
url : https://console.hookdeck.com
brew install hookdeck/hookdeck/hookdeck
hookdeck login --cli-key 1nrzzranio95zu4z7b1bh1t9s4gdgsuz2u82g4pf41x6curg12
hookdeck listen 8071 source --cli-path /monitor

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


