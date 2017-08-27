package xyz.trinitek.statusserver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.ext.guice.RestletGuice;
import xyz.trinitek.statusserver.ioc.StatusResourceModule;
import xyz.trinitek.statusserver.json.model.ServerProfileProviderImpl;

import java.util.logging.Level;

/**
 * The entry point of the plugin.
 */
public class PluginMain extends JavaPlugin {

    private Injector injector;
    private Component restServerComponent;

    @Override
    public void onLoad() {
        AbstractModule[] modules = {
                new StatusResourceModule(new ServerProfileProviderImpl(this.getServer())),
                new RestletGuice.Module()
        };

        this.injector = Guice.createInjector(modules);

        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        if (this.restServerComponent != null) {
            for (int i = 0; i < 10; i++) {
                if (this.restServerComponent.isStarted()) {
                    getLogger().info(String.format("Waiting for REST service to stop... (%d/%d tries)", i, 10));
                    try {
                        Thread.sleep(200);
                    }
                    catch (Exception e) {
                        getLogger().log(Level.SEVERE, e.getMessage(), e);
                    }
                }
                else {
                    break;
                }
            }
            if (this.restServerComponent.isStarted()) {
                getLogger().warning("Gave up waiting for REST service to stop");
            }
        }

        int port = this.getConfig().getInt("port");

        getLogger().info("Starting REST service on port " + port);

        Component component = new Component();

        this.restServerComponent = component;

        component.getServers().add(Protocol.HTTP, port);
        component.getDefaultHost().attach("", new RestApplication(this.injector.getInstance(FinderFactory.class)));

        try {
            component.start();
        }
        catch (Exception e) {
            getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Stopping REST service");

        try {
            this.restServerComponent.stop();
        }
        catch (Exception e) {
            getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
