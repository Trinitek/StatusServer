package xyz.trinitek.statusserver.domain;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Defines basic static information about a server as it would be displayed in the client's server list.
 */
public class ServerInfo extends DomainModel {

    private final Logger logger;

    private String imageBase64 = "";
    private String motd = "";

    /**
     * Creates a new ServerInfo using the given Server instance.
     *
     * @param server the instance from which to pull information about the server
     */
    public ServerInfo(Server server) {
        if (server == null) {
            throw new NullArgumentException("Server must not be null");
        }

        this.logger = server.getLogger();

        BufferedImage image;

        try {
            image = ImageIO.read(new File("server-icon.png"));
        }
        catch (Exception e) {
            image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        }

        this.setImage(image);
        this.setMotd(server.getMotd());
    }

    /**
     * Creates a new ServerInfo using the given image and MOTD.
     *
     * @param serverImage the server's image encoded as base64
     * @param motd        the server's message of the day
     */
    public ServerInfo(String serverImageBase64, String motd) {
        this.logger = null;
        this.setImageBase64(serverImageBase64);
        this.setMotd(motd);
    }

    /**
     * Creates a new empty ServerInfo.
     */
    public ServerInfo() {
        this.logger = null;
    }

    /**
     * Gets the server image encoded as a base64 string.
     *
     * @return the base64 encoded image
     */
    public String getImageBase64() {
        return this.imageBase64;
    }

    /**
     * Sets the server image as a base64 string.
     *
     * @param imageBase64 the base64 encoded image
     */
    public void setImageBase64(String imageBase64) {
        if (imageBase64 == null) {
            throw new NullArgumentException("imageBase64 must not be null");
        }
        if (!Base64.isBase64(imageBase64)) {
            throw new IllegalArgumentException("imageBase64 is not valid base64");
        }

        this.imageBase64 = imageBase64;
    }

    /**
     * Gets the server image. It is not guaranteed to be in the same format as it was saved.
     *
     * @return the server image
     */
    public Image getImage() {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(Base64.decodeBase64(this.getImageBase64()));

        BufferedImage image;

        try {
            image = ImageIO.read(byteIn);
        }
        catch (IOException e) {
            if (this.logger != null) {
                this.logger.warning("Error when decoding base64 server icon");
            }

            image = null;
        }

        if (image == null) {
            return new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        }
        else {
            return image;
        }
    }

    /**
     * Sets the server image to the given image. It is converted to a PNG image stream before saving.
     * Because of this, the Base64 may be different than expected, even if the source image is also a PNG.
     *
     * @param newImage the new image
     * @throws NullArgumentException thrown if the new image is null
     */
    public void setImage(BufferedImage newImage) {
        if (newImage == null) {
            throw new NullArgumentException("Image must not be null");
        }

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        try {
            ImageIO.write(newImage, "png", byteOut);
            this.setImageBase64(Base64.encodeBase64String(byteOut.toByteArray()));
        }
        catch (IOException e) {
            if (this.logger != null) {
                this.logger.warning("Error when encoding base64 server icon");
            }
            setImageBase64("");
        }
    }

    /**
     * Gets the server's message of the day.
     *
     * @return the message of the day
     */
    public String getMotd() {
        return this.motd;
    }

    /**
     * Sets the server's message of the day.
     *
     * @param newMotd the message of the day
     */
    public void setMotd(String newMotd) {
        if (newMotd == null) {
            throw new NullArgumentException("newMotd must not be null");
        }
        this.motd = newMotd;
    }
}
