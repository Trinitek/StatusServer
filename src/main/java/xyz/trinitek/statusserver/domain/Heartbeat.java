package xyz.trinitek.statusserver.domain;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Server;

/**
 * TODO description
 */
public class Heartbeat extends DomainModel {

    private int numberOfPlayers;
    private int playerLimit;

    public Heartbeat(Server server) {
        if (server == null) {
            throw new NullArgumentException("server");
        }

        setNumberOfPlayers(server.getOnlinePlayers().size());
        setPlayerLimit(server.getMaxPlayers());
    }

    public Heartbeat(int numberOfPlayers, int playerLimit) {
        this.setNumberOfPlayers(numberOfPlayers);
        this.setPlayerLimit(playerLimit);
    }

    public Heartbeat() {}

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 0) {
            throw new IllegalArgumentException("Number of players must be greater than 0");
        }

        this.numberOfPlayers = numberOfPlayers;
    }

    public void setPlayerLimit(int playerLimit) {
        if (playerLimit < 0) {
            throw new IllegalArgumentException("Max number of players must be greater than 0");
        }

        this.playerLimit = playerLimit;
    }

    public int getPlayerLimit() {
        return this.playerLimit;
    }
}
