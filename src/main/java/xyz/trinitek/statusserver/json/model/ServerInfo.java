package xyz.trinitek.statusserver.json.model;

/**
 * A serializable model of {@link xyz.trinitek.statusserver.domain.ServerInfo}.
 */
public class ServerInfo extends JsonModel<xyz.trinitek.statusserver.domain.ServerInfo> {

    private String imageBase64;
    private String motd;

    public ServerInfo(xyz.trinitek.statusserver.domain.ServerInfo domainModel) {
        initialize(domainModel);
    }

    @Override
    protected void initialize(xyz.trinitek.statusserver.domain.ServerInfo domainModel) {
        super.initialize(domainModel);
        this.imageBase64 = domainModel.getImageBase64();
        this.motd = domainModel.getMotd();
    }

    @Override
    public xyz.trinitek.statusserver.domain.ServerInfo getDomainModel() {
        return new xyz.trinitek.statusserver.domain.ServerInfo(imageBase64, motd);
    }

}
