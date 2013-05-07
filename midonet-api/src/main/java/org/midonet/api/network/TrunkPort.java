/*
 * Copyright 2012 Midokura Europe SARL
 * Copyright 2012 Midokura PTE LTD.
 */
package org.midonet.api.network;

import java.net.URI;
import java.util.UUID;

import org.midonet.api.ResourceUriBuilder;
import org.midonet.cluster.data.Port.Property;

/**
 * DTO for trunk port (in vlan-aware bridges)
 */
public class TrunkPort extends VlanBridgePort implements ExteriorPort {

    /**
     * VIF ID
     */
    private UUID vifId;

    /**
     * Host ID required to generate `hostInterfacePort` property.
     */
    private UUID hostId;

    /**
     * Id of the peer trunk on the device
     */
    private UUID peerId;

    /**
     * Default constructor
     */
    public TrunkPort() {
        super();
    }

    /**
     * Constructor
     *
     * @param id ID of the port
     * @param deviceId ID of the device
     * @param vifId ID of the VIF.
     */
    public TrunkPort(UUID id, UUID deviceId, UUID vifId, UUID peerId) {
        super(id, deviceId);
        this.vifId = vifId;
        this.peerId = peerId;
    }

    /**
     * Constructor
     *
     * @param portData vlan bridge trunk port data object
     */
    public TrunkPort(org.midonet.cluster.data.ports.TrunkPort portData) {
        super(portData);
        if (portData.getProperty(Property.vif_id) != null) {
            this.vifId = UUID.fromString(portData.getProperty(Property.vif_id));
        }
        if (portData.getProperty(Property.peer_id) != null) {
            this.peerId = UUID.fromString(portData.getProperty(Property.peer_id));
        }
        this.hostId = portData.getHostId();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.midonet.api.network.Port#getType()
     */
    @Override
    public String getType() {
        return PortType.TRUNK_VLAN_BRIDGE;
    }

    /**
     * @return the peerId
     */
    public UUID getPeerId() {
        return peerId;
    }

    public URI getPeer() {
        if (peerId != null) {
            return ResourceUriBuilder.getPort(getBaseUri(), peerId);
        } else {
            return null;
        }
    }

    /**
     * @param peerId the peer id to set
     */
    public void setPeerId(UUID peerId) {
        this.peerId = peerId;
    }

    /**
     * @return the vifId
     */
    @Override
    public UUID getVifId() {
        return vifId;
    }

    /**
     * @param vifId the vifId to set
     */
    @Override
    public void setVifId(UUID vifId) {
        this.vifId = vifId;
    }

    @Override
    public boolean isInterior() {
        return false;
    }

    @Override
    public UUID getAttachmentId() {
        return this.vifId;
    }

    @Override
    public void setAttachmentId(UUID id) {
        this.vifId = id;
    }

    @Override
    public boolean isVlanBridgePort() {
        return true;
    }

    public URI getLink() {
        if (id != null) {
            return ResourceUriBuilder.getPortLink(getBaseUri(), id);
        } else {
            return null;
        }
    }

    /**
     * Getter to be used to generate "host-interface-port" property's value.
     *
     * <code>host-interface-port</code> property in the JSON representation
     * of this client-side port DTO object would be generated by this method
     * automatically.
     *
     * @return the URI of the host-interface-port binding
     */
    @Override
    public URI getHostInterfacePort() {
        if (getBaseUri() != null && this.hostId != null &&
                this.getId() != null) {
            return ResourceUriBuilder.getHostInterfacePort(
                    getBaseUri(), this.hostId, this.getId());
        } else {
            return null;
        }
    }

    @Override
    public org.midonet.cluster.data.Port toData() {
        org.midonet.cluster.data.ports.TrunkPort data =
            new org.midonet.cluster.data.ports.TrunkPort();
        if (this.vifId != null) {
            data.setProperty(Property.vif_id, this.vifId.toString());
        }
        if (this.peerId != null) {
            data.setProperty(Property.peer_id, this.peerId.toString());
        }
        super.setConfig(data);
        return data;
    }

    @Override
    public String toString() {
        return super.toString() + ", vifId=" + vifId;
    }
}
