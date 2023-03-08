# AutoFilter

This is a Java library that lets you automatically generate consistent filters from entities of your project. It also has a JPA integration that allows using the filters to perform queries to the database.


## Why does AutoFilter exist?

This library was partially inspired by the filtering module of [jHipster](https://github.com/jhipster/generator-jhipster) which is a powerful code generator to create fully functional applications from scratch by using good coding standards and practices. However, once created the application can be difficult to maintain, which is a problem in any project.

Nonetheless, the idea behind jHipster and its standard structure to generate queries are very worthy of consideration, and as such this library was born. The core design pillars of this library are:
- Ease of use
- Ease to configure
- Ease to extend

## Features

- **Automatic generation of filters:** the filters are created from the fields of a class with a single annotation
- **JPA integration:** the JPA module allows using the generated filters to easily create queries to a database
- **Easy to use and configure:** by using simple annotations you can configure the filters just the way you need them while maintaining their consistent structure
- **Filters are always up-to-date:** since the filters are automatically generated then new fields that you add will be added to the filters without any need to manually modify the filters. This ensures that entities and filters are always synchronized
- **Easy to extend:** it's very easy to add new kinds of filters that are not supported in the base library. And you can even add some custom logic by creating subclasses of the generated filters

## Usage/Examples

A full example of usage can be found [here.](https://github.com/XiriusTech/java-learning/tree/main/auto-filter-example)

#### Generating a filter
To generate a filter you can use the @AutoFilter annotation:

```java
public enum Region {
    AMERICA,
    EUROPE,
    OTHER
}

@AutoFilter
public class User {
    private Region userRegion;
    private String username;
    private Boolean active;
    private int strikes;
}
```

The generated filter will be:
```java
public class UserFilter implements Filter {
    private RegionFilter userRegion;
    private BasicFilter<String> username;
    private BasicFilter<Boolean> active;
    private BasicFilter<Integer> strikes;
    ...
    public static class RegionFilter extends BasicFilter<Region> {

    }
}
```

Currently the following types are automatically detected:
- Enums
- Primitive types (e.g. int)
- Primitive types boxes (e.g. java.lang.Boolean)
- java.math.BigDecimal
- java.lang.String

#### Customizing a filter

If you need to customize your filter by excluding some fields you can use @AutoFilterExclude.

If you want to include a variable whose type is not supported by default or change the type of filter of a field then you can use @AutoFilterInclude annotation, like so:

```java
@Entity
@AutoFilter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Region userRegion;
}

@Entity
@AutoFilter
public class User {
    @AutoFilterExclude
    private Region userRegion;
    private String username;
    private Boolean active;
    @AutoFilterInclude(baseFilter = CustomFilter.class)
    private int strikes;
    /* Note that we can use a filter generated by @AutoFilter as 
    baseFilter but you MUST avoid cyclical references
    */
    @AutoFilterInclude(baseFilter = PersonFilter.class)
    private Person realPerson;
}
```

Which will generate something like:

```java
public class UserFilter implements Filter {
    private BasicFilter<String> name;
    private BasicFilter<Boolean> active;
    private CustomFilter<Integer> strikes;
    private PersonFilter realPerson;
    ...
}
```

Please note that both generic (e.g. Filter<?>) and non-generic classes (e.g. Filter) are admitted as valid filters as long as they implement the Filter interface. However, it is strongly encouraged to create and use generic filters as long as it's possible as that gives a higher degree of flexibility.

#### Using the filter

You can use the filter in a web request by adding it as a parameter:

```java
@GetMapping("/users")
    public Page<User> findUsers(@Valid UserFilter filter, Pageable pageable) {
        ...
    }
```

The web query may look like this:
```
https://localhost:8080/users?name.equals=James&active.equal=true&realPerson.id.in=1,2
```

#### Types of Filters

The filters created by AutoFilter generally rely on a set of reusable filters of two different kinds:

- ```SingleFilter```: a filter that has a single condition to filter a single field
- ```MultiFilter```: groups multiple SingleFilters

One example of how these filters are used can be seen in the image:
![Different Filters](https://user-images.githubusercontent.com/17957661/221467355-7a7366e2-a656-4606-a4b5-edccfdae4ac5.png)

The green box represents a filter created with the @AutoFilter annotation, the orange boxes are MultiFilters and the Yellow boxes are SingleFilters.

The following SingleFilters are provided Out-of-the-box:
- ```EqualsFilter```
- ```IsNullFilter```
- ```InFilter```
- ```LessThanFilter```
- ```GreaterThanFilter```
- ```LessThanOrEqualToFilter```
- ```GreaterThanOrEqualToFilter```

And the following MultiFilters are provided:
- ```BasicFilter```: contains Equals, IsNull and InFilter
- ```RangeFilter```: contains all of the out-of-the-box SingleFilters

You can extend this by creating your own SingleFilters for other filtering conditions (e.g. a LIKE query), or your own MultiFilters to create new groups of conditions (e.g. a filter using only Equals and IsNull).

**IMPORTANT**: A SingleFilter does nothing without its QueryBuilder! You can see how to create one in the AutoFilter JPA [documentation.](https://github.com/XiriusTech/auto-filter/tree/main/auto-filter-jpa) 

#### Using the JPA integration

The Filters are only a set of parameters by themselves. In order to perform queries to a database, you can use the AutoFilter JPA module.
To know how to use this module please refer to its [documentation.](https://github.com/XiriusTech/auto-filter/tree/main/auto-filter-jpa).


## Installation

You may install the library by including this dependency in your maven project:

```xml
<dependency>
    <groupId>tech.xirius</groupId>
    <artifactId>auto-filter</artifactId>
    <version>0.1.0</version>
</dependency>
```

You must also add the annotation processor to the build configuration:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>${maven.version}</version>
            <configuration>
                <source>${java.version}</source>
                <target>${java.version}</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>tech.xirius</groupId>
                        <artifactId>auto-filter-core</artifactId>
                        <version>${xirius.filter.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

You can also individually include each module, like so:

- AutoFilter Core:
```xml
<dependency>
    <groupId>tech.xirius</groupId>
    <artifactId>auto-filter-core</artifactId>
    <version>0.1.0</version>
</dependency>
```
- JPA integration:
```xml
<dependency>
    <groupId>tech.xirius</groupId>
    <artifactId>auto-filter-jpa</artifactId>
    <version>0.1.0</version>
</dependency>
```


## Feedback

If you have any feedback, please reach out to us by sending an email to tecnologia@xirius.tech.


## Contributing

For now, only members of Xirius Tech may contribute to this project, but this may change in the future.

However, you are free to fork the project and perform your own changes there under the provided license of this software.


## 🚀 About Xirius Tech
Xirius Tech is a company based in Latin America dedicated to the creation of web applications aimed to automate the processes that the client request. To do this we employ traditional technologies like Java and Spring Boot, and mix them with cutting edge advancements in Machine Learning to achieve the best results.

Visit us at [Xirius Tech](https://xirius.tech/) or send us an email directly to tecnologia@xirius.tech.


## Authors

- [@XiriusTech](https://github.com/XiriusTech)
- [@camilonar](https://www.github.com/camilonar)


## License

[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)