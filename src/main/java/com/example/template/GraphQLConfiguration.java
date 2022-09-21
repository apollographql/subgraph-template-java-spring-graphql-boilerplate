package com.example.template;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.apollographql.federation.graphqljava.tracing.FederatedTracingInstrumentation;
import com.example.template.model.Foo;
import com.example.template.services.FooService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfiguration {

    @Autowired
    private FooService fooService;

    @Bean
    public FederatedTracingInstrumentation federatedTracingInstrumentation() {
      return new FederatedTracingInstrumentation();
    }

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        return builder -> builder.schemaFactory((registry, wiring) ->
                Federation.transform(registry, wiring)
                        .fetchEntities(env ->
                            env.<List<Map<String, Object>>>getArgument(_Entity.argumentName).stream().map(reference -> {
                                final String typeName = (String)reference.get("__typename");
                                return switch (typeName) {
                                    case "Foo" -> fooService.resolveFooReference(reference);
                                    default -> null;
                                };
                            }).collect(Collectors.toList())
                        )
                        .resolveEntityType(env -> {
                            final Object src = env.getObject();
                            if (src instanceof Foo) {
                                return env.getSchema().getObjectType("Foo");
                            } else {
                                return null;
                            }
                        })
                        .build()
        );
    }
}
