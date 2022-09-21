# Example Federated Spring GraphQL Subgraph

This is an example application template that can be used to create Federated GraphQL subgraph using [Spring GraphQL](https://spring.io/projects/spring-graphql).

This example application implements following GraphQL schema:

```graphql
directive @contact(
    "Contact title of the subgraph owner"
    name: String!
    "URL where the subgraph's owner can be reached"
    url: String
    "Other relevant notes can be included here; supports markdown links"
    description: String
) on SCHEMA

schema
@contact(
    name: "FooBar Server Team"
    url: "https://myteam.slack.com/archives/teams-chat-room-url"
    description: "send urgent issues to [#oncall](https://yourteam.slack.com/archives/oncall)."
)
@link(
    url: "https://specs.apollo.dev/federation/v2.0",
    import: ["@key"]
) {
    query: Query
}

type Query {
    foo(id: ID!): Foo
}
type Foo @key(fields: "id") {
    id: ID!
    name: String
}
```

## Build

This project uses [Maven](https://maven.apache.org/) and requires Java 17+ runtime. In order to build the project locally (which
will also execute all the tests), simply run the `clean install` goals.

```shell
./mvnw clean install
```

> NOTE: in order to ensure you use the right version of Maven we highly recommend to use the provided wrapper script

### Code Quality

Build is configured with [`JaCoCo`](https://www.eclemma.org/jacoco/) plugin that measures the code coverage. It is configured
to run as part of the build lifecycle and will generate its report under `target/site`.

Example integration test is provided. It starts up the SpringBoot server and executes example queries against it. Run
`verify` goal to execute tests and calculate the coverage.

```shell
./mvnw clean verify
```

### Continuous Integration

This project comes with some example build actions that will trigger on PR requests and commits to the main branch.

## Run

To start the GraphQL server:

* Run `Application.java` directly from your IDE
* Alternatively you can also run the Spring Boot plugin directly from the command line

```shell script
./mvnw spring-boot:run
```

Once the app has started you can explore the example schema by opening the GraphQL Playground endpoint at http://localhost:8080/playground.

## Additional Resources

* [Spring GraphQL documentation](https://spring.io/projects/spring-graphql)
* [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/)
* [Maven documentation](https://maven.apache.org/)


