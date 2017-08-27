package xyz.trinitek.statusserver.domain.test;

import com.google.common.io.Files;
import org.apache.commons.lang.NullArgumentException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.trinitek.statusserver.domain.ServerInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Tests the image getters and setters in {@link ServerInfo}.
 */
public class TestServerInfoImage {

    private static String sourceBase64;
    private static BufferedImage sourceImage;
    private static String expectedBase64;

    @BeforeClass
    public static void beforeClass() throws IOException {
        sourceBase64 = Files.readFirstLine(new File("src/test/resources/small-base64.txt"), StandardCharsets.UTF_8);
        sourceImage = ImageIO.read(new File("src/test/resources/small.png"));
        expectedBase64 = Files.readFirstLine(new File("src/test/resources/small-expected-base64.txt"), StandardCharsets.UTF_8);
    }

    @Test
    public void setValidBase64() {
        ServerInfo info = new ServerInfo();
        info.setImageBase64(sourceBase64);
        Assert.assertEquals(sourceBase64, info.getImageBase64());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidBase64() {
        ServerInfo info = new ServerInfo();
        info.setImageBase64("!@#$");
    }

    @Test
    public void setImage() throws Exception {
        ServerInfo info = new ServerInfo();
        info.setImage(sourceImage);
        Assert.assertEquals(expectedBase64, info.getImageBase64());
    }

    @Test(expected = NullArgumentException.class)
    public void setNullImage() throws Exception {
        ServerInfo info = new ServerInfo();
        info.setImage(null);
    }

}
