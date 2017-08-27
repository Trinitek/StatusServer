package xyz.trinitek.statusserver.domain.test;

import org.junit.Assert;
import org.junit.Test;
import xyz.trinitek.statusserver.domain.ServerInfo;

/**
 * Tests the constructors in {@link ServerInfo}.
 */
public class TestServerInfoConstructor {

    @Test
    public void parameterlessConstructor() {
        ServerInfo info = new ServerInfo();
        Assert.assertEquals("", info.getImageBase64());
        Assert.assertNotNull(info.getImage());
        Assert.assertEquals("", info.getMotd());
    }

}
