package com.example.template.services;

import com.example.template.model.Foo;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    public Foo resolveById(@NotNull String id) {
        if ("1".equals(id)) {
            return new Foo("1", "Name");
        } else {
            return null;
        }
    }

    public Foo resolveFooReference(@NotNull Map<String, Object> reference) {
        if (reference.get("id") instanceof String fooId) {
            return resolveById(fooId);
        } else {
            return null;
        }
    }
}
