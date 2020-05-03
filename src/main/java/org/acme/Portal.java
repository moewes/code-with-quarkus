package org.acme;

import io.quarkus.vertx.web.RouteBase;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.ext.web.Router;
import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.http.HttpMethod;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class Portal {

    @Inject
    PortalBean bean;

    public void init(@Observes Router router) {
        router.get("/my-route").handler(rc -> rc.response().end("Hello from my route"));

        router.get("/portal*").handler(rc -> rc.response().end(bean.getPortalPage()));
    }

}