# Java Spring Federated GraphQL Subgraph

[![Deploy on Railway](https://railway.app/button.svg)](https://railway.app/new/template/Jomu73?referralCode=xsbY2R)

This is an example application template that can be used to create Federated GraphQL subgraph using [Spring GraphQL](https://spring.io/projects/spring-graphql). You can use this template from [Rover](https://www.apollographql.com/docs/rover/commands/template/) with `rover template use --template subgraph-java-spring-graphql`.

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

Once the app has started you can explore the example schema by opening the GraphQL Playground endpoint at http://localhost:8080/playground and begin developing your supergraph with `rover dev --url http://localhost:8080/graphql --name my-sugraph`.

## Apollo Studio Integration

1. Set these secrets in GitHub Actions:
    1. APOLLO_KEY: An Apollo Studio API key for the supergraph to enable schema checks and publishing of the
       subgraph.
    2. APOLLO_GRAPH_REF: The name of the supergraph in Apollo Studio.
    3. PRODUCTION_URL: The URL of the deployed subgraph that the supergraph gateway will route to.
2. Set `SUBGRAPH_NAME` in .github/workflows/checks.yaml and .github/workflows/deploy.yaml
3. Remove the `if: false` lines from `.github/workflows/checks.yaml` and `.github/workflows/deploy.yaml` to enable schema checks and publishing.
4. Write your custom deploy logic in `.github/workflows/deploy.yaml`.
5. Send the `Router-Authorization` header [from your Cloud router](https://www.apollographql.com/docs/graphos/routing/cloud-configuration#managing-secrets) and set the `ROUTER_SECRET` environment variable wherever you deploy this to.

## Additional Resources

* [Spring GraphQL documentation](https://spring.io/projects/spring-graphql)
* [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/)
* [Maven documentation](https://maven.apache.org/)
