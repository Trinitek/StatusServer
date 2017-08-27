package xyz.trinitek.statusserver;

import org.apache.commons.lang.NullArgumentException;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.routing.Router;
import org.restlet.routing.TemplateRoute;
import xyz.trinitek.statusserver.resource.HeartbeatResource;
import xyz.trinitek.statusserver.resource.StatusResource;

/**
 * TODO description
 */
public class RestApplication extends Application {

    private final FinderFactory finderFactory;

    public RestApplication(FinderFactory finderFactory) {
        if (finderFactory == null) {
            throw new NullArgumentException("finderFactory");
        }

        this.finderFactory = finderFactory;
    }

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());

        TemplateRoute[] routes = {
                router.attach("/status", this.finderFactory.finder(StatusResource.class)),
                router.attach("/status/heartbeat", this.finderFactory.finder(HeartbeatResource.class))
        };

        for (TemplateRoute route : routes) {
            getLogger().info(route.toString());
        }

        return router;
    }

}
