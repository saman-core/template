package io.samancore;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import io.smallrye.mutiny.Uni;

@Path("/templates")
public class TemplateApi {

    @Inject
    TemplateService service;

    @POST
    @RolesAllowed({"admin"})
    @Path("/{product}/{template}")
    public Uni<Void> create(@PathParam("product") String product, @PathParam("template") String template, Object templateForm) {
        return service.setValue(product, template, templateForm);
    }

    @GET
    @RolesAllowed({"admin"})
    @Path("/{product}/{template}")
    public Uni<Object> get(@PathParam("product") String product, @PathParam("template") String template) {
        return service.getValue(product, template);
    }

    @DELETE
    @RolesAllowed({"admin"})
    @Path("/{product}/{template}")
    public Uni<Void> delete(@PathParam("product") String product, @PathParam("template") String template) {
        return service.delValue(product, template);
    }
}