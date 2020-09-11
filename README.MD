# XML Test Convertor 

## Description

All requirements for this project you can read [here](Тестовое%20задание%20Java%20-%20XML,%20JDBC.doc).

## Before start
Change properties in [application.yml](src/main/resources/application.yml).
Add your DB driver to [pom.xml](pom.xml).
In this case we use MySQL DB.

Make sure you set a right credentials for DB connection and n.


```yml
url: "jdbc:mysql://localhost:3306/TEST?rewriteBatchedStatements=true"
username: "root"
password: "password"
driver: "com.mysql.jdbc.Driver"

n: 10000
```
## Read more about libraries
[Yml parser](https://www.javadoc.io/doc/org.yaml/snakeyaml/1.19/org/yaml/snakeyaml/Yaml.html).

[Java Architecture for XML Binding](https://docs.oracle.com/javase/8/docs/api/javax/xml/bind/JAXB.html).

[Transforming XML via XSLT](https://docs.oracle.com/javase/tutorial/jaxp/xslt/transformingXML.html).

## Links with samples of usage
[Yml parser sample](https://www.baeldung.com/java-snake-yaml).

[Creating XML based on entity sample](https://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/).

[Converting existing XML by template](https://docs.oracle.com/javase/tutorial/jaxp/xslt/transformingXML.html).

[Getting values from XML](https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm).