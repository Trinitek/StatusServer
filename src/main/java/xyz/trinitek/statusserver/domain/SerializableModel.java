package xyz.trinitek.statusserver.domain;

import org.apache.commons.lang.NullArgumentException;
import xyz.trinitek.statusserver.domain.DomainModel;

import java.io.Serializable;

/**
 * Defines a basic implementation for models that are serializable.
 */
public abstract class SerializableModel<TDomain extends DomainModel> implements Serializable {

    protected void initialize(TDomain domainModel) {
        if (domainModel == null) {
            throw new NullArgumentException("domainModel");
        }
    }

    public abstract TDomain getDomainModel();

    public abstract String serialize();

}
