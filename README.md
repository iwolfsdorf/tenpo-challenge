# Tenpo Challenge

## Requirements
- [Docker](https://www.docker.com/ "Docker") installed
- Linux console (In Windows SO you can use Git Bash)
- Postman
- Maven (You can install maven with [SdkMan](https://sdkman.io))
- Java 11 (You can install java with [SdkMan](https://sdkman.io))

## Usage

1. Download code from [tenpo-challenge](https://github.com/iwolfsdorf/tenpo-challenge)
2. Go to ~/tenpo-challenge/
3. Run `$ mvn clean install -U`
4. Run `$ docker-compose up`
5. In Postman import collection from tenpo-challenge.postman_collection.json
6. Execute request **GetResults** or **Sum two numbers with percentage**
7. Once you finish run `$ docker-compose down`