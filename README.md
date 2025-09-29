# iso19139-metadata-jaxb

The iso19139-metadata-jaxb project contains the JAXB model for ISO 19139

Additional project information, javadocs, and test coverage is located at https://ci-cmg.github.io/project-documentation/iso19139-metadata-jaxb/

## Adding To Your Project

jakarta.xml.bind-api is required to use this library. Add it as a dependency to your project with your choice of implementation.

Add the following dependency to your Maven pom.xml

```xml
    <dependency>
      <groupId>io.github.ci-cmg</groupId>
      <artifactId>iso19139-metadata-jaxb</artifactId>
      <version>1.0.0</version>
    </dependency>

    <!-- additional XML dependencies -->
    <dependency>
      <groupId>jakarta.xml.bind</groupId>
      <artifactId>jakarta.xml.bind-api</artifactId>
      <version>4.0.2</version>
    </dependency>
    <!-- use an implementation of your choice -->
    <dependency>
      <groupId>org.glassfish.jaxb</groupId>
      <artifactId>jaxb-runtime</artifactId>
      <version>4.0.5</version>
   </dependency>
```
