/*
* Copyright 2012 Midokura Europe SARL
*/
package org.midonet.odp.protos;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.midonet.odp.FlowMatch;
import org.midonet.odp.flows.FlowAction;
import org.midonet.odp.flows.FlowActions;
import org.midonet.odp.flows.FlowKeyEtherType;
import static org.midonet.odp.flows.FlowKeys.encap;
import static org.midonet.odp.flows.FlowKeys.etherType;
import static org.midonet.odp.flows.FlowKeys.ethernet;
import static org.midonet.odp.flows.FlowKeys.inPort;
import static org.midonet.odp.flows.FlowKeys.vlan;

public class OvsFlowsCreateSet_VLAN_Truncated_Test
    extends OvsFlowsCreateSetMatchTest {

    @Before
    public void setUp() throws Exception {
        super.setUp(responses);
        connection = OvsDatapathConnection.create(channel, reactor);
        connection.bypassSendQueue(true);
        connection.setMaxBatchIoOps(1);
    }

    @Override
    protected int uplinkPid() {
        return 7867;
    }

    @Override
    protected FlowMatch flowMatch() {
        return new FlowMatch()
            .addKey(inPort(0))
            .addKey(ethernet(macFromString("ae:b3:77:8c:a1:48"),
                             macFromString("33:33:00:00:00:16")))
            .addKey(etherType(FlowKeyEtherType.Type.ETH_P_8021Q))
            .addKey(vlan(0x0101))
            .addKey(encap());
    }

    @Override
    protected List<FlowAction<?>> flowActions() {
        return Arrays.<FlowAction<?>>asList(FlowActions.output(513));
    }

    @Test
    public void testQandQ() throws Exception {
        doTest();
    }

    final byte[][] responses = {
/*
// write - time: 1343041885198
    {
        (byte)0x28, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x02, (byte)0x00,
        (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x64, (byte)0x61,
        (byte)0x74, (byte)0x61, (byte)0x70, (byte)0x61, (byte)0x74, (byte)0x68,
        (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885199
        {
            (byte)0xC0, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x02,
            (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x64, (byte)0x61,
            (byte)0x74, (byte)0x61, (byte)0x70, (byte)0x61, (byte)0x74, (byte)0x68,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0xF9, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x04, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x05, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x54, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x14, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x0E, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x24, (byte)0x00, (byte)0x07, (byte)0x00, (byte)0x20, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F,
            (byte)0x64, (byte)0x61, (byte)0x74, (byte)0x61, (byte)0x70, (byte)0x61,
            (byte)0x74, (byte)0x68, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885217
    {
        (byte)0x24, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x0E, (byte)0x00, (byte)0x02, (byte)0x00,
        (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x76, (byte)0x70,
        (byte)0x6F, (byte)0x72, (byte)0x74, (byte)0x00, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885218
        {
            (byte)0xB8, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x02,
            (byte)0x00, (byte)0x00, (byte)0x0E, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x76, (byte)0x70,
            (byte)0x6F, (byte)0x72, (byte)0x74, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0xFA, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x64, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x54, (byte)0x00, (byte)0x06, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x0B, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x0E, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x20, (byte)0x00, (byte)0x07, (byte)0x00,
            (byte)0x1C, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x0E, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x6F, (byte)0x76,
            (byte)0x73, (byte)0x5F, (byte)0x76, (byte)0x70, (byte)0x6F, (byte)0x72,
            (byte)0x74, (byte)0x00, (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885219
    {
        (byte)0x24, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x0D, (byte)0x00, (byte)0x02, (byte)0x00,
        (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x66, (byte)0x6C,
        (byte)0x6F, (byte)0x77, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885220
        {
            (byte)0xB8, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x02,
            (byte)0x00, (byte)0x00, (byte)0x0D, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x66, (byte)0x6C,
            (byte)0x6F, (byte)0x77, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0xFB, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x06, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x54, (byte)0x00, (byte)0x06, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x0B, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x0E, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x20, (byte)0x00, (byte)0x07, (byte)0x00,
            (byte)0x1C, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x0D, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x6F, (byte)0x76,
            (byte)0x73, (byte)0x5F, (byte)0x66, (byte)0x6C, (byte)0x6F, (byte)0x77,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885220
    {
        (byte)0x24, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x0F, (byte)0x00, (byte)0x02, (byte)0x00,
        (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x70, (byte)0x61,
        (byte)0x63, (byte)0x6B, (byte)0x65, (byte)0x74, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885221
        {
            (byte)0x5C, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x02,
            (byte)0x00, (byte)0x00, (byte)0x0F, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x70, (byte)0x61,
            (byte)0x63, (byte)0x6B, (byte)0x65, (byte)0x74, (byte)0x00, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0xFC, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x04, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x18, (byte)0x00, (byte)0x06, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885223
    {
        (byte)0x28, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x02, (byte)0x00,
        (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x64, (byte)0x61,
        (byte)0x74, (byte)0x61, (byte)0x70, (byte)0x61, (byte)0x74, (byte)0x68,
        (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885224
        {
            (byte)0xC0, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x02,
            (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x64, (byte)0x61,
            (byte)0x74, (byte)0x61, (byte)0x70, (byte)0x61, (byte)0x74, (byte)0x68,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0xF9, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x04, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x05, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x54, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x14, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x0E, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x24, (byte)0x00, (byte)0x07, (byte)0x00, (byte)0x20, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F,
            (byte)0x64, (byte)0x61, (byte)0x74, (byte)0x61, (byte)0x70, (byte)0x61,
            (byte)0x74, (byte)0x68, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885227
    {
        (byte)0x24, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x0E, (byte)0x00, (byte)0x02, (byte)0x00,
        (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x76, (byte)0x70,
        (byte)0x6F, (byte)0x72, (byte)0x74, (byte)0x00, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885227
        {
            (byte)0xB8, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x02,
            (byte)0x00, (byte)0x00, (byte)0x0E, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x6F, (byte)0x76, (byte)0x73, (byte)0x5F, (byte)0x76, (byte)0x70,
            (byte)0x6F, (byte)0x72, (byte)0x74, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0xFA, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x64, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x54, (byte)0x00, (byte)0x06, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00,
            (byte)0x0B, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x0E, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x14, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x08, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x0B, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x20, (byte)0x00, (byte)0x07, (byte)0x00,
            (byte)0x1C, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x0E, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x6F, (byte)0x76,
            (byte)0x73, (byte)0x5F, (byte)0x76, (byte)0x70, (byte)0x6F, (byte)0x72,
            (byte)0x74, (byte)0x00, (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885283
    {
        (byte)0x24, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xF9, (byte)0x00,
        (byte)0x09, (byte)0x00, (byte)0x07, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x09, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x62, (byte)0x69,
        (byte)0x62, (byte)0x69, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885283
        {
            (byte)0x48, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xF9, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x07, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0xBE, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x09, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x62, (byte)0x69,
            (byte)0x62, (byte)0x69, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x24, (byte)0x00, (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x1D, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x1D, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885302
    {
        (byte)0x4C, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFB, (byte)0x00,
        (byte)0x09, (byte)0x04, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0xBE, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x04, (byte)0x00, (byte)0x02, (byte)0x80, (byte)0x30, (byte)0x00,
        (byte)0x01, (byte)0x80, (byte)0x08, (byte)0x00, (byte)0x03, (byte)0x00,
        (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00,
        (byte)0x04, (byte)0x00, (byte)0xAE, (byte)0xB3, (byte)0x77, (byte)0x8C,
        (byte)0xA1, (byte)0x48, (byte)0x33, (byte)0x33, (byte)0x00, (byte)0x00,
        (byte)0x00, (byte)0x16, (byte)0x06, (byte)0x00, (byte)0x06, (byte)0x00,
        (byte)0x81, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00,
        (byte)0x05, (byte)0x00, (byte)0x11, (byte)0x01, (byte)0x00, (byte)0x00,
        (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00
    },
*/
        // read - time: 1343041885305
        {
            (byte)0x4C, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFB, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0xBE, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x30, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x10, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0xAE, (byte)0xB3,
            (byte)0x77, (byte)0x8C, (byte)0xA1, (byte)0x48, (byte)0x33, (byte)0x33,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x06, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x81, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x11, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x02, (byte)0x00
        },
/*
// write - time: 1343041885315
    {
        (byte)0x48, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFB, (byte)0x00,
        (byte)0x09, (byte)0x06, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0xBE, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x30, (byte)0x00, (byte)0x01, (byte)0x80, (byte)0x08, (byte)0x00,
        (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x10, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0xAE, (byte)0xB3,
        (byte)0x77, (byte)0x8C, (byte)0xA1, (byte)0x48, (byte)0x33, (byte)0x33,
        (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x06, (byte)0x00,
        (byte)0x06, (byte)0x00, (byte)0x81, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x06, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x11, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00
    },
*/
        // read - time: 1343041885316
        {
            (byte)0x4C, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFB, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0xBE, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x30, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x10, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0xAE, (byte)0xB3,
            (byte)0x77, (byte)0x8C, (byte)0xA1, (byte)0x48, (byte)0x33, (byte)0x33,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x06, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x81, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x11, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x04, (byte)0x00, (byte)0x02, (byte)0x00
        },

        // read - time: 1343041885316
        {
            (byte)0x14, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x00,
            (byte)0x02, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00
        },
/*
// write - time: 1343041885320
    {
        (byte)0x54, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFB, (byte)0x00,
        (byte)0x09, (byte)0x00, (byte)0x0A, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0xBE, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x30, (byte)0x00, (byte)0x01, (byte)0x80, (byte)0x08, (byte)0x00,
        (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x10, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0xAE, (byte)0xB3,
        (byte)0x77, (byte)0x8C, (byte)0xA1, (byte)0x48, (byte)0x33, (byte)0x33,
        (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x06, (byte)0x00,
        (byte)0x06, (byte)0x00, (byte)0x81, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x06, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x11, (byte)0x01,
        (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00,
        (byte)0x0C, (byte)0x00, (byte)0x02, (byte)0x80, (byte)0x08, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x00, (byte)0x00
    },
*/
        // read - time: 1343041885321
        {
            (byte)0x54, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFB, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x0A, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x56, (byte)0x55, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0xBE, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x30, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x10, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0xAE, (byte)0xB3,
            (byte)0x77, (byte)0x8C, (byte)0xA1, (byte)0x48, (byte)0x33, (byte)0x33,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x06, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x81, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x06, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x11, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x0C, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x08, (byte)0x00,
            (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x00, (byte)0x00
        },
    };
}
