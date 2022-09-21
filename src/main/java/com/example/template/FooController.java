package com.example.template;

import com.example.template.model.Foo;
import com.example.template.services.FooService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FooController {

    @Autowired
    private FooService fooService;

    @QueryMapping
    public Foo foo(@NotNull @Argument String id) {
        return fooService.resolveById(id);
    }
}
