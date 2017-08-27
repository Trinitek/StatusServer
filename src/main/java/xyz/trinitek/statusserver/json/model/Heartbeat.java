package xyz.trinitek.statusserver.json.model;

/**
 * A serializable model of {@link xyz.trinitek.statusserver.domain.Heartbeat}.
 */
public class Heartbeat extends JsonModel<xyz.trinitek.statusserver.domain.Heartbeat> {

    private int numberOfPlayers;
    private int playerLimit;

    public Heartbeat(xyz.trinitek.statusserver.domain.Heartbeat domainModel) {
        initialize(domainModel);
    }

    @Override
    public void initialize(xyz.trinitek.statusserver.domain.Heartbeat domainModel) {
        super.initialize(domainModel);
        this.numberOfPlayers = domainModel.getNumberOfPlayers();
        this.playerLimit = domainModel.getPlayerLimit();
    }

    @Override
    public xyz.trinitek.statusserver.domain.Heartbeat getDomainModel() {
        return new xyz.trinitek.statusserver.domain.Heartbeat();
    }

}
