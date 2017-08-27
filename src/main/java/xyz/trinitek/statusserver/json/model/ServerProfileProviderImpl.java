package xyz.trinitek.statusserver.json.model;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Server;
import xyz.trinitek.statusserver.domain.ServerProfileProvider;

/**
 * Implements {@link ServerProfileProvider} for the implementation models that are serializable to JSON.
 */
public class ServerProfileProviderImpl implements ServerProfileProvider<ServerInfo, Heartbeat> {

    private final Server bukkitServer;

    public ServerProfileProviderImpl(Server bukkitServer) {
        if (bukkitServer == null) {
            throw new NullArgumentException("bukkitServer");
        }

        this.bukkitServer = bukkitServer;
    }

    @Override
    public ServerInfo getServerInfo() {
        return new ServerInfo(new xyz.trinitek.statusserver.domain.ServerInfo(bukkitServer));
    }

    @Override
    public Heartbeat getHeartbeat() {
        return new Heartbeat(new xyz.trinitek.statusserver.domain.Heartbeat(bukkitServer));
    }
}
