// Copyright 2012 Midokura Inc.

package org.midonet.midolman.state;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.midonet.midolman.state.PortDirectory.*;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MaterializedBridgePortConfig.class,
        name = "ExteriorBridgePort"),
    @JsonSubTypes.Type(value = MaterializedRouterPortConfig.class,
        name = "ExteriorRouterPort"),
    @JsonSubTypes.Type(value = LogicalBridgePortConfig.class,
        name = "InteriorBridgePort"),
    @JsonSubTypes.Type(value = LogicalRouterPortConfig.class,
        name = "InteriorRouterPort"),
    @JsonSubTypes.Type(value = TrunkVlanBridgePortConfig.class,
        name = "TrunkPort"),
    @JsonSubTypes.Type(value = LogicalVlanBridgePortConfig.class,
        name = "InteriorVlanBridgePort")
})
public abstract class PortConfig {

    PortConfig(UUID device_id) {
        super();
        this.device_id = device_id;
    }
    // Default constructor for the Jackson deserialization.
    PortConfig() { super(); }
    public UUID device_id;
    public UUID inboundFilter;
    public UUID outboundFilter;
    public Set<UUID> portGroupIDs;
    public int tunnelKey;
    public Map<String, String> properties = new HashMap<String, String>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortConfig that = (PortConfig) o;

        if (tunnelKey != that.tunnelKey) return false;
        if (device_id != null
                ? !device_id.equals(that.device_id) : that.device_id != null)
            return false;
        if (inboundFilter != null
                ? !inboundFilter.equals(that.inboundFilter)
                : that.inboundFilter != null)
            return false;
        if (outboundFilter != null
                ? !outboundFilter.equals(that.outboundFilter)
                : that.outboundFilter != null)
            return false;
        if (portGroupIDs != null ? !portGroupIDs.equals(that.portGroupIDs)
                : that.portGroupIDs != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = device_id != null ? device_id.hashCode() : 0;
        result = 31 * result +
                (inboundFilter != null ? inboundFilter.hashCode() : 0);
        result = 31 * result +
                (outboundFilter != null ? outboundFilter.hashCode() : 0);
        result = 31 * result +
                (portGroupIDs != null ? portGroupIDs.hashCode() : 0);
        result = 31 * result + tunnelKey;
        return result;
    }
}
