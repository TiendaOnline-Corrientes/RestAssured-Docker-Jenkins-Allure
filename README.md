# Summary Project 
this project contains a rest assured test using technologies like JAVA 1.8 version, Maven,
Rest assured, Docker, Allure and Jenkins.

## Requirements
install allure and add to allure bin folder in your environments variables.

### Run allure report
once test has been running, go to allure-result folder and execute this command `allure serve` afterward tests report will be created.

#### To run test
exucute the next command  `mvn clean install` 

##### To start docker container
execute those commands, first `docker ps` to see all created containers and copy id of the container
that you want to choose and then run this command to start execution `docker run CONTAINERHASH` for instance -> docker start 56b8e38f59b7