package xyz.trinitek.statusserver.json.model.test;

import com.google.common.io.Files;
import org.apache.commons.lang.NullArgumentException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.trinitek.statusserver.json.model.ServerInfo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Tests the constructors in {@link ServerInfo}.
 */
public class TestServerInfoConstructor {

    private static String sourceBase64;

    @BeforeClass
    public static void beforeClass() throws IOException {
        sourceBase64 = Files.readFirstLine(new File("src/test/resources/small-base64.txt"), StandardCharsets.UTF_8);
    }

    @Test
    public void constructorDefaults() {
        xyz.trinitek.statusserver.domain.ServerInfo domain = new xyz.trinitek.statusserver.domain.ServerInfo();
        ServerInfo info = new ServerInfo(domain);
        Assert.assertEquals(domain.getImageBase64(), info.getDomainModel().getImageBase64());
        Assert.assertEquals(domain.getMotd(), info.getDomainModel().getMotd());
    }

    @Test
    public void constructorValidInputs() {
        xyz.trinitek.statusserver.domain.ServerInfo domain = new xyz.trinitek.statusserver.domain.ServerInfo(sourceBase64, "asdf");
        ServerInfo info = new ServerInfo(domain);
        Assert.assertEquals(domain.getImageBase64(), info.getDomainModel().getImageBase64());
        Assert.assertEquals(domain.getMotd(), info.getDomainModel().getMotd());
    }

    @Test(expected = NullArgumentException.class)
    public void constructorNull() {
        new ServerInfo(null);
    }

}
