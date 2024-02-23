# Introduction
This repo contains 2 Java projects that make use of a REST API containing data from all countries of the world.
# Description
There are 2 Maven projects in this repository. CountryRESTAPI implements 4 operations between the program and the REST API and CountriesApp implements a JavaFX application that makes use of the 4 operations created in CountryRESTAPI.
# API
[REST Countries](https://restcountries.com/v3.1/)
# Operations
1) Get Info of All Countries <br>
2) Get Country Info By Name <br>
3) Get Countries Info By Their Language <br>
4) Get Countries Info By Their Currency <br>
# Dependencies
Gson (For the JSON -> POJO Deserialization)
>        <dependency>
>            <groupId>com.google.code.gson</groupId>
>            <artifactId>gson</artifactId>
>            <version>2.10.1</version>
>        </dependency>
JUnit (For writing Unit Tests)
>        <dependency>
>            <groupId>junit</groupId>
>            <artifactId>junit</artifactId>
>            <version>4.13.2</version>
>            <scope>test</scope>
>        </dependency>
# Maven Coordinates
To add this library as a dependency add the following maven coordinates into your pom.xml file
>	  <dependency>
>        <groupId>org.example</groupId>
>        <artifactId>CountryRESTAPIProject</artifactId>
>        <version>0.0.1-SNAPSHOT</version>
>    </dependency>
>  </dependencies>
