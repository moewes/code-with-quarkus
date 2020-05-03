package org.acme;

import io.smallrye.mutiny.Multi;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/links")
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> hello() {

        ArrayList hello =  new ArrayList();
        hello.add( PortalLink.builder().text("Home").link("/portal").build());
        hello.add( PortalLink.builder().text("Users").link("/portal/users").build());

        return Multi.createFrom().iterable(hello);
    }
}