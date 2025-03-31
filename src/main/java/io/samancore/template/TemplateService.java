package io.samancore.template;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TemplateService {
    private static final Logger log = Logger.getLogger(TemplateService.class);
    private static final String PREFIX_KEY = "template-";
    private static final String DASH = "-";
    private final ReactiveKeyCommands<String> keyCommands;
    private final ReactiveValueCommands<String, Object> countCommands;

    public TemplateService(ReactiveRedisDataSource reactive) {
        countCommands = reactive.value(Object.class);
        keyCommands = reactive.key();
    }

    public Uni<Object> getValue(String module, String product, String template) {
        log.debugf("TemplateService.getValue product: %s, template: %s", product, template);
        var key = PREFIX_KEY.concat(module).concat(DASH).concat(product).concat(DASH).concat(template);
        return countCommands.get(key);
    }

    public Uni<Void> setValue(String module, String product, String template, Object value) {
        log.debugf("TemplateService.setValue product: %s, template: %s", product, template);
        var key = PREFIX_KEY.concat(module).concat(DASH).concat(product).concat(DASH).concat(template);
        return countCommands.set(key, value);
    }

    public Uni<Void> delValue(String module, String product, String template) {
        log.debugf("TemplateService.delValue product: %s, template: %s", product, template);
        var key = PREFIX_KEY.concat(module).concat(DASH).concat(product).concat(DASH).concat(template);
        return keyCommands.del(key).replaceWithVoid();
    }
}
