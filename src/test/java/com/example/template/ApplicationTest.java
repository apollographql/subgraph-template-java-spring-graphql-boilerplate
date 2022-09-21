package com.example.template;

import com.example.template.model.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

    @Autowired
    private ExecutionGraphQlService graphQLService;

    @Test
    public void startServer_verifyFooQueryResponse() {
        ExecutionGraphQlServiceTester tester = ExecutionGraphQlServiceTester.create(graphQLService);
        final Foo response = tester.documentName("FooQuery")
            .execute()
            .path("foo")
            .entity(Foo.class)
            .get();

        Assertions.assertEquals("1", response.getId());
        Assertions.assertEquals("Name", response.getName());
    }
}
