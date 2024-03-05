package io.samancore;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemplateService {
    private static final String PREFIX_KEY = "template-";
    private static final String DASH = "-";
    private final ReactiveKeyCommands<String> keyCommands;
    private final ReactiveValueCommands<String, Object> countCommands;

    public TemplateService(ReactiveRedisDataSource reactive) {
        countCommands = reactive.value(Object.class);
        keyCommands = reactive.key();
    }

    public Uni<Object> getValue(String product, String template) {
        var key = PREFIX_KEY.concat(product).concat(DASH).concat(template);
        return countCommands.get(key);
    }

    public Uni<Void> setValue(String product, String template, Object value) {
        var key = PREFIX_KEY.concat(product).concat(DASH).concat(template);
        return countCommands.set(key, value);
    }
    public Uni<Void> delValue(String product, String template) {
        var key = PREFIX_KEY.concat(product).concat(DASH).concat(template);
        return keyCommands.del(key)
                .replaceWithVoid();
    }
}
