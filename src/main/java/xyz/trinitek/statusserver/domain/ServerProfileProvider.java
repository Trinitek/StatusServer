package xyz.trinitek.statusserver.domain;

/**
 * Interface that defines a provider for cursory server information.
 */
public interface ServerProfileProvider<TServerInfo extends SerializableModel, THeartbeat extends SerializableModel> {

    TServerInfo getServerInfo();
    THeartbeat getHeartbeat();

}
