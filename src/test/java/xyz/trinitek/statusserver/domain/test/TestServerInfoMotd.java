package xyz.trinitek.statusserver.domain.test;

import org.apache.commons.lang.NullArgumentException;
import org.junit.Assert;
import org.junit.Test;
import xyz.trinitek.statusserver.domain.ServerInfo;

/**
 * Tests the MOTD getters and setters in {@link ServerInfo}.
 */
public class TestServerInfoMotd {

    @Test
    public void setMotd() {
        ServerInfo info = new ServerInfo();
        info.setMotd("asdf");
        Assert.assertEquals("asdf", info.getMotd());
    }

    @Test(expected = NullArgumentException.class)
    public void setNullMotd() {
        ServerInfo info = new ServerInfo();
        info.setMotd(null);
    }

}