package xyz.trinitek.statusserver.resource;

import com.google.inject.Inject;
import org.apache.commons.lang.NullArgumentException;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import xyz.trinitek.statusserver.domain.ServerProfileProvider;

/**
 * The REST endpoint that returns a serialized {@link xyz.trinitek.statusserver.domain.Heartbeat}.
 */
public class HeartbeatResource extends ServerResource {

    private final ServerProfileProvider provider;

    @Inject
    public HeartbeatResource(ServerProfileProvider provider) {
        if (provider == null) {
            throw new NullArgumentException("provider");
        }
        this.provider = provider;
    }

    @Get("json")
    public String getMCHeartbeat() {
        return this.provider.getHeartbeat().serialize();
    }

}
