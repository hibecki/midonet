/*
 * Copyright 2015 Midokura SARL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.midonet.cluster.services.c3po.translators

import org.midonet.cluster.data.storage.{ReadOnlyStorage, Transaction}
import org.midonet.cluster.models.Neutron.NeutronConfig
import org.midonet.cluster.models.Topology.TunnelZone

/**
  * Provides a translator for Neutron Config.
  */
class ConfigTranslator(protected val storage: ReadOnlyStorage)
    extends Translator[NeutronConfig] with TunnelZoneManager {

    override protected def translateCreate(tx: Transaction,
                                           config: NeutronConfig)
    : OperationList = {

        if (tx.exists(classOf[TunnelZone], config.getId)) {
            return List()
        }

        // Create the singleton Tunnel Zone
        tx.create(neutronDefaultTunnelZone(config))

        List()
    }

    override protected def translateUpdate(tx: Transaction,
                                           config: NeutronConfig)
    : OperationList = {
        throw new UnsupportedOperationException(
            "Config Update is not supported.")
    }

    override protected def translateDelete(tx: Transaction,
                                           config: NeutronConfig)
    : OperationList = {
        throw new UnsupportedOperationException(
            "Config Delete is not supported.")
    }
}
