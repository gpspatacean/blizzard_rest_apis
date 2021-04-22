# Blizzard WoW REST API Client Generator
Generator of Java REST Client SDK on top of Blizzard`s World of Warcraft API 
endpoints described [here](https://develop.battle.net/documentation/world-of-warcraft/game-data-apis). 

## Overview
Blizzard has a series of APIs publicly available. The detailed list can be found on 
[Blizzard`s official website](https://develop.battle.net/documentation/world-of-warcraft/game-data-apis).

This project aims to automatically generate Java SDK Clients to consume the APIs Blizzard is making available,
based on [OpenAPI](https://github.com/OAI/OpenAPI-Specification) specification files, in conjuction with the [openapi-generator](https://github.com/OpenAPITools/openapi-generator).

## Build with
* [Maven](https://maven.apache.org/download.cgi)
* Java 8

## Getting started
#### Application Register
Register a new application with Blizzard from [here](https://develop.battle.net/access/clients). You will be provided with a Client ID and Client Secret.

#### App Bootstrapping
Run `mvn clean install` to build everything - all registered APIs, and a very basic sample.

After this, there are 2 ways to run the sample from [driver-app](driver-app) . 
1. set *BLIZZARD_API_CLIENT_ID*, *BLIZZARD_API_CLIENT_SECRET* with the values obtained from registering the application in [driver-app/pom.xml](driver-app/pom.xml) :
    ```
   <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.2.6.RELEASE</version>
                ...
                <configuration>
                    <environmentVariables>
                        <BLIZZARD_API_CLIENT_ID><!-- Client from application regsiter --></BLIZZARD_API_CLIENT_ID>
                        <BLIZZARD_API_CLIENT_SECRET><!--Secret from application register--></BLIZZARD_API_CLIENT_SECRET>
                    </environmentVariables>
                </configuration>
            </plugin>
        </plugins>
    </build>
   ``` 
   then run `mvn spring-boot:run` from [driver-app](driver-app) .
 2. set *BLIZZARD_API_CLIENT_ID*, *BLIZZARD_API_CLIENT_SECRET* as environment variables. Then
 just run `java -jar blizzard-sdk-driver-app-1.0-SNAPSHOT.jar`

## Swagger-ui Integration
In [tools/swagger-ui-integration](tools/swagger-ui-integration) there is a [Swagger-UI](https://swagger.io/tools/swagger-ui/) based project that can be used to 
visually interact with the exposed APIs in a simple/clean manner. It just renders registered Open API Specification files.

Run it with `java -jar tools\swagger-ui-integration\target\blizzard-swagger-ui-integration-1.0-SNAPSHOT.jar --spring.config.location=<path\to\custom\application.yml>` for SSL Settings,
 or disable SSL with `--server.ssl.enabled=false`

It can also be started with Spring-Boot plugin: `mvn spring-boot:run -Dspring-boot.run.arguments="--spring.config.location=<path/to/custom>/application.yml"`
## Contributing
Adding new modules is straight-forward. You can use the provided Maven Archetype (`com.gspatace:blizzard-rest-client-generator`) in order to generate new modules.
In `<root>/client-apis`, run `mvn archetype:generate -DarchetypeGroupId=com.gspatace -DarchetypeArtifactId=blizzard-rest-client-generator`
. Sample:
```
[INFO] Archetype repository not defined. Using the one from [com.gspatace:blizzard-rest-client-generator:1.0-SNAPSHOT] found in catalog local
 Define value for property 'groupId': com.gspatace
 Define value for property 'artifactId': blizzard-wow-character-client
 Define value for property 'version' 1.0-SNAPSHOT: :
 Define value for property 'package' com.gspatace: :
 Define value for property 'inputSpecFile': wow_character_openapi_spec.yaml
 Define value for property 'mainApiName': wow.character
 Define value for property 'generatedApiPackage' com.gspatace.blizzard.wow.character.api: :
 Define value for property 'generatedModelPackage' com.gspatace.blizzard.wow.character.model: :
 Confirm properties configuration:
 groupId: com.gspatace
 artifactId: blizzard-wow-character-client
 version: 1.0-SNAPSHOT
 package: com.gspatace
 inputSpecFile: wow_character_openapi_spec.yaml
 mainApiName: wow.character
 generatedApiPackage: com.gspatace.blizzard.wow.character.api
 generatedModelPackage: com.gspatace.blizzard.wow.character.model
  Y: :
 ...
[INFO] Project created from Archetype in dir: <root>\client-apis\blizzard-wow-character-client
``` 
This creates the `<artifactId>` module, and adds it to the main parent. The OpenAPI description of the target REST
API has to be placed in `<root>\client-apis\<artifactId>\src\main\resources\<provided-input-spec-file>`. `mvn clean install` will now generate the artifact.

For Swagger-UI integration, a new endpoint controller should be added in [tools\swagger-ui-integration](tools\swagger-ui-integration), 
in `com.gspatace.blizzard.swagger.integration.apis` package. 
See [Auction House API](tools\swagger-ui-integration\src\main\java\com\gspatace\blizzard\swagger\integration\apis\AuctionHouseApi.java)
for reference.

## Available APIs
As per Blizzard`s categories, found on their official [website](https://develop.battle.net/documentation/world-of-warcraft/game-data-apis), currently the following APIs are available:
1. Auction House API

## Authors
* George Spătăcean <george.spatacean@gmail.com>
 