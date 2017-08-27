package xyz.trinitek.statusserver.ioc;

import com.google.inject.AbstractModule;
import xyz.trinitek.statusserver.domain.ServerProfileProvider;

/**
 * Configures the IoC container for {@link xyz.trinitek.statusserver.resource.StatusResource}.
 */
public class StatusResourceModule extends AbstractModule {

    private final ServerProfileProvider serverProfileProvider;

    public StatusResourceModule(ServerProfileProvider serverProfileProvider) {
        this.serverProfileProvider = serverProfileProvider;
    }

    @Override
    protected void configure() {
        bind(ServerProfileProvider.class).toInstance(this.serverProfileProvider);
    }
}
