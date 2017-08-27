package xyz.trinitek.statusserver.json.model;

import com.google.gson.Gson;
import xyz.trinitek.statusserver.domain.DomainModel;
import xyz.trinitek.statusserver.domain.SerializableModel;

/**
 * Defines a basic implementation for models that are serializable to JSON.
 */
public abstract class JsonModel<TDomain extends DomainModel> extends SerializableModel<TDomain> {

    private transient final Gson gson;

    public JsonModel() {
        this.gson = new Gson();
    }

    @Override
    public String serialize() {
        return this.gson.toJson(this);
    }

}
