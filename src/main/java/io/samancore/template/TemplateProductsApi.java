package io.samancore.template;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/templates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TemplateProductsApi {
    private static final Logger log = Logger.getLogger(TemplateProductsApi.class);

    @Inject
    TemplateService service;

    @POST
    @RolesAllowed({"admin"})
    @Path("/{product}/{template}")
    public Uni<Void> create(@PathParam("product") String product, @PathParam("template") String template, Object templateForm) {
        log.debugf("TemplateProductsApi.create %s/%s", product, template);
        return service.setValue(product, template, templateForm);
    }

    @GET
    @RolesAllowed({"admin"})
    @Path("/{product}/{template}")
    public Uni<Object> get(@PathParam("product") String product, @PathParam("template") String template) {
        log.debugf("TemplateProductsApi.get %s/%s", product, template);
        return service.getValue(product, template);
    }

    @DELETE
    @RolesAllowed({"admin"})
    @Path("/{product}/{template}")
    public Uni<Void> delete(@PathParam("product") String product, @PathParam("template") String template) {
        log.debugf("TemplateProductsApi.delete %s/%s", product, template);
        return service.delValue(product, template);
    }
}
