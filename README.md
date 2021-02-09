# productclient
Contract testing demo project using pact

Run docker-compose up

There are project in this folder 

1) pactconsumer : 
Write ProductServiceContractTest file under test/java/ which will generate pact file accordinglgy 
Generate pact file using pact consumer
Run mvn clean install which will generate pact folder and pact file inside it

2) pactprovider
Behaving as provider  
Copy the pact folder from consumer and paste it in parellel to src folder.
Start the application
Run mvn clean install which will validate the contract against API
