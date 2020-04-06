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

## Available APIs
As per Blizzard`s categories, found on their official [website](https://develop.battle.net/documentation/world-of-warcraft/game-data-apis), currently the following APIs are available:
1. Auction House API

## Authors
* George Spătăcean <george.spatacean@gmail.com>
 