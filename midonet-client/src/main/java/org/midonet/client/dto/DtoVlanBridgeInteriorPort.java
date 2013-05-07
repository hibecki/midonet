/*
 * Copyright 2011 Midokura Europe SARL
 */

package org.midonet.client.dto;

import java.net.URI;
import java.util.UUID;

public class DtoVlanBridgeInteriorPort extends DtoPort
    implements DtoInteriorPort {

    private UUID peerId = null;
    private URI peer = null;
    private URI link = null;
    private URI unlink = null;
    private Short vlanId = null;

    @Override
    public UUID getPeerId() {
        return peerId;
    }

    @Override
    public void setPeerId(UUID peerId) {
        this.peerId = peerId;
    }

    @Override
    public URI getPeer() {
        return this.peer;
    }

    @Override
    public void setPeer(URI peer) {
        this.peer = peer;
    }

    @Override
    public URI getLink() {
        return this.link;
    }

    @Override
    public void setLink(URI link) {
        this.link = link;
    }

    @Override
    public String getType() {
        return PortType.INTERIOR_VLAN_BRIDGE;
    }

    public Short getVlanId() {
        return this.vlanId;
    }

    public void setVlanId(Short vlanId) {
        this.vlanId = vlanId;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        DtoVlanBridgeInteriorPort port = (DtoVlanBridgeInteriorPort) other;

        if (peerId != null ? !peerId.equals(port.peerId) : port.peerId != null) {
            return false;
        }

        if (peer != null ? !peer.equals(port.peer) : port.peer != null) {
            return false;
        }

        if (link != null ? !link.equals(port.link) : port.link != null) {
            return false;
        }

        if (unlink != null ? !unlink.equals(port.unlink) : port.unlink != null) {
            return false;
        }

        if (vlanId != null ? !vlanId.equals(port.vlanId) : port.vlanId != null) {
            return false;
        }

        return true;
    }
}
