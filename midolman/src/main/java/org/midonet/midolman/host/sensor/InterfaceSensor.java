/*
 * Copyright 2014 Midokura SARL
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
package org.midonet.midolman.host.sensor;

import org.midonet.midolman.host.interfaces.InterfaceDescription;

import java.util.Set;

public interface InterfaceSensor {

    /**
     * Given a list of interfaces on the system, traverses the list and updates each interface
     * with the data retrieved from the sensor.
     * The first InterfaceSensor will create the InterfaceDescriptions. The rest will just
     * update them.
     *
     * @param interfaces list of interfaces detected on the system, or null on first call
     */
    public void updateInterfaceData(Set<InterfaceDescription> interfaces);
}
