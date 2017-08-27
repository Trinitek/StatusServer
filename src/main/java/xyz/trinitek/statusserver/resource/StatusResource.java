package xyz.trinitek.statusserver.resource;

import com.google.inject.Inject;
import org.apache.commons.lang.NullArgumentException;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import xyz.trinitek.statusserver.domain.ServerProfileProvider;

/**
 * The REST endpoint that returns a serialized {@link xyz.trinitek.statusserver.domain.ServerInfo}.
 */
public class StatusResource extends ServerResource {

    private final ServerProfileProvider provider;

    @Inject
    public StatusResource(ServerProfileProvider provider) {
        if (provider == null) {
            throw new NullArgumentException("provider");
        }
        this.provider = provider;
    }

    @Get("json")
    public String getMCServerInfo() {
        return this.provider.getServerInfo().serialize();
    }

}
